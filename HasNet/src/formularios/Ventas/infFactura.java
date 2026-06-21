package formularios.Ventas;

import Vista.Ventas.VistaFactura;
import clases.Instancias;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infFactura extends javax.swing.JInternalFrame {

    private VistaFactura pnlFactura;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public VistaFactura getPnlFactura() {
        return pnlFactura;
    }

    public void setPnlFactura(VistaFactura pnlFactura) {
        this.pnlFactura = pnlFactura;
    }

    public infFactura() {
        initComponents();

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        pnlFactura = new VistaFactura("facturacion");
        pnlFactura.setSize(pnlContenedor.getSize());
        pnlContenedor.add(pnlFactura, CENTER_ALIGNMENT);
        pnlContenedor.revalidate();
        pnlContenedor.repaint();
        pnlContenedor.setVisible(true);
    }

    @Override
    public void setSelected(boolean selected) {
        try {
            super.setSelected(selected); //To change body of generated methods, choose Tools | Templates.
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infFactura.class.getName()).log(Level.SEVERE, null, ex);
        }

        int fila = pnlFactura.obtenerDocumento();
        pnlFactura.actualizarConsecutivo(fila);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrFormulario = new javax.swing.JScrollPane();
        pnlContenedor = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("Factura");

        scrFormulario.setBackground(new java.awt.Color(255, 255, 255));

        pnlContenedor.setBackground(new java.awt.Color(255, 255, 255));
        pnlContenedor.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                pnlContenedorComponentResized(evt);
            }
        });

        javax.swing.GroupLayout pnlContenedorLayout = new javax.swing.GroupLayout(pnlContenedor);
        pnlContenedor.setLayout(pnlContenedorLayout);
        pnlContenedorLayout.setHorizontalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGap(0, 822, Short.MAX_VALUE)
        );
        pnlContenedorLayout.setVerticalGroup(
            pnlContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );

        scrFormulario.setViewportView(pnlContenedor);

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

    private void pnlContenedorComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_pnlContenedorComponentResized
        pnlFactura.setSize(pnlContenedor.getSize());
    }//GEN-LAST:event_pnlContenedorComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlContenedor;
    private javax.swing.JScrollPane scrFormulario;
    // End of variables declaration//GEN-END:variables
}
