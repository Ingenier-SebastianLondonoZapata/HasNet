package formularios.Medico;

import clases.Instancias;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class infReimpresionExamenes extends javax.swing.JFrame {

    public infReimpresionExamenes() {
        initComponents();
        modelo = (DefaultTableModel) tblTabla.getModel();
        this.setLocationRelativeTo(null);
        tblTabla.setDefaultRenderer(Object.class, new cambiarColorTabla(4, 0));

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private ActionListener accion(final String opc, final JFrame ventana) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "cerrar":
                        ventana.dispose();
                        break;
                }
            }
        };
        return a;
    }

    Instancias instancias;
    metodosGenerales metodos;
    DefaultTableModel modelo;
    private Integer opc = null;
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        tblTabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jLabel2.setText("Paciente:");

        tblTabla.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTabla.setRowHeight(24);
        tblTabla.getTableHeader().setReorderingAllowed(false);
        tblTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTabla);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        jButton1.setText("SELECCIONAR");
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tblTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTablaMouseClicked
        if (evt.getClickCount() == 2) {
            jButton1ActionPerformed(null);
        }
    }//GEN-LAST:event_tblTablaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (tblTabla.getSelectedRow() >= 0) {
            switch (tblTabla.getToolTipText()) {
                case "formulaMedica":
                    this.dispose();
                    instancias.getFormulaMedica().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "remision":
                    this.dispose();
                    instancias.getRemision().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "contraremision":
                    this.dispose();
                    instancias.getContraremision().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "ayudaDiagnostica":
                    this.dispose();
                    instancias.getAyudaDiagnostica().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "incapacidad":
                    this.dispose();
                    instancias.getIncapacidad().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "certificadoOys":
                    this.dispose();
                    instancias.getCertificadoMedicoOys().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "certificadoEstudiante":
                    this.dispose();
                    instancias.getCertificadoMedicoEstudiante().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
                case "certificadoMedico":
                    this.dispose();
                    instancias.getCertificadoMedico().reimprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
//                case "biopsia":
//                    this.dispose();
//                    instancias.getBiopsia().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "ecografia":
//                    this.dispose();
//                    instancias.getEcografiaMamaria().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "colpo":
//                    this.dispose();
//                    instancias.getColposcopia().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "dispositivo":
//                    this.dispose();
//                    instancias.getDispositivo().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "conizacion":
//                    this.dispose();
//                    instancias.getConizacion().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "resultado":
//                    this.dispose();
//                    instancias.getResultado().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "autorizacion":
//                    this.dispose();
//                    instancias.getAutorizacionSirvicios().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
//                case "autorizacionAnular":
//                    this.dispose();
//                    instancias.getAutorizacionSirvicios().anular(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
//                    break;
                case "orden":
                    this.dispose();
                    if (opc == null) {
                        instancias.getOrdenMedica().cargarOrdenServicio(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    } else {
                        instancias.getHistoriaC().cargarOrdenServicio(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    }
                    break;
                case "ordenListado":
                    this.dispose();
                    instancias.getHistoriaC().cargarPacienteOrden(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString(), tblTabla.getValueAt(tblTabla.getSelectedRow(), 4).toString());
                    break;
//                case "cita":
//                    this.dispose();
//                    instancias.getAgenda().cargarCita(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString(), jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString());
//                    break;
                case "nota":
                    this.dispose();
                    instancias.getNotaEnfermeria().imprimir(tblTabla.getValueAt(tblTabla.getSelectedRow(), 0).toString());
                    break;
            }
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(infReimpresionExamenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infReimpresionExamenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infReimpresionExamenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infReimpresionExamenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infReimpresionExamenes().setVisible(true);
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

            case "remision":
                datos = instancias.getSql().getRegistrosRemision(paciente);

                modelo = new DefaultTableModel(new Object[]{"ID", "Fecha", "Tipo", "Especialidad"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }

                this.show();
                tblTabla.setToolTipText(examen);
                return;

            case "formulaMedica":
                datos = instancias.getSql().getRegistrosExamenesHistoria(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "formulaMedicaBuscadorReimpresion");
                break;
            case "ayudaDiagnostica":
                datos = instancias.getSql().getRegistrosExamenesHistoria(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "ayudaDiagnosticaBuscadorReimpresion");
//                modelo = new DefaultTableModel(new Object[]{"Id", "Fecha"}, 0);
//                jTable1.setModel(modelo);
//                this.show();
//                jTable1.setToolTipText(examen);
                break;
            case "incapacidad":
                datos = instancias.getSql().getRegistrosExamenesHistoria(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "incapacidadBuscadorReimpresion");
                break;
            case "nota":
                datos = instancias.getSql().getNotasEnfermeria(paciente);
                modelo = new DefaultTableModel(new Object[]{"Codigo", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblTabla.setModel(modelo);
                break;
//            case "biopsia":
//                datos = instancias.getSql().getRegistrosExamens(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "Biopsia");
//                break;
//            case "ecografia":
//                datos = instancias.getSql().getRegistrosExamens2(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "EcografiaMamaria");
//                break;
//            case "colpo":
//                datos = instancias.getSql().getRegistrosExamens(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "Colposcopia");
//                break;
//            case "dispositivo":
//                datos = instancias.getSql().getRegistrosExamens(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "Dispositivo");
//                break;
//            case "conizacion":
//                datos = instancias.getSql().getRegistrosExamens(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "Conizacion");
//                break;
//            case "resultado":
//                datos = instancias.getSql().getRegistrosExamens(instancias.getSql().getDatosHistoriaClinica(paciente).getId(), "Resultado");
//                break;
//            case "autorizacion":
//                datos = instancias.getSql().getRegistrosExamens4(paciente, "AutorizacionServicios");
//                break;
//            case "autorizacionAnular":
//                datos = instancias.getSql().getRegistrosExamens4(paciente, "AutorizacionServicios");
//                break;
            case "certificadoOys":
                datos = instancias.getSql().getRegistrosCertificados(paciente, "conCertificadoMedicoOys");
                modelo = new DefaultTableModel(new Object[]{"Certificado", "Paciente"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                this.show();
                tblTabla.setToolTipText(examen);
                return;
            case "certificadoEstudiante":
                datos = instancias.getSql().getRegistrosCertificados(paciente, "conCertificadoMedicoEstudiante");

                modelo = new DefaultTableModel(new Object[]{"Certificado", "Paciente"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                this.show();
                tblTabla.setToolTipText(examen);
                return;
            case "certificadoMedico":
                datos = instancias.getSql().getRegistrosCertificados(paciente, "conCertificadoMedico");

                modelo = new DefaultTableModel(new Object[]{"Certificado", "Paciente"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                this.show();
                tblTabla.setToolTipText(examen);
                return;

            case "orden":
                datos = instancias.getSql().getRegistrosOrden(paciente);
                System.out.println(datos.length);
                if (datos.length == 0) {
                    this.dispose();
                } else {
                    modelo = new DefaultTableModel(datos, new Object[]{"Orden", "Consecutivo", "Fecha"});
                    tblTabla.setModel(modelo);
//                    for (int i = 0; i < jTable1.getRowCount(); i++) {
//                        jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 2).toString()), i, 2);
//                    }

                    this.show();
                    tblTabla.setToolTipText(examen);
                    return;
                }
                break;

            case "ordenListado":
                jLabel2.setText("Medico:");
                jTextField2.setText(paciente);

                System.out.println("a. " + paciente);

                datos = instancias.getSql().getRegistrosListadoOrden(paciente);

                modelo = new DefaultTableModel(new Object[]{"Orden", "Consecutivo", "Fecha", "Hora", "Paciente", "Nombre", "Estado"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(new Object[]{dato[0], dato[1], metodos.fecha(dato[2].toString()), dato[3], dato[4], dato[5], dato[6]});
                }

                tblTabla.getColumnModel().getColumn(0).setMinWidth(40);
                tblTabla.getColumnModel().getColumn(0).setPreferredWidth(40);
                tblTabla.getColumnModel().getColumn(0).setMaxWidth(60);
                tblTabla.getColumnModel().getColumn(1).setMinWidth(100);
                tblTabla.getColumnModel().getColumn(1).setPreferredWidth(100);
                tblTabla.getColumnModel().getColumn(1).setMaxWidth(100);
                tblTabla.getColumnModel().getColumn(2).setMinWidth(100);
                tblTabla.getColumnModel().getColumn(2).setPreferredWidth(100);
                tblTabla.getColumnModel().getColumn(2).setMaxWidth(100);
                tblTabla.getColumnModel().getColumn(3).setMinWidth(60);
                tblTabla.getColumnModel().getColumn(3).setPreferredWidth(60);
                tblTabla.getColumnModel().getColumn(3).setMaxWidth(60);
                tblTabla.getColumnModel().getColumn(4).setMinWidth(0);
                tblTabla.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblTabla.getColumnModel().getColumn(4).setMaxWidth(0);
                tblTabla.getColumnModel().getColumn(6).setMinWidth(120);
                tblTabla.getColumnModel().getColumn(6).setPreferredWidth(120);
                tblTabla.getColumnModel().getColumn(6).setMaxWidth(120);

                this.show();
                tblTabla.setToolTipText(examen);
                return;

            case "contraremision":

                jTextField2.setText(paciente);
                datos = instancias.getSql().getRegistrosContraremision(paciente);

                modelo = new DefaultTableModel(new Object[]{"ID", "Fecha", "Hallazgos"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }

                this.show();
                tblTabla.setToolTipText(examen);
                return;

            case "cita":

                datos = instancias.getSql().getRegistrosCitas(paciente);

                modelo = new DefaultTableModel(new Object[]{"Fecha", "Hora", "Medico"}, 0);
                tblTabla.setModel(modelo);

                for (Object[] dato : datos) {
                    modelo.addRow(new Object[]{dato[0].toString().substring(0, 11), dato[1], dato[2]});
                }
                this.show();
                tblTabla.setToolTipText(examen);
                return;
        }

        for (Object[] dato : datos) {
            modelo.addRow(dato);
        }

        tblTabla.setToolTipText(examen);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblTabla;
    // End of variables declaration//GEN-END:variables
}
