package formularios.Veterinario;

import clases.Instancias;
import clases.metodosGenerales;
import javax.swing.table.DefaultTableModel;

public class infReimpresiones extends javax.swing.JFrame {

    Instancias instancias;
    metodosGenerales metodos;
    DefaultTableModel modelo;
    private Integer opc = null;
    private String nombre = null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public infReimpresiones() {
        initComponents();
        modelo = (DefaultTableModel) jTable1.getModel();
        this.setLocationRelativeTo(null);
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

    public metodosGenerales getMetodos() {
        return metodos;
    }

    public void setMetodos(metodosGenerales metodos) {
        this.metodos = metodos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reimpresiones...");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel2.setText("Paciente:");

        jTable1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Fecha", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(24);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jButton1.setText("Seleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (jTable1.getSelectedRow() >= 0) {
            switch (jTable1.getToolTipText()) {

                case "cita":
                    this.dispose();
//                    instancias.getAgenda().cargarCita(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                    instancias.getPeluqueria().cargarCita(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
                    break;
                case "formulaMedicaVeterinaria":
                    this.dispose();
                    instancias.getFormulaMedicaVeterinaria().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "ayudaDiagnosticaVeterinaria":
                    this.dispose();
                    instancias.getAyudaDiagnosticoVeterinaria().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "nota":
                    this.dispose();
                    instancias.getNotaMedicaVeterinaria().imprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "quirurgica":
                    this.dispose();
                    instancias.getQuirurgica().imprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "remision":
                    this.dispose();
                    instancias.getRemisionVeterinaria().imprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
            }
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            jButton1ActionPerformed(null);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(infReimpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infReimpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infReimpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infReimpresiones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infReimpresiones().setVisible(true);
            }
        });
    }

    public void cargarRegistros(String paciente, String examen, Instancias instancias) {

        this.instancias = instancias;
        if (nombre != null) {
            jTextField2.setText(nombre);
        } else {
            jTextField2.setText(instancias.getSql().getDatosTercero(paciente).getNombre());
        }

        Object[][] datos = null;

        switch (examen) {

            case "cita":

                datos = instancias.getSql().getRegistrosCitas(paciente);

                modelo = new DefaultTableModel(new Object[]{"Fecha", "Hora", "A Cargo"}, 0) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };

                jTable1.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(new Object[]{metodos.fecha(dato[0].toString()), dato[1], dato[2]});
                }
                this.show();
                jTable1.setToolTipText(examen);

                return;

            case "formulaMedicaVeterinaria":
                datos = instancias.getSql().getRegistrosExamenes(instancias.getSql().getDatosHistoria(paciente).getId(), "formulaMedicaVeterinaria");
                break;
            case "ayudaDiagnosticaVeterinaria":
                datos = instancias.getSql().getRegistrosExamenes(instancias.getSql().getDatosHistoria(paciente).getId(), "ayudaDiagnosticaVeterinaria");

                modelo = new DefaultTableModel(new Object[]{"ID", "Fecha"}, 0) {
                    boolean[] canEdit = new boolean[]{
                        false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                jTable1.setModel(modelo);
                break;
            case "nota":
                datos = instancias.getSql().getNotasEnfermeria(paciente);
                jTable1.setModel(new DefaultTableModel(new Object[]{"Codigo", "Fecha"}, 0));
                modelo = (DefaultTableModel) jTable1.getModel();
                break;
            case "quirurgica":
                datos = instancias.getSql().getHistoriasQuirurgicas(paciente);
                modelo = new DefaultTableModel(new Object[]{"ID", "Fecha", "Observaciones"}, 0) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                jTable1.setModel(modelo);

                jTable1.getColumnModel().getColumn(0).setMinWidth(25);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
                jTable1.getColumnModel().getColumn(1).setMinWidth(100);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(1).setMaxWidth(100);
                break;
            case "remision":
                datos = instancias.getSql().getRemisiones(paciente);
                modelo = new DefaultTableModel(new Object[]{"ID", "Fecha", "Observaciones"}, 0) {
                    boolean[] canEdit = new boolean[]{
                        false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                };
                jTable1.setModel(modelo);
                jTable1.getColumnModel().getColumn(0).setMinWidth(25);
                jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
                jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
                jTable1.getColumnModel().getColumn(1).setMinWidth(100);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(1).setMaxWidth(100);
                break;
        }

        for (Object[] dato : datos) {
//            dato[1] = metodos.fecha(dato[1].toString());
            modelo.addRow(dato);
        }

        jTable1.setToolTipText(examen);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
