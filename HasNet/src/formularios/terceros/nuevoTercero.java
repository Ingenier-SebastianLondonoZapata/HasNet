package formularios.terceros;

import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import Modelo.Terceros.ModeloContacto;
import Vista.Ventas.VistaFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class nuevoTercero extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private Instancias instancias;
    private String opc;
    private VistaFactura factura;

    public VistaFactura getFactura() {
        return factura;
    }

    public void setFactura(VistaFactura factura) {
        this.factura = factura;
    }

    public nuevoTercero(java.awt.Frame parent, boolean modal, metodosGenerales metodos, Instancias instancias, String opc) {
        super(parent, modal);
        initComponents();
        this.instancias = instancias;
        this.metodos = metodos;
        this.opc = opc;
        this.setLocationRelativeTo(null);

        Object[][] dep = instancias.getSql().getDepartamentos();
        cmbDepartamento.removeAllItems();
        for (int i = 0; i < dep.length; i++) {
            cmbDepartamento.addItem(dep[i][0]);
        }
        cmbDepartamento.setSelectedItem("ANTIOQUIA");
        consultarMunicipios("ANTIOQUIA");

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

        jPanel1 = new javax.swing.JPanel();
        lbNit1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lbTipo1 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        lbRazon = new javax.swing.JLabel();
        lbRazon2 = new javax.swing.JLabel();
        txtPNombre = new javax.swing.JTextField();
        txtPApellido = new javax.swing.JTextField();
        lbRazon1 = new javax.swing.JLabel();
        txtSNombre = new javax.swing.JTextField();
        lbRazon3 = new javax.swing.JLabel();
        txtSApellido = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lbTelefono1 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        lbDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lbDireccion2 = new javax.swing.JLabel();
        cmbDepartamento = new javax.swing.JComboBox();
        lbDireccion1 = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox();
        btnEliminar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lbDireccion3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar un cliente...");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        lbNit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit1.setText("NUEVO CLIENTE");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNit.setText("CC/Nit: *");

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("Nit"); // NOI18N
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
        });

        lbTipo1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbTipo1.setText("Tipo:*");

        cmbTipo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cédula de ciudadanía", "Nit", "Cédula de extranjería", "Pasaporte", "Registro civil", "Tarjeta de identidad", "Adulto sin identificación", "Menor sin identificación", "Número único de identificación" }));
        cmbTipo.setName("Tipo"); // NOI18N
        cmbTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoKeyReleased(evt);
            }
        });

        lbRazon.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbRazon.setText("P. Nombre: *");

        lbRazon2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRazon2.setText("P. Apellido: ");

        txtPNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPNombre.setName("Razón social"); // NOI18N
        txtPNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPNombreKeyReleased(evt);
            }
        });

        txtPApellido.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPApellido.setName("Razón social"); // NOI18N
        txtPApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPApellidoKeyReleased(evt);
            }
        });

        lbRazon1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRazon1.setText("S. Nombre: ");

        txtSNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSNombre.setName("Razón social"); // NOI18N
        txtSNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSNombreKeyReleased(evt);
            }
        });

        lbRazon3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbRazon3.setText("S. Apellido:");

        txtSApellido.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSApellido.setName("Razón social"); // NOI18N
        txtSApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSApellidoKeyReleased(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbTelefono.setText("Teléfono: *");

        txtTelefono.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtTelefono.setName("Teléfono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lbTelefono1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbTelefono1.setText("Celular:");

        txtCelular.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCelular.setName("Teléfono"); // NOI18N
        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCelularKeyTyped(evt);
            }
        });

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion.setText("Dirección:");

        txtDireccion.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDireccion.setName("Dirección"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        lbDireccion2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion2.setText("Depto:");

        cmbDepartamento.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbDepartamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DEPARTAMENTOS" }));
        cmbDepartamento.setName("Departamento"); // NOI18N
        cmbDepartamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDepartamentoItemStateChanged(evt);
            }
        });

        lbDireccion1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion1.setText("Ciudad:");

        cmbCiudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbCiudad.setName("Ciudad"); // NOI18N

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnEliminar.setText("CANCELAR");
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnEliminar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR  ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lbDireccion3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbDireccion3.setText("E-mail: *");

        txtEmail.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtEmail.setName("E-mail"); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbDireccion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTelefono, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDireccion3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(lbDireccion1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbCiudad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtEmail)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNit, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                    .addComponent(txtPNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbTipo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbRazon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSNombre)
                                    .addComponent(txtSApellido)
                                    .addComponent(txtCelular)))
                            .addComponent(txtDireccion))))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbTipo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbTipo))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPNombre)
                    .addComponent(lbRazon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSNombre))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbRazon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPApellido)
                        .addComponent(txtSApellido)))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTelefono)
                        .addComponent(lbTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDireccion)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail)
                    .addComponent(lbDireccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lbDireccion2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(cmbCiudad, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar))
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTipoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPNombre.requestFocus();
        }
    }//GEN-LAST:event_cmbTipoKeyReleased

    private void txtPNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSNombre.requestFocus();
        }
    }//GEN-LAST:event_txtPNombreKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCelular.requestFocus();
        }
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Object[] campos = {txtNit, txtPNombre, txtTelefono, cmbTipo,};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

            if (nodo.getId() != null) {
                metodos.msgError(null, "El Tercero ya existe");
                txtNit.requestFocus();
                return;
            }

            String consecutivo = "";
            int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("TERCERO")[0].toString());
            consecutivo = String.valueOf(num);
            for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
                consecutivo = "0" + consecutivo;
            }

            Object[] vector = {"TERC-" + consecutivo, txtPNombre.getText(), txtSNombre.getText(), txtPApellido.getText(), txtSApellido.getText(),
                txtTelefono.getText(), instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()), txtCelular.getText(),
                txtDireccion.getText(), txtNit.getText()
            };

            if (!instancias.getSql().AgregarNuevoTercero(vector)) {
                metodos.msgError(null, "Error al guardar el Tercero");
                return;
            }

            Object[] vector1 = {"TERC-" + consecutivo, txtNit.getText(), txtPNombre.getText() + " " + txtSNombre.getText() + " " + txtPApellido.getText() + " "
                + txtSApellido.getText(), txtTelefono.getText(), txtCelular.getText(), txtDireccion.getText(), cmbCiudad.getSelectedItem(), txtEmail.getText(),
                cmbDepartamento.getSelectedItem(), metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), cmbTipo.getSelectedItem().toString(),
                txtPNombre.getText() + " " + txtSNombre.getText() + " " + txtPApellido.getText() + " " + txtSApellido.getText(), txtPNombre.getText(),
                txtSNombre.getText(), txtPApellido.getText(), txtSApellido.getText(), "", "", "", false, "", big.getMoneda("0"), "", "COLOMBIA", "", "",
                "", "", "", "", "", metodos.fechaConsulta(metodosGenerales.fecha()), "", null, instancias.getTerminal(), "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", 0, metodos.fechaConsulta(metodosGenerales.fecha()),
                metodos.fechaConsulta(metodosGenerales.fecha()), false, "", "", "", "TERCERO", "", false
            };

            nodo = metodos.llenarTercero(vector1);
            if (!instancias.getSql().agregarTercero(nodo)) {
                metodos.msgError(null, "Hubo un problema al guardar el Tercero");
                return;
            }

            metodos.msgExito(null, "Tercero registrado con éxito");

            if (!instancias.getSql().aumentarConsecutivo("TERCERO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("TERCERO")[0]) + 1)) {
                metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del tercero");
            }

            System.out.println(opc);
            switch (opc) {
                case "factura":
                    instancias.getBusClientes().dispose();
                    factura.nuevoTercero(txtNit.getText());
                    this.dispose();
                    break;
                case "agenda":
                    String nombre = txtPNombre.getText() + " " + txtSNombre.getText() + " " + txtPApellido.getText() + " " + txtSApellido.getText();
                    instancias.getInfoAgenda().cargarNuevoPaciente(txtNit.getText(), nombre, txtTelefono.getText(),
                            txtCelular.getText(), "TERC-" + consecutivo);
                    this.dispose();
                    break;
                default:
                    this.dispose();
                    break;
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtDireccion.requestFocus();
        }
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtCelularKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCelularKeyTyped

    private void txtSNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSNombreKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPApellido.requestFocus();
        }
    }//GEN-LAST:event_txtSNombreKeyReleased

    private void txtPApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPApellidoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtSApellido.requestFocus();
        }
    }//GEN-LAST:event_txtPApellidoKeyReleased

    private void txtSApellidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSApellidoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtSApellidoKeyReleased

    private void cmbDepartamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDepartamentoItemStateChanged
        //Este metodo llena los municipios al seleccionar el departamento, como no se ha seleccionado ninguno al cargar sale null y presenta error.
        try {
            consultarMunicipios(cmbDepartamento.getSelectedItem().toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmbDepartamentoItemStateChanged

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTipo.requestFocus();
        }
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailKeyReleased

    public void consultarMunicipios(String departamento) {
        Object[][] municipio = instancias.getSql().getMunicipios(departamento);
        cmbCiudad.removeAllItems();
        cmbCiudad.addItem(" ");
        for (int i = 0; i < municipio.length; i++) {
            if (null != municipio[i][0]) {
                cmbCiudad.addItem(municipio[i][0]);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbCiudad;
    private javax.swing.JComboBox cmbDepartamento;
    private javax.swing.JComboBox cmbTipo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbDireccion1;
    private javax.swing.JLabel lbDireccion2;
    private javax.swing.JLabel lbDireccion3;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbRazon1;
    private javax.swing.JLabel lbRazon2;
    private javax.swing.JLabel lbRazon3;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono1;
    private javax.swing.JLabel lbTipo1;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtPApellido;
    private javax.swing.JTextField txtPNombre;
    private javax.swing.JTextField txtSApellido;
    private javax.swing.JTextField txtSNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
