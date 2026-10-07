package Vista.Cartera;

import clases.Instancias;
import clases.metodosGenerales;
import Vista.ReportesCarteras.VistaReporteAbonos;
import Vista.ReportesCarteras.VistaReporteCuentasPendientes;
import Vista.ReportesCarteras.VistaReporteNotasCredito;
import Vista.ReportesCarteras.VistaReporteNotasDebito;
import Vista.ReportesCarteras.VistaReportePagos;
import Vista.ReportesCarteras.VistaReportePagosPedientes;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class VistaReportesCartera extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;

    public VistaReportesCartera() {
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
        pnlReporte = new javax.swing.JPanel();
        cmbTiposReportes = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setTitle("Cartera");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlFormularioComponentResized(evt);
            }
        });

        pnlReporte.setBackground(new java.awt.Color(255, 255, 255));
        pnlReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlReporte.setPreferredSize(new java.awt.Dimension(907, 783));
        pnlReporte.setRequestFocusEnabled(false);

        javax.swing.GroupLayout pnlReporteLayout = new javax.swing.GroupLayout(pnlReporte);
        pnlReporte.setLayout(pnlReporteLayout);
        pnlReporteLayout.setHorizontalGroup(
            pnlReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );
        pnlReporteLayout.setVerticalGroup(
            pnlReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );

        cmbTiposReportes.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        cmbTiposReportes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reporte de cuentas pendientes", "Reporte de abonos de cuentas", "Reporte de pagos pendientes", "Reporte de abonos de pagos", "Reporte de notas crédito", "Reporte de notas débito" }));
        cmbTiposReportes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTiposReportesItemStateChanged(evt);
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
                    .addComponent(pnlReporte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTiposReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTiposReportes, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addComponent(pnlReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
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

    private void cmbTiposReportesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTiposReportesItemStateChanged
        mostrarReportes();
    }//GEN-LAST:event_cmbTiposReportesItemStateChanged

    private void pnlFormularioComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlFormularioComponentResized
        try {
            if (pnlReporte.getComponent(0) instanceof JPanel) {
                JPanel panel = (JPanel) pnlReporte.getComponent(0);
                panel.setSize(pnlReporte.getSize());
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pnlFormularioComponentResized

    private void mostrarReportes() {
        if (cmbTiposReportes.getSelectedIndex() == 1) {
            if (tienePermiso(instancias.getUsuarioLog().isReporteCarteraCxC())) {
                VistaReporteCuentasPendientes reporte = new VistaReporteCuentasPendientes();
                instancias.setRepCartera(reporte);
                cargarReporte(reporte);
            }
        }

        if (cmbTiposReportes.getSelectedIndex() == 2) {
            if (tienePermiso(instancias.getUsuarioLog().isReporteAbonosCxC())) {
                VistaReporteAbonos reporte = new VistaReporteAbonos();
                instancias.setRepAbonos(reporte);
                cargarReporte(reporte);
            }
        }

        if (cmbTiposReportes.getSelectedIndex() == 3) {
            if (tienePermiso(instancias.getUsuarioLog().isReporteCarteraCxP())) {
                VistaReportePagosPedientes reporte = new VistaReportePagosPedientes();
                instancias.setRepPagos(reporte);
                cargarReporte(reporte);
            }
        }

        if (cmbTiposReportes.getSelectedIndex() == 4) {
            if (tienePermiso(instancias.getUsuarioLog().isReporteAbonosCxP())) {
                VistaReportePagos reporte = new VistaReportePagos();
                instancias.setRepAbonosCxp(reporte);
                cargarReporte(reporte);
            }
        }

        if (cmbTiposReportes.getSelectedIndex() == 5) {
            if (tienePermiso(instancias.getUsuarioLog().isReporteNotasCredito())) {
                VistaReporteNotasCredito reporte = new VistaReporteNotasCredito();
                instancias.setRepNC(reporte);
                cargarReporte(reporte);
            }
        }

        if (cmbTiposReportes.getSelectedIndex() == 6) {
            if (tienePermiso(instancias.getUsuarioLog().isReporteNotasCredito())) {
                VistaReporteNotasDebito reporte = new VistaReporteNotasDebito();
                instancias.setRepND(reporte);
                cargarReporte(reporte);
            }
        }
    }

    private boolean tienePermiso(boolean permiso) {
        if (!permiso) {
            cmbTiposReportes.setSelectedIndex(0);
            metodos.msgAdvertencia(null, "No tiene permisos para esta función");
        }
        return permiso;
    }

    private void cargarReporte(JPanel reporte) {
        pnlReporte.removeAll();
        pnlReporte.setLayout(new BorderLayout());
        pnlReporte.add(reporte, BorderLayout.CENTER);
        pnlReporte.revalidate();
        pnlReporte.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbTiposReportes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlReporte;
    private javax.swing.JScrollPane scrFormulario;
    // End of variables declaration//GEN-END:variables
}
