package formularios.Ventas;

import Controlador.Alertas.ControladorAlertas;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;

public class dlgRecogidaParcial extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private final String opc, simbolo;
    Instancias instancias;

    public dlgRecogidaParcial(java.awt.Frame parent, boolean modal, String opc) {
        super(parent, modal);
        initComponents();

        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();
        this.opc = opc;

        switch (opc) {
            case "caja":
                lbValor.setText("VALOR RECOGIDA");
                lbTitulo.setText("Recogida parcial");
                break;
            case "cajaBase":
                lbValor.setText("VALOR BASE");
                lbTitulo.setText("Base del día");
                break;
            default:
                break;
        }

        this.simbolo = instancias.getSimbolo();
        txtTotal.setText(this.simbolo + " 0");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        lbValor = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lbTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lbValor.setFont(new java.awt.Font("Century Gothic", 0, 28)); // NOI18N
        lbValor.setText("VALOR BASE:");

        txtTotal.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setText("0");
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(46, 204, 113));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setText("ACEPTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 178, 2));
        jButton2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton2.setText("SALIR SIN GUARDAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Base del día");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbValor)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal)))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(lbValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("Recogida Parcial");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!txtTotal.getText().equals(this.simbolo + " 0")) {
            switch (opc) {
                case "caja":
                    instancias.getSql().agregarRecogidaParcial(new Object[]{instancias.getTerminal(), instancias.getUsuario(),
                        metodos.fechaConsulta(metodosGenerales.fecha()), true},
                            new Object[]{big.getMoneda(txtTotal.getText())});
                    metodos.msgExito(null, "Recogida Parcial registrada con exito");
                    instancias.getCaja().desdeRecogida(txtTotal.getText());
                    break;
                case "cajaBase":
                    instancias.getSql().agregarBaseCuadreDeCaja(new Object[]{instancias.getTerminal(), instancias.getUsuario(),
                        metodos.fechaConsulta(metodosGenerales.fecha()), true, "", "", "", "", "", "", "", "", "", "", ""},
                            new Object[]{big.getMoneda(txtTotal.getText())});
                    metodos.msgExito(null, "Base registrada con exito");
                    instancias.getCaja().actualizar();
                    break;
            }
            this.dispose();
        } else {
            ControladorAlertas.alert("Debe ingresar un valor");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTotalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyReleased
        if (txtTotal.getText().equals("") || txtTotal.getText().equals(this.simbolo) || txtTotal.getText().equals(this.simbolo + " ")) {
            txtTotal.setText("0");
        }

        txtTotal.setText(big.setMoneda(big.getMoneda(txtTotal.getText())));
    }//GEN-LAST:event_txtTotalKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbValor;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
