package configuracion;

import Vista.BarraProceso.vistaBarraProceso;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class msgSalir extends javax.swing.JDialog {

    Instancias instancias;
    metodosGenerales metodos;
//    String url = "";
    Object[] datos;

    public msgSalir(java.awt.Frame parent, boolean modal, String mensaje) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);

        metodos = new metodosGenerales();
        instancias = Instancias.getInstancias();
        jLabel3.requestFocus();

        datos = instancias.getSql().getDatosMaestra();

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private ActionListener accion(final String opc, final JDialog ventana) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpEncabezados = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jLabel3 = new javax.swing.JLabel();
        lbCopia1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("¿ Desea salir ?");

        jToggleButton1.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jToggleButton1.setText("Continuar Trabajando");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setBackground(new java.awt.Color(255, 102, 102));
        jToggleButton2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setText("Salir");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/advertencia1.png"))); // NOI18N

        lbCopia1.setBackground(new java.awt.Color(204, 204, 204));
        lbCopia1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCopia1.setText("Generar copia de seguridad y salir");
        lbCopia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbCopia1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCopia1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(lbCopia1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        Object[] data = instancias.getSql().obtenerDatosUsuarioIniciado(instancias.getUsuario());
        boolean adentro = false;

        try {
            if (data[0].toString().equals("ON")) {
                adentro = true;
            }
        } catch (Exception e) {
            metodos.msgError(null, "No se reconoce la base de datos");
            metodos.msgError(null, "El programá se cerrará");
            System.exit(0);
        }

        Boolean preguntar = false;
        if ((Boolean) datos[43]) {
            preguntar = true;
        }

        if (preguntar) {

            if (metodos.msgPregunta(null, "¿Generar copia de base de datos?") == 0) {

                int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("MULTI")[0].toString());
                if (num > 1) {
                    this.dispose();
                    msgGenerarCopia copias = new msgGenerarCopia(null, rootPaneCheckingEnabled);
                    copias.setVisible(true);
                } else {
                    generarCopiaSeguridad();
                }

            } else {
                while (adentro) {
                    if (Instancias.getInstancias().getSql().usuarioActivo("OFF", "")) {
                        try {
                            Thread.sleep(500);
                        } catch (Exception ee) {
                        }
                    }
                    data = instancias.getSql().obtenerDatosUsuarioIniciado(instancias.getUsuario());
                    if (data[0].toString().equals("OFF")) {
                        adentro = false;
                    } else {
                    }
                }
                System.exit(0);
            }
        } else {
            while (adentro) {
                if (Instancias.getInstancias().getSql().usuarioActivo("OFF", "")) {
                    try {
                        Thread.sleep(500);
                    } catch (Exception ee) {
                    }
                }
                data = instancias.getSql().obtenerDatosUsuarioIniciado(instancias.getUsuario());
                if (data[0].toString().equals("OFF")) {
                    adentro = false;
                } else {
                }
            }
            System.exit(0);
        }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void lbCopia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbCopia1ActionPerformed
        int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("MULTI")[0].toString());
        if (num > 1) {
            this.dispose();
            msgGenerarCopia copias = new msgGenerarCopia(null, rootPaneCheckingEnabled);
            copias.setVisible(true);
        } else {
            this.dispose();
            generarCopiaSeguridad();
        }
    }//GEN-LAST:event_lbCopia1ActionPerformed

    public void generarCopiaSeguridad() {
        String destino = "\"" + System.getProperty("user.home") + "\\Desktop\\Copia_Seguridad_bdclick\"";
        this.dispose();

        vistaBarraProceso barra = new vistaBarraProceso();
        barra.show();

        String urlCompleto = "\"" + System.getProperty("user.dir") + "\\mariadb\\bin\"";
        String baseUtilizada = instancias.getSql().getBaseDatos();
        String sentencia = "cmd /C " + urlCompleto + "\\" + "mysqldump.exe\" -u click --password=cl1ckP4$4yMzf " + baseUtilizada + " > " + destino;

        try {
            Runtime rt = Runtime.getRuntime();
            Process p = rt.exec(new String[]{"cmd", "/C " + sentencia});
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        barra.dispose();

        Object[] data = instancias.getSql().obtenerDatosUsuarioIniciado(instancias.getUsuario());
        boolean adentro = false;

        try {
            if (data[0].toString().equals("ON")) {
                adentro = true;
            }
        } catch (Exception e) {
            System.exit(0);
        }

        while (adentro) {
            if (Instancias.getInstancias().getSql().usuarioActivo("OFF", "")) {
                try {
                    Thread.sleep(500);
                } catch (Exception ee) {
                }
            }
            data = instancias.getSql().obtenerDatosUsuarioIniciado(instancias.getUsuario());
            if (data[0].toString().equals("OFF")) {
                adentro = false;
            } else {
            }
        }

        System.exit(0);
    }

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
            java.util.logging.Logger.getLogger(msgSalir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(msgSalir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(msgSalir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(msgSalir.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                msgSalir dialog = new msgSalir(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grpEncabezados;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton lbCopia1;
    // End of variables declaration//GEN-END:variables
}
