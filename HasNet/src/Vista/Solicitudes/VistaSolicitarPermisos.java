package Vista.Solicitudes;

import Controlador.BarraProceso.controladorBarraProceso;
import Controlador.BarraProceso.jcThread;
import Enums.TipoDocumento;
import Modelo.Solicitudes.AccionesPermisos;
import formularios.*;
import clases.Instancias;
import clases.metodosGenerales;
import configuracion.dlgEsperandoRespuesta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class VistaSolicitarPermisos extends javax.swing.JDialog {

    private final metodosGenerales metodos;
    private final Instancias instancias;
    private final String tipoProceso;
    private jcThread barra2;

    private final AccionesPermisos accionRealizar;

    public VistaSolicitarPermisos(java.awt.Frame parent, String tipoProceso, AccionesPermisos accion, String valor, BigDecimal descuentoMaximo) {

        super(parent, true);

        initComponents();

        instancias = Instancias.getInstancias();
        metodos = new metodosGenerales();

        this.accionRealizar = accion;
        this.tipoProceso = tipoProceso;

        configurarPantalla(tipoProceso, descuentoMaximo);

        txtValor.setText(valor);

        getRootPane().registerKeyboardAction(
                accion("cerrar", this),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void configurarPantalla(String tipoProceso, BigDecimal descuentoMaximo) {

        if (TipoDocumento.ANULAR_FACTURACION.getValor().equals(tipoProceso)) {
            lbMensaje.setText("ANULACIÓN FACTURA");
            txtTipo.setText("ANULACION");
            lbTitulo.setText("DOCUMENTO A ANULAR:");
            return;
        }

        if (TipoDocumento.NOTA_CREDITO.getValor().equals(tipoProceso)) {
            lbMensaje.setText("NOTAS CRÉDITO");
            txtTipo.setText("NOTA-CREDITO");
            lbTitulo.setText("DOCUMENTO A GENERAR:");
            return;
        }

        lbMensaje.setText(obtenerMensaje(descuentoMaximo));
        txtTipo.setText(obtenerTipo());
        lbTitulo.setText(obtenerTitulo());
    }

    private String obtenerMensaje(BigDecimal descuentoMaximo) {
        if (accionRealizar.isAccionDescuento()) {
            return "DESCUENTO MÁXIMO PRODUCTO " + descuentoMaximo + "%";
        }

        if (accionRealizar.isAccionLimpiar()) {
            return obtenerMensajeLimpiar();
        }

        if (accionRealizar.isAccionBorrarProducto()) {
            return "ELIMINAR PRODUCTO";
        }

        return "";
    }

    private String obtenerMensajeLimpiar() {
        if (!TipoDocumento.MESA.getValor().equals(tipoProceso)) {
            return "LIMPIAR FACTURA";
        }

        if (instancias.getConfiguraciones().isRestaurante()) {
            return "LIMPIAR MESA";
        }

        return "LIMPIAR CONGELADA";
    }

    private String obtenerTipo() {
        if (accionRealizar.isAccionDescuento()) {
            return "DESCUENTO";
        }

        if (accionRealizar.isAccionLimpiar()) {
            return "LIMPIAR";
        }

        return "ELIMINAR";
    }

    private String obtenerTitulo() {
        if (accionRealizar.isAccionDescuento()) {
            return "DESCUENTO A APLICAR";
        }

        if (accionRealizar.isAccionLimpiar()) {
            return "DOCUMENTO A LIMPIAR";
        }

        if (accionRealizar.isAccionBorrarProducto()) {
            return "PRODUCTO A ELIMINAR";
        }

        return "";
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
        btnSolicitarPermiso = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btnSolicitarPermisoContrasenha = new javax.swing.JButton();
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

        btnSolicitarPermiso.setBackground(new java.awt.Color(0, 204, 102));
        btnSolicitarPermiso.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSolicitarPermiso.setText("SOLICITAR");
        btnSolicitarPermiso.setBorder(null);
        btnSolicitarPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarPermisoActionPerformed(evt);
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
                .addComponent(btnSolicitarPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSolicitarPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permiso del administrador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        btnSolicitarPermisoContrasenha.setBackground(new java.awt.Color(0, 204, 102));
        btnSolicitarPermisoContrasenha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        btnSolicitarPermisoContrasenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnSolicitarPermisoContrasenha.setBorder(null);
        btnSolicitarPermisoContrasenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarPermisoContrasenhaActionPerformed(evt);
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
                .addComponent(btnSolicitarPermisoContrasenha, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField1)
                    .addComponent(btnSolicitarPermisoContrasenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        txtValor.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtValor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValor.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValor)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValor))
                .addGap(5, 5, 5))
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

    private void btnSolicitarPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarPermisoActionPerformed
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
        btnSolicitarPermiso.setEnabled(false);
        txtNota.setEnabled(false);
        this.dispose();

        String estado = "PENDIENTE";
        int cant = 0;

        while (estado.equals("PENDIENTE")) {
            Object[] datos = instancias.getSql().getInformacionPermiso("PERMISO-" + consecutivo);
            estado = datos[7].toString();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
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
            }
        }

        barra2.detener(true);
        procesarEstado(estado, consecutivo);
    }//GEN-LAST:event_btnSolicitarPermisoActionPerformed

    private void btnSolicitarPermisoContrasenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarPermisoContrasenhaActionPerformed
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

            procesarAceptada(consecutivo);
            this.dispose();
        } else {
            metodos.msgError(null, "Contraseña incorrecta");
            jPasswordField1.setText("");
            jPasswordField1.requestFocus();
        }
    }//GEN-LAST:event_btnSolicitarPermisoContrasenhaActionPerformed

    private void lbMensaje1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMensaje1MouseClicked
        this.dispose();
    }//GEN-LAST:event_lbMensaje1MouseClicked

    private void procesarEstado(String estado, String consecutivo) {

        if ("ACEPTADA".equals(estado)) {
            procesarAceptada(consecutivo);
            metodos.msgExito(null, "SOLICITUD ACEPTADA");
            return;
        }

        if ("RECHAZADA".equals(estado)) {
            procesarRechazada();
            metodos.msgError(null, "SOLICITUD RECHAZADA");
        }
    }

    private void procesarAceptada(String consecutivo) {

        if (accionRealizar.isAccionDescuento()) {
            actualizarSolicitudPermiso(true, consecutivo);
            return;
        }

        switch (tipoProceso) {
            case "notaCredito":
                instancias.getNc().realizarNc();
                break;

            case "anular_facturacion":
                instancias.getReimpresion().anularFactura(txtNota.getText());
                break;

            case "borrarMesa":
                instancias.getMesa1().limpiarMesa();
                break;

            case "borrarFactura":
                instancias.getFactura().limpiar(false);
                break;

            case "borrarProductoMesa":
                instancias.getMesa1().eliminarFila();
                break;

            case "borrarProductoFactura":
                instancias.getFactura().eliminarFila();
                break;
        }
    }

    private void procesarRechazada() {

        actualizarSolicitudPermiso(false, null);

        switch (tipoProceso) {
            case "notaCredito":
                System.out.println("Nota credito rechazada.");
                break;

            case "anular_facturacion":
                System.out.println("Anulación rechazada.");
                break;

            case "borrarMesa":
                System.out.println("Solicitud rechazada.");
                break;
        }
    }

    private void actualizarSolicitudPermiso(boolean autorizado, String consecutivo) {

        String permiso = consecutivo == null ? null : "PERMISO-" + consecutivo;

        switch (tipoProceso) {

            case "pedido":
                instancias.getPedido().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getPedido().setPermisoNumero(permiso);
                }
                break;

            case "orden":
                instancias.getOrdenServicio().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getOrdenServicio().setPermisoNumero(permiso);
                }
                break;

            case "separe":
                instancias.getPlanSepare().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getPlanSepare().setPermisoNumero(permiso);
                }
                break;

            case "credito":
                instancias.getFacturaCreditos().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getFacturaCreditos().setPermisoNumero(permiso);
                }
                break;

            case "cuentaCobro":
                instancias.getCuentaCobro().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getCuentaCobro().setPermisoNumero(permiso);
                }
                break;

            case "cotizacion":
                instancias.getCotiza().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getCotiza().setPermisoNumero(permiso);
                }
                break;

            case "facturacion":
                instancias.getFactura().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getFactura().setPermisoNumero(permiso);
                }
                break;

            case "mesa":
                instancias.getMesa1().setSolicitudPermiso(autorizado);
                if (autorizado) {
                    instancias.getMesa1().setPermisoNumero(permiso);
                }
                break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSolicitarPermiso;
    private javax.swing.JButton btnSolicitarPermisoContrasenha;
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
