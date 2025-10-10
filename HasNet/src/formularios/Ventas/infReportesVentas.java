package formularios.Ventas;

import clases.Instancias;
import clases.metodosGenerales;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class infReportesVentas extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public infReportesVentas() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();
        instancias = Instancias.getInstancias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlFormularioComponentResized(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setPreferredSize(new java.awt.Dimension(907, 783));
        jPanel1.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );

        jComboBox1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reporte de ventas", "Reporte de ventas anuladas", "Reporte de cotizaciones", "Reporte de separes", "Reporte de creditos", "Reporte de ordenes", "Reporte de impuestos de ventas", "Reportes del día", "Indicadores de ventas", "Reporte de plantillas" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setText("Seleccione:");

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        mostrarReportes();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void pnlFormularioComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlFormularioComponentResized
        try {
            if (jPanel1.getComponent(0) instanceof JPanel) {
                JPanel panel = (JPanel) jPanel1.getComponent(0);
                panel.setSize(jPanel1.getSize());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pnlFormularioComponentResized

    private void mostrarReportes() {
        if (jComboBox1.getSelectedIndex() == 1) {
            if (instancias.getUsuarioLog().isReportesVentas()) {
                infRepVentas pnlReporte = new infRepVentas();
                instancias.setRepVentas(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }

        if (jComboBox1.getSelectedIndex() == 2) {
            if (instancias.getUsuarioLog().isReporteVentasAnuladas()) {
                infRepAnulas pnlReporte = new infRepAnulas();
                instancias.setRepAnulas(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }
        if (jComboBox1.getSelectedIndex() == 3) {
            if (instancias.getUsuarioLog().isReporteCotizaciones()) {
                infRepCotizas pnlReporte = new infRepCotizas();
                instancias.setRepCotizas(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }

        if (jComboBox1.getSelectedIndex() == 4) {
            if (instancias.getUsuarioLog().isReportePlanSepares()) {
                infRepSepares pnlReporte = new infRepSepares();
                instancias.setRepSepares(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }

        }

        if (jComboBox1.getSelectedIndex() == 5) {
            if (instancias.getUsuarioLog().isReporteCreditos()) {
                infRepCreditos pnlReporte = new infRepCreditos();
                instancias.setRepCreditos(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }

        if (jComboBox1.getSelectedIndex() == 6) {
            if (instancias.getUsuarioLog().isReporteOrdenesServicio()) {
                infRepOrden pnlReporte = new infRepOrden();
                instancias.setRepOrden(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }

        if (jComboBox1.getSelectedIndex() == 7) {
            if (instancias.getUsuarioLog().isReporteIvasVentas()) {
                infRepIvas pnlReporte = new infRepIvas();
                instancias.setRepIvas(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }

        if (jComboBox1.getSelectedIndex() == 8) {
            if (instancias.getUsuarioLog().isReporteDia()) {
                infResumenDia pnlReporte = new infResumenDia();
                instancias.setResumenDia(pnlReporte);
                pnlReporte.setSize(jPanel1.getSize());
                jPanel1.removeAll();
                jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
                jPanel1.revalidate();
                jPanel1.repaint();
                jPanel1.setVisible(true);
            } else {
                jComboBox1.setSelectedIndex(0);
                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
            }
        }

        if (jComboBox1.getSelectedIndex() == 9) {
//            if (instancias.getUsuarioLog().isReporteDia()) {
            infIndicadoresVentas pnlReporte = new infIndicadoresVentas();
            instancias.setIndicadoresVentas(pnlReporte);
            pnlReporte.setSize(jPanel1.getSize());
            jPanel1.removeAll();
            jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
            jPanel1.revalidate();
            jPanel1.repaint();
            jPanel1.setVisible(true);
//            } else {
//                jComboBox1.setSelectedIndex(0);
//                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
//            }
        }

        if (jComboBox1.getSelectedIndex() == 10) {
//            if (instancias.getUsuarioLog().isReporteDia()) {
            infRepCuentaCobro pnlReporte = new infRepCuentaCobro();
            instancias.setRepCuentaCobro(pnlReporte);
            pnlReporte.setSize(jPanel1.getSize());
            jPanel1.removeAll();
            jPanel1.add(pnlReporte, CENTER_ALIGNMENT);
            jPanel1.revalidate();
            jPanel1.repaint();
            jPanel1.setVisible(true);
//            } else {
//                jComboBox1.setSelectedIndex(0);
//                metodos.msgAdvertencia(null, "No tiene permisos para esta función");
//            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JScrollPane scrFormulario;
    // End of variables declaration//GEN-END:variables
}
