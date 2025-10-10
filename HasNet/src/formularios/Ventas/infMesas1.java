package formularios.Ventas;

import clases.Instancias;
import Utilidades.BaseDatos.SQL;
import clases.big;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

public class infMesas1 extends javax.swing.JInternalFrame {

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    DefaultTableModel modelo;

    public infMesas1() {

        initComponents();
        tblMesas.setDefaultRenderer(Object.class, new cambiarColorTabla(13, 0));

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        instancias = Instancias.getInstancias();

        cargarEspacios();
        cargarRegistros();
    }

    @Override
    public void setSelected(boolean selected) {
        try {
            super.setSelected(selected); //To change body of generated methods, choose Tools | Templates.
        } catch (PropertyVetoException ex) {
            Logger.getLogger(infMesas1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnOcultar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setTitle("Factura");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnOcultar.setBackground(new java.awt.Color(255, 255, 255));
        btnOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ESTAERA.jpg"))); // NOI18N
        btnOcultar.setToolTipText("");
        btnOcultar.setBorder(null);
        btnOcultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOcultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 961, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        tblMesas.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMesas.setRowHeight(100);
        tblMesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMesasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblMesas);
        if (tblMesas.getColumnModel().getColumnCount() > 0) {
            tblMesas.getColumnModel().getColumn(0).setMinWidth(240);
            tblMesas.getColumnModel().getColumn(0).setPreferredWidth(240);
            tblMesas.getColumnModel().getColumn(0).setMaxWidth(240);
            tblMesas.getColumnModel().getColumn(1).setMinWidth(240);
            tblMesas.getColumnModel().getColumn(1).setPreferredWidth(240);
            tblMesas.getColumnModel().getColumn(1).setMaxWidth(240);
            tblMesas.getColumnModel().getColumn(2).setMinWidth(240);
            tblMesas.getColumnModel().getColumn(2).setPreferredWidth(240);
            tblMesas.getColumnModel().getColumn(2).setMaxWidth(240);
            tblMesas.getColumnModel().getColumn(3).setMinWidth(240);
            tblMesas.getColumnModel().getColumn(3).setPreferredWidth(240);
            tblMesas.getColumnModel().getColumn(3).setMaxWidth(240);
            tblMesas.getColumnModel().getColumn(4).setMinWidth(240);
            tblMesas.getColumnModel().getColumn(4).setPreferredWidth(240);
            tblMesas.getColumnModel().getColumn(4).setMaxWidth(240);
        }

        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar pequeño.png"))); // NOI18N
        jButton1.setText("Actualizar");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1202, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblMesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMesasMouseClicked
        if (evt.getClickCount() > 1) {
            String valor = tblMesas.getValueAt(tblMesas.getSelectedRow(), tblMesas.getSelectedColumn()).toString();
            String tipo = "CONG";

            if (valor.length() > 34) {
                instancias.getMenu().ocultarMenu("preparacion");
                instancias.setTitulo(valor.split("<br>")[0].substring(6, valor.split("<br>")[0].length()).replace(": ", "-"));
                instancias.getMesa().setSelected(true);
                instancias.getMesa().getPnlFactura().
                        cargarCongelada(valor.split("<br>")[0].substring(6, valor.split("<br>")[0].length()).replace(": ", "-"), tipo,
                                valor.split("<br>")[0].substring(6, valor.split("<br>")[0].length()).replace(": ", "-"));
            } else if (valor.length() < 34 && valor.length() > 1) {
                instancias.getMenu().ocultarMenu("preparacion");
                instancias.setTitulo(valor.substring(20, valor.length()));
                instancias.getMesa().setSelected(true);
                instancias.getMesa().getPnlFactura().cargarCongelada("", tipo, valor);
            } else if (valor.equals("")) {
                metodos.msgError(this, "¡Seleccione una congelada válida!");
                return;
            }
        }
    }//GEN-LAST:event_tblMesasMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cargarRegistros();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOcultarActionPerformed
        instancias.getMenu().ocultarMenu("preparacion");
        instancias.getMenu().cambiarTitulo("DOMICILIO");
        instancias.getMesa().setSelected(true);
        instancias.getMesa().getPnlFactura().cargarCongelada("", "DOMICILIO", "DOMICILIO");
    }//GEN-LAST:event_btnOcultarActionPerformed

    public void cargarEspacios() {
        DefaultTableModel modelo = (DefaultTableModel) tblMesas.getModel();
        int posicion = 1;
        for (int i = 0; i < tblMesas.getRowCount(); i++) {
            for (int j = 0; j < tblMesas.getColumnCount(); j++) {
                modelo.setValueAt("                    CONG: " + posicion, i, j);
                posicion = posicion + 1;
            }
        }
    }

    public void cargarRegistros() {

        cargarEspacios();

        Object[][] congeladas = instancias.getSql().getDatosCongelada();

        if (congeladas.length > 0) {

            for (int i = 0; i < congeladas.length; i++) {
                String lugar = congeladas[i][3].toString();

                for (int k = 0; k < tblMesas.getRowCount(); k++) {
                    for (int s = 0; s < tblMesas.getColumnCount(); s++) {

                        String lugarTabla = tblMesas.getValueAt(k, s).toString();

                        if (lugarTabla.contains("DOMICILIO")) {
                            lugarTabla = tblMesas.getValueAt(k, s).toString().replace(": ", "-");
                        } else {
                            lugarTabla = tblMesas.getValueAt(k, s).toString().replace(": ", "-");
                            lugarTabla = lugarTabla.substring(20, lugarTabla.length());
                        }

                        if (lugarTabla.equals(lugar)) {
                            String telefono = "", observaciones = "";

                            try {
                                observaciones = congeladas[i][6].toString();
                            } catch (Exception e) {
                                observaciones = "";
                            }

                            try {
                                telefono = congeladas[i][5].toString();
                            } catch (Exception e) {
                                telefono = "";
                            }

                            BigDecimal total = big.getBigDecimal(congeladas[i][2]);
                            if (lugar.split("-")[0].equals("DOMICILIO")) {
                                tblMesas.setValueAt("<html>" + lugar + "<br> TEL: " + telefono + "<br> TOTAL: " + big.setMonedaExacta(total)
                                        + "<br> OBV: " + observaciones + "</html> ", k, s);
                            } else if (lugar.split("-")[0].equals("MESA") || lugar.split("-")[0].equals("CONG")) {
                                tblMesas.setValueAt("<html>" + lugar.replace("-", ": ") + "<br> TOTAL: " + big.setMonedaExacta(total)
                                        + "<br>" + congeladas[i][4] + "<br> OBV: " + observaciones + "</html> ", k, s);
                            }
                        }
                    }
                }
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOcultar;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JTable tblMesas;
    // End of variables declaration//GEN-END:variables
}
