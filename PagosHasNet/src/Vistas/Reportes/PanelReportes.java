package Vistas.Reportes;

import Controlador.Alertas.controladorAlertas;
import clases.ExportadorExcel;
import clases.Instancias;
import clases.SQLIngresos;
import clases.big;
import java.io.File;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class PanelReportes extends javax.swing.JPanel {

    private static final String TODOS = "Todos los movimientos";
    private final List<String> codigosTipo = new ArrayList<>();
    private final List<Object[]> filasReporte = new ArrayList<>();
    private BigDecimal totalIngresos = BigDecimal.ZERO;
    private BigDecimal totalGastos = BigDecimal.ZERO;

    public PanelReportes() {
        initComponents();
        tblMovimientos.getTableHeader().setFont(new java.awt.Font("Century Gothic", java.awt.Font.BOLD, 14));
        cmbTipo.removeAllItems();
        cmbTipo.addItem(TODOS);
        codigosTipo.add(null);
        for (java.util.Map.Entry<String, String> tipo : SQLIngresos.TIPOS_MOVIMIENTO.entrySet()) {
            cmbTipo.addItem(tipo.getValue());
            codigosTipo.add(tipo.getKey());
        }
        LocalDate hoy = LocalDate.now();
        dtDesde.setDate(aFecha(hoy.withDayOfMonth(1)));
        dtHasta.setDate(aFecha(hoy));
    }

    private static Date aFecha(LocalDate fecha) {
        return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private static LocalDate aLocalDate(Date fecha) {
        return fecha == null ? null : fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void recargar() {
        consultar();
    }

    private void consultar() {
        try {
            String tipo = codigosTipo.get(Math.max(0, cmbTipo.getSelectedIndex()));
            List<Object[]> movimientos = Instancias.getInstancias().getSqlIngresos()
                    .reporteMovimientos(aLocalDate(dtDesde.getDate()), aLocalDate(dtHasta.getDate()), tipo, txtCliente.getText());

            DefaultTableModel modelo = (DefaultTableModel) tblMovimientos.getModel();
            modelo.setRowCount(0);
            filasReporte.clear();
            totalIngresos = BigDecimal.ZERO;
            totalGastos = BigDecimal.ZERO;
            for (Object[] movimiento : movimientos) {
                BigDecimal valor = (BigDecimal) movimiento[6];
                if (valor.signum() < 0) {
                    totalGastos = totalGastos.add(valor.negate());
                } else {
                    totalIngresos = totalIngresos.add(valor);
                }
                String fecha = ((LocalDate) movimiento[0]).format(SQLIngresos.FORMATO_FECHA);
                String nombreTipo = SQLIngresos.nombreTipoMovimiento((String) movimiento[1]);
                modelo.addRow(new Object[]{fecha, nombreTipo, movimiento[2], movimiento[3], movimiento[4], movimiento[5], big.setMoneda(valor)});
                filasReporte.add(new Object[]{fecha, nombreTipo, movimiento[2], movimiento[3], movimiento[4], movimiento[5], valor});
            }

            lbCantidad.setText(movimientos.size() + " movimientos");
            lbIngresos.setText("Ingresos: " + big.setMoneda(totalIngresos));
            lbGastos.setText("Gastos: " + big.setMoneda(totalGastos));
            BigDecimal neto = totalIngresos.subtract(totalGastos);
            lbNeto.setText("Neto: " + big.setMoneda(neto));
            lbNeto.setForeground(neto.signum() < 0 ? new java.awt.Color(192, 57, 43) : new java.awt.Color(44, 62, 80));
        } catch (IllegalStateException ex) {
            controladorAlertas.alertFail(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PanelReportes.class.getName()).log(Level.SEVERE, "Error al consultar el reporte", ex);
            controladorAlertas.alertFail("No se pudo consultar el reporte de movimientos");
        }
    }

    private void exportar() {
        if (filasReporte.isEmpty()) {
            controladorAlertas.alert("No hay movimientos para exportar. Presione CONSULTAR primero.");
            return;
        }
        LocalDate desde = aLocalDate(dtDesde.getDate());
        LocalDate hasta = aLocalDate(dtHasta.getDate());
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar reporte de movimientos");
        selector.setFileFilter(new FileNameExtensionFilter("Libro de Excel 97-2003 (*.xls)", "xls"));
        selector.setSelectedFile(new File("Movimientos_" + desde.toString() + "_a_" + hasta.toString() + ".xls"));
        if (selector.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File archivo = selector.getSelectedFile();
        if (!archivo.getName().toLowerCase().endsWith(".xls")) {
            archivo = new File(archivo.getParentFile(), archivo.getName() + ".xls");
        }
        String titulo = "Reporte de movimientos del " + desde.format(SQLIngresos.FORMATO_FECHA) + " al " + hasta.format(SQLIngresos.FORMATO_FECHA)
                + " - " + cmbTipo.getSelectedItem() + (txtCliente.getText().trim().isEmpty() ? "" : " - Cliente: " + txtCliente.getText().trim());
        List<String[]> totales = Arrays.asList(new String[]{"Ingresos", big.setMoneda(totalIngresos)},
                new String[]{"Gastos", big.setMoneda(totalGastos)}, new String[]{"Neto", big.setMoneda(totalIngresos.subtract(totalGastos))});
        try {
            ExportadorExcel.exportar(archivo, titulo, new String[]{"Fecha", "Tipo", "NIT", "Cliente", "Descripción", "Cantidad", "Valor"},
                    filasReporte, totales);
            controladorAlertas.alertSuccess("Reporte guardado en " + archivo.getAbsolutePath());
        } catch (Exception ex) {
            Logger.getLogger(PanelReportes.class.getName()).log(Level.SEVERE, "Error al exportar el reporte", ex);
            controladorAlertas.alertFail("No se pudo guardar el archivo: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFiltros = new javax.swing.JPanel();
        lbDesde = new javax.swing.JLabel();
        dtDesde = new com.toedter.calendar.JDateChooser();
        lbHasta = new javax.swing.JLabel();
        dtHasta = new com.toedter.calendar.JDateChooser();
        lbTipo = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        lbCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        pnlTotales = new javax.swing.JPanel();
        lbCantidad = new javax.swing.JLabel();
        lbIngresos = new javax.swing.JLabel();
        lbGastos = new javax.swing.JLabel();
        lbNeto = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        pnlFiltros.setBackground(new java.awt.Color(255, 255, 255));
        pnlFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros del reporte de movimientos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbDesde.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbDesde.setText("Desde:");

        dtDesde.setDateFormatString("dd/MM/yyyy");
        dtDesde.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbHasta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbHasta.setText("Hasta:");

        dtHasta.setDateFormatString("dd/MM/yyyy");
        dtHasta.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        lbTipo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTipo.setText("Tipo:");

        cmbTipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los movimientos" }));

        lbCliente.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbCliente.setText("Cliente:");

        txtCliente.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCliente.setToolTipText("NIT o nombre (deje vacío para todos)");
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        btnConsultar.setBackground(new java.awt.Color(52, 152, 219));
        btnConsultar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnConsultar.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultar.setText("CONSULTAR");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        btnExportar.setBackground(new java.awt.Color(46, 204, 113));
        btnExportar.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        btnExportar.setText("EXPORTAR A EXCEL");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFiltrosLayout = new javax.swing.GroupLayout(pnlFiltros);
        pnlFiltros.setLayout(pnlFiltrosLayout);
        pnlFiltrosLayout.setHorizontalGroup(
            pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipo, 0, 189, Short.MAX_VALUE)
                    .addComponent(dtDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltrosLayout.createSequentialGroup()
                        .addComponent(lbCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFiltrosLayout.createSequentialGroup()
                        .addComponent(lbHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExportar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlFiltrosLayout.setVerticalGroup(
            pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrosLayout.createSequentialGroup()
                .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFiltrosLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(lbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(pnlFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlFiltrosLayout.createSequentialGroup()
                        .addComponent(btnConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        tblMovimientos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Tipo", "NIT", "Cliente", "Descripción", "Cantidad", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMovimientos.setRowHeight(26);
        tblMovimientos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblMovimientos);
        if (tblMovimientos.getColumnModel().getColumnCount() > 0) {
            tblMovimientos.getColumnModel().getColumn(0).setMinWidth(90);
            tblMovimientos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblMovimientos.getColumnModel().getColumn(0).setMaxWidth(110);
            tblMovimientos.getColumnModel().getColumn(1).setPreferredWidth(170);
            tblMovimientos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblMovimientos.getColumnModel().getColumn(3).setPreferredWidth(220);
            tblMovimientos.getColumnModel().getColumn(4).setPreferredWidth(380);
            tblMovimientos.getColumnModel().getColumn(5).setMinWidth(70);
            tblMovimientos.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblMovimientos.getColumnModel().getColumn(5).setMaxWidth(100);
            tblMovimientos.getColumnModel().getColumn(6).setPreferredWidth(130);
        }

        pnlTotales.setBackground(new java.awt.Color(232, 248, 239));

        lbCantidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCantidad.setText("0 movimientos");

        lbIngresos.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        lbIngresos.setForeground(new java.awt.Color(39, 174, 96));
        lbIngresos.setText("Ingresos: $ 0");

        lbGastos.setFont(new java.awt.Font("Century Gothic", 1, 17)); // NOI18N
        lbGastos.setForeground(new java.awt.Color(192, 57, 43));
        lbGastos.setText("Gastos: $ 0");

        lbNeto.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNeto.setText("Neto: $ 0");

        javax.swing.GroupLayout pnlTotalesLayout = new javax.swing.GroupLayout(pnlTotales);
        pnlTotales.setLayout(pnlTotalesLayout);
        pnlTotalesLayout.setHorizontalGroup(
            pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbCantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIngresos)
                .addGap(40, 40, 40)
                .addComponent(lbGastos)
                .addGap(40, 40, 40)
                .addComponent(lbNeto)
                .addGap(15, 15, 15))
        );
        pnlTotalesLayout.setVerticalGroup(
            pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCantidad)
                    .addComponent(lbIngresos)
                    .addComponent(lbGastos)
                    .addComponent(lbNeto))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(pnlTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTotales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            consultar();
        }
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        consultar();
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        exportar();
    }//GEN-LAST:event_btnExportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JComboBox<String> cmbTipo;
    private com.toedter.calendar.JDateChooser dtDesde;
    private com.toedter.calendar.JDateChooser dtHasta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCantidad;
    private javax.swing.JLabel lbCliente;
    private javax.swing.JLabel lbDesde;
    private javax.swing.JLabel lbGastos;
    private javax.swing.JLabel lbHasta;
    private javax.swing.JLabel lbIngresos;
    private javax.swing.JLabel lbNeto;
    private javax.swing.JLabel lbTipo;
    private javax.swing.JPanel pnlFiltros;
    private javax.swing.JPanel pnlTotales;
    private javax.swing.JTable tblMovimientos;
    private javax.swing.JTextField txtCliente;
    // End of variables declaration//GEN-END:variables
}
