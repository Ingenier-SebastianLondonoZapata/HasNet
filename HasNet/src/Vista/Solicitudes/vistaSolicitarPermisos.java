package Vista.Solicitudes;

import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import formularios.*;
import clases.Instancias;
import clases.metodosGenerales;
import Vista.Ventas.VistaFactura;
import configuracion.dlgEsperandoRespuesta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileSystemView;

public class vistaSolicitarPermisos extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private Instancias instancias;
    private String opc;
    jcThread barra2;

    public vistaSolicitarPermisos(java.awt.Frame parent, boolean modal, String mensaje, String tipo, String valor, String lugar) {
        super(parent, modal);

        initComponents();
        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();

        lbMensaje.setText(mensaje);
        txtTipo.setText(tipo);
        txtValor.setText(valor);
        opc = lugar;

        if (opc.equals("anulacion")) {
            lbTitulo.setText("DOCUMENTO A ANULAR:");
        } else if (opc.equals("acceso")) {
            lbTitulo.setText("MÓDULO A ENTRAR:");
        } else if (opc.equals("borrarMesa")) {
            lbTitulo.setText("DOCUMENTO A BORRAR:");
        }

        this.getRootPane().registerKeyboardAction(accion("cerrar", this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void muestraContenido(String archivo, String consecutivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);

        String validacion1 = "KG8KH/GbXbA=";
        String validacion2 = "2782763659899507458";
        String validacion3 = "2.0136721508259097";

        Boolean val1 = false, val2 = false, val3 = false;

        while ((cadena = b.readLine()) != null) {
//            System.out.println(cadena);
            if (cadena.equals(validacion1)) {
                val1 = true;
            }

            if (cadena.equals(validacion2)) {
                val2 = true;
            }

            if (cadena.equals(validacion3)) {
                val3 = true;
            }
        }

        if (val1 && val2 && val3) {
            Instancias.getInstancias().getSql().cambiarEstadoGeneral("DISPOSITIVO-USB", consecutivo, " bdPermisos");
        } else {
            Instancias.getInstancias().getSql().cambiarEstadoGeneral("RECHAZADA", consecutivo, " bdPermisos");
        }

        b.close();
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

        jPanel5 = new javax.swing.JPanel();
        lbMensaje = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSolicitar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnSolicitar2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        lbTitulo = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lbMensaje1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONFIGURACIONES");
        setUndecorated(true);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lbMensaje.setFont(new java.awt.Font("Century Gothic", 1, 28)); // NOI18N
        lbMensaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMensaje.setText("#mensaje");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Solicitar permiso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        btnSolicitar.setBackground(new java.awt.Color(0, 204, 102));
        btnSolicitar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSolicitar.setText("SOLICITAR");
        btnSolicitar.setBorder(null);
        btnSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jButton1.setText("CANCELAR");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(5);
        txtNota.setToolTipText("Nota");
        txtNota.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(txtNota);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permiso del administrador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        btnSolicitar2.setBackground(new java.awt.Color(0, 204, 102));
        btnSolicitar2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSolicitar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnSolicitar2.setBorder(null);
        btnSolicitar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitar2ActionPerformed(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSolicitar2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField1)
                    .addComponent(btnSolicitar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel3.setText("TIPO DE SOLICITUD:");

        txtTipo.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtTipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTipo.setEnabled(false);

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbTitulo.setText("VALOR A SOLICITAR:");

        txtValor.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValor.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lbTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValor)
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTipo)
                        .addComponent(txtValor)
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbMensaje1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbMensaje1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMensaje1.setText("X");
        lbMensaje1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMensaje1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbMensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lbMensaje1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lbMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        if (txtNota.getText().equalsIgnoreCase("") || txtNota.getText().equalsIgnoreCase(" ")) {
            metodos.msgError(null, "Debe ingresar la nota");
            txtNota.requestFocus();
            return;
        }

        controladorBarraProceso controladorBarra = new controladorBarraProceso();
        esperandoRespuesta barra = new esperandoRespuesta(controladorBarra, Instancias.getInstancias(), "ESPERANDO RESPUESTA");
        barra.show();
        barra2 = controladorBarra.getBarra();

        String consecutivo = instancias.getSql().getNumConsecutivo("PERMISO")[0].toString();
        if (!instancias.getSql().agregarPermiso("PERMISO-" + consecutivo, txtTipo.getText(), txtValor.getText(),
                txtNota.getText(), metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora(), "PENDIENTE", instancias.getUsuario())) {
            metodos.msgError(null, "Hubo un error al ingresar el permiso");
            return;
        }

        instancias.getSql().aumentarConsecutivo("PERMISO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PERMISO")[0]) + 1);
        btnSolicitar.setEnabled(false);
        txtNota.setEnabled(false);
        this.dispose();

        String estado = "PENDIENTE";
        int cant = 0;
        int validador = 0;

        while (estado.equals("PENDIENTE")) {
            Object[] datos = instancias.getSql().getInformacionPermiso("PERMISO-" + consecutivo);
            estado = datos[7].toString();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

            cant = cant + 1;
            if (cant % 7 == 0) {
                barra2.detener(true);

                dlgEsperandoRespuesta esperando = new dlgEsperandoRespuesta(null, true, "PERMISO-" + consecutivo);
                esperando.setVisible(true);

                if (esperando.respuesta()) {
                    controladorBarra = new controladorBarraProceso();
                    barra = new esperandoRespuesta(controladorBarra, Instancias.getInstancias(), "ESPERANDO RESPUESTA");
                    barra.show();
                    barra2 = controladorBarra.getBarra();
                } else {
                    instancias.getSql().cambiarEstadoGeneral("CANCELADA", "PERMISO-" + consecutivo, " bdPermisos");
                }

//                if (metodos.msgPregunta(null, "¿Desea seguir esperando?") != 0) {
//                 
//                } else {
//                
//                }
            }

            File unidades[] = File.listRoots();

            for (int i = 0; i < unidades.length; i++) {
                System.out.println(unidades[i]);

                if (FileSystemView.getFileSystemView().getSystemDisplayName(unidades[i]).contains("PERMISO ADM")) {
                    try {
                        muestraContenido(unidades[i] + "/DESBLOQ/password.txt", "PERMISO-" + consecutivo);
                    } catch (IOException ex) {
                        Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (validador > 0) {
                        if (unidades.length > validador) {
                            barra2.detener(true);

                            if (!FileSystemView.getFileSystemView().getSystemDisplayName(unidades[unidades.length - 1]).contains("PERMISO ADM")) {
                                metodos.msgError(null, "El dispositivo insertado no es válido");
                            }

                            controladorBarra = new controladorBarraProceso();
                            barra = new esperandoRespuesta(controladorBarra, Instancias.getInstancias(), "ESPERANDO RESPUESTA");
                            barra.show();
                            barra2 = controladorBarra.getBarra();
                        }
                    }
                }

                validador = unidades.length;
            }
        }

        barra2.detener(true);

        if (estado.equals("ACEPTADA") || estado.equals("DISPOSITIVO-USB")) {

            switch (opc) {
                case "pedido":
                    instancias.getPedido().setSolicitudPermiso(true);
                    instancias.getPedido().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "orden":
                    instancias.getOrdenServicio().setSolicitudPermiso(true);
                    instancias.getOrdenServicio().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "separe":
                    instancias.getPlanSepare().setSolicitudPermiso(true);
                    instancias.getPlanSepare().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "credito":
                    instancias.getFacturaCreditos().setSolicitudPermiso(true);
                    instancias.getFacturaCreditos().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "cuentaCobro":
                    instancias.getCuentaCobro().setSolicitudPermiso(true);
                    instancias.getCuentaCobro().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "cotizacion":
                    instancias.getCotiza().setSolicitudPermiso(true);
                    instancias.getCotiza().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "facturacion":
                    instancias.getFactura().setSolicitudPermiso(true);
                    instancias.getFactura().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "mesa":
                    instancias.getMesa1().setSolicitudPermiso(true);
                    instancias.getMesa1().setPermisoNumero("PERMISO-" + consecutivo);
                    break;
                case "notaCredito":
                    instancias.getNc().realizarNc();
                    return;
                case "anulacion":
                    instancias.getAnula().anularFactura(txtNota.getText());
                    return;
                case "borrarMesa":
                    instancias.getMesa1().limpiar(true, "SI");
            }

            metodos.msgExito(null, "SOLICITUD ACEPTADA");
        } else if (estado.equals("RECHAZADA")) {
            switch (opc) {
                case "pedido":
                    instancias.getPedido().setSolicitudPermiso(false);
                    break;
                case "orden":
                    instancias.getOrdenServicio().setSolicitudPermiso(false);
                    break;
                case "separe":
                    instancias.getPlanSepare().setSolicitudPermiso(false);
                    break;
                case "credito":
                    instancias.getFacturaCreditos().setSolicitudPermiso(false);
                    break;
                case "cuentaCobro":
                    instancias.getOrdenServicio().setSolicitudPermiso(false);
                    break;
                case "cotizacion":
                    instancias.getCotiza().setSolicitudPermiso(false);
                    break;
                case "facturacion":
                    instancias.getFactura().setSolicitudPermiso(false);
                    break;
                case "mesa":
                    instancias.getMesa1().setSolicitudPermiso(false);
                    break;
                case "notaCredito":
                    System.out.println("Nota credito rechazada.");
                    break;
                case "anulacion":
                    System.out.println("Anulacion rechazada.");
                    break;
                case "borrarMesa":
                    System.out.println("Solicitud rechazada.");
                    break;
            }

            metodos.msgError(null, "SOLICITUD RECHAZADA");
        }
    }//GEN-LAST:event_btnSolicitarActionPerformed

    private void btnSolicitar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitar2ActionPerformed
        if (txtNota.getText().equalsIgnoreCase("") || txtNota.getText().equalsIgnoreCase(" ")) {
            metodos.msgError(null, "Debe ingresar la nota");
            txtNota.requestFocus();
            return;
        }

        if (instancias.getSegundaClave().equals(jPasswordField1.getText())) {

            String consecutivo = instancias.getSql().getNumConsecutivo("PERMISO")[0].toString();
            if (!instancias.getSql().agregarPermiso("PERMISO-" + consecutivo, txtTipo.getText(), txtValor.getText(),
                    txtNota.getText(), metodos.fechaConsulta(metodosGenerales.fecha()), metodosGenerales.hora(), "AUTO-CONFIRMADO", instancias.getUsuario())) {
                metodos.msgError(null, "Hubo un error al ingresar la medida");
                return;
            }
            instancias.getSql().aumentarConsecutivo("PERMISO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PERMISO")[0]) + 1);

            switch (opc) {
                case "pedido":
                    instancias.getPedido().setSolicitudPermiso(true);
                    instancias.getPedido().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "orden":
                    instancias.getOrdenServicio().setSolicitudPermiso(true);
                    instancias.getOrdenServicio().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "separe":
                    instancias.getPlanSepare().setSolicitudPermiso(true);
                    instancias.getPlanSepare().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "credito":
                    instancias.getFacturaCreditos().setSolicitudPermiso(true);
                    instancias.getFacturaCreditos().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "cuentaCobro":
                    instancias.getCuentaCobro().setSolicitudPermiso(true);
                    instancias.getCuentaCobro().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "cotizacion":
                    instancias.getCotiza().setSolicitudPermiso(true);
                    instancias.getCotiza().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "facturacion":
                    instancias.getFactura().setSolicitudPermiso(true);
                    instancias.getFactura().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "mesa":
                    instancias.getMesa1().setSolicitudPermiso(true);
                    instancias.getMesa1().setPermisoNumero("PERMISO-" + consecutivo);
                    this.dispose();
                    break;
                case "notaCredito":
                    this.dispose();
                    instancias.getNc().realizarNc();
                    break;
                case "anulacion":
                    this.dispose();
                    instancias.getAnula().anularFactura(txtNota.getText());
                    break;
                case "copiaSeguridad":
                    this.dispose();
                    try {
                        String origen = System.getProperty("user.dir") + "\\bdClick.accdb";
                        System.out.println(origen);
                        String destino = metodos.obtenerRuta2(null, "bdClick");
                        metodosGenerales.copiarArchivo(origen, destino);
                        metodos.msgExito(null, "Copia de seguridad registrada con exito");
                    } catch (Exception e) {
                        metodos.msgError(null, "Hubo un error al guardar la copia de seguridad, llamar a soporte tecnico");
                        System.out.println(e);
                    }
                    break;
            }
        } else {
            metodos.msgError(null, "Contraseña incorrecta");
            jPasswordField1.setText("");
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_btnSolicitar2ActionPerformed

    private void lbMensaje1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMensaje1MouseClicked
        this.dispose();
    }//GEN-LAST:event_lbMensaje1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolicitar;
    private javax.swing.JButton btnSolicitar2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbMensaje;
    private javax.swing.JLabel lbMensaje1;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
