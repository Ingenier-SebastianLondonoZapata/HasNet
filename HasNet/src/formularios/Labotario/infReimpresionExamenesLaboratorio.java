package formularios.Labotario;

import formularios.Medico.*;
import clases.Instancias;
import clases.cambiarColorTabla;
import clases.metodosGenerales;
import javax.swing.table.DefaultTableModel;

public class infReimpresionExamenesLaboratorio extends javax.swing.JFrame {

    public infReimpresionExamenesLaboratorio() {
        initComponents();
        modelo = (DefaultTableModel) jTable1.getModel();
        this.setLocationRelativeTo(null);
        jTable1.setDefaultRenderer(Object.class, new cambiarColorTabla(4, 0));
    }

    Instancias instancias;
    metodosGenerales metodos = new metodosGenerales();
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
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField2.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("Paciente:");

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
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
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                case "acidoUrico":
                    this.dispose();
                    instancias.getAcidoUrico().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "antiestreptolisina":
                    this.dispose();
                    instancias.getAntiestreptosilina().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "cannabinoides":
                    this.dispose();
                    instancias.getCannabinoides().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "cocaina":
                    this.dispose();
                    instancias.getCocaina().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "coprologico":
                    this.dispose();
                    instancias.getCoprologico().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "coprograma":
                    this.dispose();
                    instancias.getCoprograma().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "colesterol":
                    this.dispose();
                    instancias.getColesterol().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "citoquimico":
                    this.dispose();
                    instancias.getCitoquimico().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "hemoglobina":
                    this.dispose();
                    instancias.getHemoglobina().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "acidos":
                    this.dispose();
                    instancias.getAcidos().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "sangreOculta":
                    this.dispose();
                    instancias.getSangreOculta().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "tiempos":
                    this.dispose();
                    instancias.getTiempos().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "directo":
                    this.dispose();
                    instancias.getDirectos().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "creatinina":
                    this.dispose();
                    instancias.getCreatinina().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "hemograma":
                    this.dispose();
                    instancias.getHemograma().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "hemoclasificacion":
                    this.dispose();
                    instancias.getHemoclasificacion().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "serologia":
                    this.dispose();
                    instancias.getSerologia().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "koh":
                    this.dispose();
                    instancias.getKoh().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "proteina":
                    this.dispose();
                    instancias.getProteina().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "glicemia":
                    this.dispose();
                    instancias.getGlicemia().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "perfil":
                    this.dispose();
                    instancias.getPerfil().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
                    break;
                case "embarazo":
                    this.dispose();
                    instancias.getEmbarazo().reimprimir(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
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
            java.util.logging.Logger.getLogger(infReimpresionExamenesLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(infReimpresionExamenesLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(infReimpresionExamenesLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(infReimpresionExamenesLaboratorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new infReimpresionExamenesLaboratorio().setVisible(true);
            }
        });
    }

    public void cargarRegistros(String paciente, String examen, Instancias instancias) {

        this.instancias = instancias;

        jTable1.setToolTipText(examen);
        if (nombre != null) {
            jTextField2.setText(nombre);
        } else {
            jTextField2.setText(instancias.getSql().getDatosTercero(paciente).getNombre());
        }

        Object[][] datos = null;
        switch (examen) {
            case "acidoUrico":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "acidoUrico", "IdAcido");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "antiestreptolisina":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "antiestreptolisina", "IdAntiestreptolisina");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "cannabinoides":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "cannabinoides", "IdCannabinoides");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "cocaina":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "cocaina", "IdCocaina");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "coprologico":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "coprologico", "IdCoprologico");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "coprograma":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "coprograma", "IdCoprograma");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "colesterol":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "colesterol", "IdColesterol");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "citoquimico":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "citoquimico", "IdCitoquimico");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "hemoglobina":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "hemoglobina", "IdHemoglobina");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "acidos":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "acidos", "IdAcidos");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "sangreOculta":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "sangreOculta", "IdSangre");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "tiempos":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "tiempos", "IdTiempo");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "directo":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "directo", "IdDirecto");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "creatinina":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "creatinina", "IdCreatinina");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "hemograma":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "hemograma", "IdHemograma");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "hemoclasificacion":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "hemoclasificacion", "IdHemoclasificacion");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "serologia":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "serologia", "IdSerologia");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "koh":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "koh", "IdKoh");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "proteina":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "proteina", "IdProteina");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "glicemia":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "glicemia", "IdGlicemia");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "perfil":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "perfil", "IdPerfil");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
            case "embarazo":
                datos = instancias.getSql().getRegistrosExamenes(paciente, "embarazo", "IdEmbarazo");
                modelo = new DefaultTableModel(new Object[]{"ID", "Num.Orden", "Cedula", "Fecha"}, 0) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                jTable1.setModel(modelo);
                for (Object[] dato : datos) {
                    modelo.addRow(dato);
                }
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    jTable1.setValueAt(metodos.fecha(jTable1.getValueAt(i, 3).toString()), i, 3);
                }
                this.show();
                break;
        }

//        for (Object[] dato : datos) {
//            modelo.addRow(dato);
//        }
//
//        jTable1.setToolTipText(examen);
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
