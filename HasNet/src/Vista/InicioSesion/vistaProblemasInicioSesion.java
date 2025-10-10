package Vista.InicioSesion;

import Controlador.Alertas.ControladorAlertas;
import clases.Instancias;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

public class vistaProblemasInicioSesion extends javax.swing.JFrame {

    private static String idUsuarioSeleccionado = "";
    private final Instancias instancias = Instancias.getInstancias();
    private final ControladorAlertas alertas = new ControladorAlertas();
    
    public vistaProblemasInicioSesion() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.cargarUsuariosLogeados();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnSalida = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        lbTitulo = new javax.swing.JLabel();
        lbTitulo1 = new javax.swing.JLabel();
        lbGato = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtNombre = new javax.swing.JTextField();
        btnCambiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TanFacil.Click");
        setBackground(new java.awt.Color(51, 51, 51));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnSalida.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        btnSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalida.setText("<");
        btnSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalidaMouseClicked(evt);
            }
        });

        tblUsuarios.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Nombre", "Estado", "Terminal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setRowHeight(24);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);
        if (tblUsuarios.getColumnModel().getColumnCount() > 0) {
            tblUsuarios.getColumnModel().getColumn(2).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(2).setMaxWidth(80);
            tblUsuarios.getColumnModel().getColumn(3).setMinWidth(110);
            tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(110);
            tblUsuarios.getColumnModel().getColumn(3).setMaxWidth(110);
        }

        lbTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Usuarios utilizando en el sistema");

        lbTitulo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo1.setText("¿ Olvidaste tu usuario o contraseña ?");

        lbGato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/InicioSesion/lineaRecuperarPassword.jpg"))); // NOI18N

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N

        btnCambiar.setBackground(new java.awt.Color(46, 204, 113));
        btnCambiar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnCambiar.setText("CAMBIAR ESTADO");
        btnCambiar.setEnabled(false);
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(lbGato, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCambiar))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lbTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbGato, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(btnCambiar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalidaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidaMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnSalidaMouseClicked

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        String estado = tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 2).toString();
        if (estado.equals("ON")) {
            txtNombre.setText(tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 1).toString());
            idUsuarioSeleccionado = tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0).toString();
            btnCambiar.setEnabled(true);
        } else {
            btnCambiar.setEnabled(false);
            txtNombre.setText(tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 1).toString());
            idUsuarioSeleccionado = "";
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        instancias.getSql().usuarioActivo("OFF", "", idUsuarioSeleccionado);
        alertas.alertSuccess("Estado actualizado");
        cargarUsuariosLogeados();
        reiniciarCampos();
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void reiniciarCampos() {
        txtNombre.setText("");
        btnCambiar.setEnabled(false);
    }
    
    private void cargarUsuariosLogeados() {
        DefaultTableModel modeloTablaUsuarios = (DefaultTableModel) tblUsuarios.getModel();
        while (tblUsuarios.getRowCount() > 0) {
            modeloTablaUsuarios.removeRow(0);
        }
        
        Object[][] listadoUsuarios = instancias.getSql().getEstadoUsuarios();
        for (Object[] usuario : listadoUsuarios) {
              if (usuario[2].equals("ON")) {
                modeloTablaUsuarios.addRow(usuario);
            }
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JLabel btnSalida;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbGato;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTitulo1;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
