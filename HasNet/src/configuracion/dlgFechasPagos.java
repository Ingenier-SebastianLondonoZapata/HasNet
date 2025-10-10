package configuracion;

import Modelo.Maestra.modeloConfiguracion;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;

public class dlgFechasPagos extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private Instancias instancias;
   
    public dlgFechasPagos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        metodos = new metodosGenerales();
        instancias = Instancias.getInstancias();

        cargarTabla();

        modeloConfiguracion nodoConf = new modeloConfiguracion();
        txtIdentificadorNombre.setText(nodoConf.getNombre());
        txtIdentificadorNit.setText(nodoConf.getNit());
        txtIdFechaPago.setVisible(false);

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

        factura = new javax.swing.ButtonGroup();
        grupoRegimen = new javax.swing.ButtonGroup();
        grupoCongeladas = new javax.swing.ButtonGroup();
        grupoMedico = new javax.swing.ButtonGroup();
        grupoVeterinaria = new javax.swing.ButtonGroup();
        grupoParqueadero = new javax.swing.ButtonGroup();
        grupoOrdenServ = new javax.swing.ButtonGroup();
        grupoCreditos = new javax.swing.ButtonGroup();
        grupoSepare = new javax.swing.ButtonGroup();
        grupoPedido = new javax.swing.ButtonGroup();
        grupoAgenda = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFechas = new javax.swing.JTable();
        txtIdFechaPago = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        rdFechaHabilitada = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        lbTelefono31 = new javax.swing.JLabel();
        txtIdentificadorNit = new javax.swing.JTextField();
        lbTelefono32 = new javax.swing.JLabel();
        txtIdentificadorNombre = new javax.swing.JTextField();
        lbTelefono33 = new javax.swing.JLabel();
        txtCodigoFinal = new javax.swing.JTextField();
        btnGuardar1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar pagos");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tblFechas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblFechas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha", "Habilitada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFechas.setComponentPopupMenu(jPopupMenu1);
        tblFechas.setInheritsPopupMenu(true);
        tblFechas.setRowHeight(24);
        tblFechas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFechasMouseClicked(evt);
            }
        });
        tblFechas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblFechasKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblFechas);
        if (tblFechas.getColumnModel().getColumnCount() > 0) {
            tblFechas.getColumnModel().getColumn(2).setMinWidth(70);
            tblFechas.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblFechas.getColumnModel().getColumn(2).setMaxWidth(70);
        }

        txtIdFechaPago.setEditable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cuotas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 10))); // NOI18N

        rdFechaHabilitada.setBackground(new java.awt.Color(255, 255, 255));
        rdFechaHabilitada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rdFechaHabilitada.setText("Habilitar");
        rdFechaHabilitada.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdFechaHabilitada, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdFechaHabilitada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Identificador de la empresa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        lbTelefono31.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbTelefono31.setText("Nit:");

        txtIdentificadorNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdentificadorNit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdentificadorNit.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdentificadorNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificadorNitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificadorNitKeyTyped(evt);
            }
        });

        lbTelefono32.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono32.setText("Nombre:");

        txtIdentificadorNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtIdentificadorNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdentificadorNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtIdentificadorNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentificadorNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentificadorNombreKeyTyped(evt);
            }
        });

        lbTelefono33.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono33.setText("Codigo:");

        txtCodigoFinal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigoFinal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoFinal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCodigoFinal.setEnabled(false);
        txtCodigoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoFinalKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono32, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                    .addComponent(lbTelefono31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdentificadorNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(txtCodigoFinal)
                    .addComponent(txtIdentificadorNit, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdentificadorNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdentificadorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelefono33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar1.setText("GUARDAR");
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar1)
                .addGap(174, 174, 174))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtIdFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(txtIdFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (btnGuardar.getText().equals("GUARDAR")) {

            for (int i = 0; i < tblFechas.getRowCount(); i++) {

                if (!instancias.getSql().agregarFechasBloqueo(tblFechas.getValueAt(i, 1).toString())) {
                    metodos.msgError(null, "Hubo un error al ingresar la fecha");
                    return;
                }

            }
            metodos.msgExito(null, "Guardado con exito");
            try {
                Thread.sleep(300);
            } catch (Exception e) {
            }

            cargarTabla();
        } else {

            if (!instancias.getSql().modificarFechasBloqueo(txtIdFechaPago.getText(), rdFechaHabilitada.isSelected())) {
                metodos.msgError(null, "No se habilito la fecha");
                return;
            }
            metodos.msgExito(null, "Registro modificado");
            try {
                Thread.sleep(300);
            } catch (Exception e) {
            }
            cargarTabla();
        }

        this.dispose();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed

        if (tblFechas.getSelectedRow() > -1) {
            int fila = tblFechas.getSelectedRow();
            String codigo = (String) tblFechas.getValueAt(fila, 0);
            if (!instancias.getSql().eliminarFechaBloqueo(codigo)) {
                metodos.msgError(null, "Hubo un error al eliminar la fecha");
                return;
            } else {
                metodos.msgExito(null, "Fecha Eliminada");
            }
            DefaultTableModel modelo = (DefaultTableModel) tblFechas.getModel();
            modelo.removeRow(fila);
        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void tblFechasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblFechasKeyReleased

    }//GEN-LAST:event_tblFechasKeyReleased

    private void tblFechasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFechasMouseClicked
        if (evt.getClickCount() == 2) {
            txtIdFechaPago.setText(tblFechas.getValueAt(tblFechas.getSelectedRow(), 0).toString());
//            dtFecha.setSelectedDate(metodos.haciaDate2(tblFechas.getValueAt(tblFechas.getSelectedRow(), 1).toString()));
            rdFechaHabilitada.setSelected((Boolean) tblFechas.getValueAt(tblFechas.getSelectedRow(), 2));
            btnGuardar.setText("MODIFICAR");
        }
    }//GEN-LAST:event_tblFechasMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        txtIdFechaPago.setText("");
        rdFechaHabilitada.setSelected(false);
//        dtFecha.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        btnGuardar.setText("GUARDAR");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtIdentificadorNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIdentificadorNombre.requestFocus();
        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtIdentificadorNitKeyReleased

    private void txtIdentificadorNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorNitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificadorNitKeyTyped

    private void txtIdentificadorNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

        } else {
            calcularCodigo();
        }
    }//GEN-LAST:event_txtIdentificadorNombreKeyReleased

    private void txtIdentificadorNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentificadorNombreKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificadorNombreKeyTyped

    private void txtCodigoFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoFinalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoFinalKeyTyped

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (!instancias.getSql().modificarConfiguraciones(txtIdentificadorNit.getText(), txtIdentificadorNombre.getText())) {
            metodos.msgError(null, "Error al guardar los datos");
            return;
        } else {
            try {
                if (txtIdentificadorNit.isEnabled()) {
                    //agregarCliente(txtCodigoFinal.getText(), txtIdentificadorNit.getText(), txtIdentificadorNombre.getText());
//                    if (!instancias.getSql1().agregarRegistro(, , )) {
//                        metodos.msgError(null, "Error al registrar cliente");
//                        return;
//                    }
                }

                try {
                    String fechaCorte = metodos.sumarFecha(metodosGenerales.fecha(), 30);
                    fechaCorte = encriptarFecha(fechaCorte);
                    instancias.getSql().agregarFechasBloqueo(fechaCorte);
                } catch (Exception ex) {
                    Logger.getLogger(dlgFechasPagos.class.getName()).log(Level.SEVERE, null, ex);
                }

                metodos.msgExito(null, "Datos guardados con exito");
                this.dispose();
            } catch (Exception e) {
                metodos.msgError(null, "Necesita tener acceso a internet");
            }
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    /*public String agregarCliente(String codigo, String nit, String nombre) {
        String concat = "NuevoUsuario/-/" + codigo + "/-/" + nit + "/-/" + nombre;
        String resultadoFinal = "";
        try {
            String postUrl = "https://ujvgudsfe4.execute-api.us-east-2.amazonaws.com/prod/suspen-prod";
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity("\"" + concat + "\"");
            post.setEntity(postingString);

            post.addHeader(HTTP.CONTENT_TYPE, "application/json");
            HttpResponse response = httpClient.execute(post);
            InputStream resultado = response.getEntity().getContent();
            resultadoFinal = IOUtils.toString(resultado);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(dlgFechasPagos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(dlgFechasPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultadoFinal;
    }*/

    public static String encriptarFecha(String texto) {
        String secretKey = "yI4z%jIMndKd3N%bj#%f"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public void calcularCodigo() {
        String hashEncriptado = txtIdentificadorNit.getText() + "--" + txtIdentificadorNombre.getText();
        try {
            hashEncriptado = Encriptar(hashEncriptado, "yI4z%jIMndKd3N%bj#%f");
        } catch (Exception ex) {
            Logger.getLogger(dlgFechasPagos.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtCodigoFinal.setText(hashEncriptado);
    }

    public static String Encriptar(String valor, String keyPrivate) throws Exception {
        String resultado = null;
        //Se obtienen los bytes de la clave privada   
        byte[] keyBytes = keyPrivate.getBytes();
        Key key = new SecretKeySpec(keyBytes, 0, keyBytes.length, "HmacMD5");
        Mac mac = null;

        //Se obtiene el algoritmo de encriptacion   
        try {
            mac = Mac.getInstance("HmacMD5");
            mac.init(key);
        } catch (InvalidKeyException e) {
            throw new Exception(e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(e.getMessage(), e);
        }      //Se encripta el valor enviado   

        byte[] valorEncriptado = mac.doFinal(valor.getBytes());

        //Cadena con los digitos correspondientes en hexadecimal   
        String digitosHexadecimales = "0123456789abcdef";
        StringBuilder stringBuilder = new StringBuilder(valorEncriptado.length * 2);

        for (int cx = 0; cx < valorEncriptado.length; cx++) {
            int hn = ((int) (valorEncriptado[cx]) & 0x00ff) / 16;
            int ln = ((int) (valorEncriptado[cx]) & 0x000f);
            stringBuilder.append(digitosHexadecimales.charAt(hn));
            stringBuilder.append(digitosHexadecimales.charAt(ln));
        }

        resultado = stringBuilder.toString();
        return resultado;
    }

//    public void calcularCuotasCredito() {
//        if (metodos.fecha(metodos.desdeDate(dtFecha.getCurrent())).equals(metodosGenerales.fecha())) {
//            metodos.msgError(null, "Seleccione la fecha de pago");
//            return;
//        }
//        for (int i = 0; i < tblFechas.getRowCount(); i++) {
//            String fecha = tblFechas.getValueAt(i, 1).toString();
//            String fechaDos = metodos.desdeDate(dtFecha.getCurrent());
//            String fecha3 = metodos.fecha(fechaDos);
//            if (fecha.equals(fecha3)) {
//                metodos.msgError(null, "Esta fecha ya esta registrada");
//                return;
//            }
//        }
//
//        DefaultTableModel modeloCredito;
//
//        BigDecimal cuotas;
////                valor = big.getMoneda("0"), , intereses, total, cuotaCapital, cuotaInteres,
////                cuotaTotal, porcentaje, saldo, tem, factor;
//
//        int j = tblFechas.getRowCount();
//        int tipoPlazo = 0;
//
//        if (cmbTipoPlazo.getSelectedItem().equals("Días")) {
//            tipoPlazo = 1;
//        } else if (cmbTipoPlazo.getSelectedItem().equals("Semanal")) {
//            tipoPlazo = 7;
//        } else if (cmbTipoPlazo.getSelectedItem().equals("Quincenal")) {
//            tipoPlazo = 14;
//        } else if (cmbTipoPlazo.getSelectedItem().equals("Mensual")) {
//            tipoPlazo = 1;
//        }
//
//        modeloCredito = (DefaultTableModel) tblFechas.getModel();
//
//        for (int i = 0; i < j; i++) {
//            modeloCredito.removeRow(0);
//        }
//
//        String fecha = metodos.fecha(metodos.desdeDate(dtFecha.getCurrent()));
//
//        cuotas = big.getMoneda(txtCuotas.getText());
//
//        j = cuotas.intValueExact();
//
//        for (int i = 0; i < j; i++) {
//            if (i == 0) {
//                modeloCredito.addRow(new Object[]{i, fecha, false});
//            } else {
//                if (cmbTipoPlazo.getSelectedItem().equals("Mensual")) {
//                    modeloCredito.addRow(new Object[]{i, metodos.sumarMeses(fecha, tipoPlazo), false});
//
//                    if (i > 0) {
//                        fecha = metodos.sumarMeses(fecha, tipoPlazo);
//                    }
//                } else {
//                    modeloCredito.addRow(new Object[]{i, metodos.sumarFecha(fecha, tipoPlazo), false});
//
//                    if (i > 0) {
//                        fecha = metodos.sumarFecha(fecha, tipoPlazo);
//                    }
//                }
//            }
//        }
//    }
    public void cargarTabla() {
        tblFechas.setModel(instancias.getSql().getFechasBloqueadas());
        tblFechas.getColumnModel().getColumn(0).setMinWidth(50);
        tblFechas.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblFechas.getColumnModel().getColumn(0).setMaxWidth(50);
        tblFechas.getColumnModel().getColumn(2).setMinWidth(80);
        tblFechas.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblFechas.getColumnModel().getColumn(2).setMaxWidth(80);

        for (int i = 0; i < tblFechas.getRowCount(); i++) {
//            tblFechas.setValueAt(metodos.fecha(tblFechas.getValueAt(i, 1).toString()), i, 1);
            tblFechas.setValueAt(tblFechas.getValueAt(i, 1).toString(), i, 1);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.ButtonGroup factura;
    private javax.swing.ButtonGroup grupoAgenda;
    private javax.swing.ButtonGroup grupoCongeladas;
    private javax.swing.ButtonGroup grupoCreditos;
    private javax.swing.ButtonGroup grupoMedico;
    private javax.swing.ButtonGroup grupoOrdenServ;
    private javax.swing.ButtonGroup grupoParqueadero;
    private javax.swing.ButtonGroup grupoPedido;
    private javax.swing.ButtonGroup grupoRegimen;
    private javax.swing.ButtonGroup grupoSepare;
    private javax.swing.ButtonGroup grupoVeterinaria;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTelefono31;
    private javax.swing.JLabel lbTelefono32;
    private javax.swing.JLabel lbTelefono33;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JCheckBox rdFechaHabilitada;
    private javax.swing.JTable tblFechas;
    private javax.swing.JTextField txtCodigoFinal;
    private javax.swing.JTextField txtIdFechaPago;
    private javax.swing.JTextField txtIdentificadorNit;
    private javax.swing.JTextField txtIdentificadorNombre;
    // End of variables declaration//GEN-END:variables
}
