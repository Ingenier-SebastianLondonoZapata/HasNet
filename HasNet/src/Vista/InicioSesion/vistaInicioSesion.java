package Vista.InicioSesion;

import Controlador.Alertas.ControladorAlertas;
import Controlador.FacturacionElectronica.controladorFacturacionElectronica;
import Controlador.InicioSesion.controladorInicioSesion;
import Modelo.Maestra.modeloConfiguracion;
import Utilidades.Constantes;
import clases.Instancias;
import clases.metodosGenerales;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class vistaInicioSesion extends javax.swing.JFrame {

    private final metodosGenerales metodos = new metodosGenerales();
    private static final Instancias instancias = Instancias.getInstancias();
    private final controladorInicioSesion controlador = new controladorInicioSesion();
    private final controladorFacturacionElectronica controladorFacturacion = new controladorFacturacionElectronica();
    private final ControladorAlertas alertas = new ControladorAlertas();
    private Object[][] registroPago;
    private static boolean datosCorrectos = false;
    private static boolean sistemaBloqueadoPorPago = false; 

    public vistaInicioSesion(boolean validarDatos) {
        initComponents();

        if (validarDatos) {
            validarPermisosCliente();
        }

        this.setLocationRelativeTo(null);
        txtPassword.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbGato = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        lbProblemas = new javax.swing.JLabel();
        btnSalida = new javax.swing.JLabel();
        btnSalida1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HS.NET");
        setBackground(new java.awt.Color(51, 51, 51));
        setIconImage(new ImageIcon(getClass().getResource("/imagenes/icono.png")).getImage());
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lbTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("La mejor aplicación para el control de tu negocio");

        lbGato.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/InicioSesion/calculadora.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtUsuario.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText("ADMIN");
        txtUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Contraseña");

        txtPassword.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(113, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(txtUsuario))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuario)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbProblemas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbProblemas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProblemas.setText("¿Tienes problemas para iniciar sesion? Click aquí.");
        lbProblemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbProblemasMouseClicked(evt);
            }
        });

        btnSalida.setFont(new java.awt.Font("Arial Black", 0, 24)); // NOI18N
        btnSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalida.setText("X");
        btnSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalidaMouseClicked(evt);
            }
        });

        btnSalida1.setFont(new java.awt.Font("Arial Black", 0, 30)); // NOI18N
        btnSalida1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSalida1.setText("<");
        btnSalida1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSalida1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbGato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbProblemas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnSalida1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalida1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbGato, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProblemas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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
        System.exit(0);
    }//GEN-LAST:event_btnSalidaMouseClicked

    private void lbProblemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProblemasMouseClicked
        vistaProblemasInicioSesion problemasInicio = new vistaProblemasInicioSesion();
        problemasInicio.setVisible(true);
    }//GEN-LAST:event_lbProblemasMouseClicked

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            boolean datosCorrectos = controlador.validarIniciarSesion(txtUsuario.getText(), txtPassword.getText());
            if (datosCorrectos) {
                this.dispose();
            }
        }
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void btnSalida1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalida1MouseClicked
        this.dispose();
        instancias.getSql().obtenerConexion(Constantes.BASE_DATOS_PRINCIPAL, true);
        vistaInicioSesionMultiempresa vistaInicio = new vistaInicioSesionMultiempresa();
        vistaInicio.setLocationRelativeTo(null);
        vistaInicio.setVisible(true);
    }//GEN-LAST:event_btnSalida1MouseClicked

    private void validarPermisosCliente() {
        String identificadorCliente = Constantes.leerIdentificadorCliente();
        if (identificadorCliente.isEmpty()) {
            alertas.alertFail("El sistema no se pudo cargar");
            System.exit(0);
        }

        modeloConfiguracion modelo = instancias.getSqlPagos().obtenerConfiguracionCliente(identificadorCliente);
        if (null != modelo.getNit() || null != modelo.getNombre()) {
            if (!instancias.getSql().modificarConfiguracion(modelo)) {
                alertas.alertFail("Error al actualizar la configuracion");
            } else {
                instancias.setEsPruebasFacturacionElectronica(modelo.isPruebasFacturacion());
                if (modelo.isPruebasFacturacion()) {
                    instancias.setCanalFacturacion(Constantes.CANAL_PRUEBAS);
                } else {
                    instancias.setCanalFacturacion(Constantes.CANAL_PRODUCCION);
                }

                if (validacionesPagos(identificadorCliente)) {
                    datosCorrectos = true;
                }

                if (modelo.isFacturaElectronica() && !sistemaBloqueadoPorPago) {
                    generarToken(registroPago[0][1].toString(), registroPago[0][8].toString());
                }
            }
        } else {
            alertas.bigAlert("Tenemos problemas al consultar la información de la empresa, si el problema persiste comunicarse directamente con el proveedor.");
            System.exit(0);
        }
    }

    private boolean validacionesPagos(String identificadorCliente) {
        this.registroPago = instancias.getSqlPagos().obtenerRegistroPago(identificadorCliente);
        String fechaActual = metodos.fecha(instancias.getSqlPagos().obtenerFecha());
        String fechaLimite = registroPago[0][3].toString();
        int anterioridad = Integer.parseInt(registroPago[0][4].toString());
        int despues = Integer.parseInt(registroPago[0][5].toString());
        long diferenciaDias = metodos.restarFecha(fechaLimite, fechaActual) * -1;

        if (diferenciaDias < 0) {
            if ((diferenciaDias * -1) > despues) {
                new vistaBloqueoPrograma().setVisible(true);
                sistemaBloqueadoPorPago = true;
                return false;
            } else {
                vistaCuotaPendiente vistaBloqueo = new vistaCuotaPendiente("Recuerde que la fecha limite para el pago fue el " + fechaLimite
                        + " más " + despues + " días de gabela para que pueda realizar el pago.");
                vistaBloqueo.setVisible(true);
                return false;
            }

        } else if (diferenciaDias <= anterioridad) {
            vistaCuotaPendiente vistaBloqueo = new vistaCuotaPendiente("Recuerde que la fecha limite para el pago es el " + fechaLimite);
            vistaBloqueo.setVisible(true);
            return false;
        }

        return true;
    }

    private boolean generarToken(String nitEmisor, String tipoDocumento) {
        boolean tokenGenerado = false;

        try {
            tokenGenerado = controladorFacturacion.generarToken(nitEmisor, tipoDocumento);
            if (!tokenGenerado) {
                System.out.println("Hubo un error al generar el token de facturación electrónica");
            }
        } catch (Exception ex) {
            Logger.getLogger(controladorInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tokenGenerado;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    ServerSocket SERVER_SOCKETE = new ServerSocket(1779);
                } catch (IOException x) {
                    ControladorAlertas.alertFail("EL SISTEMA YA SE ENCUENTRA EN EJECUCIÓN");
                    System.exit(0);
                }

                vistaInicioSesion vistaInicio = new vistaInicioSesion(true);

                if (datosCorrectos) {
                    vistaInicioSesionMultiempresa vistaInicioMultiEmpresas = new vistaInicioSesionMultiempresa();
                    vistaInicioMultiEmpresas.setLocationRelativeTo(null);
                    vistaInicioMultiEmpresas.setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnSalida;
    private javax.swing.JLabel btnSalida1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbGato;
    private javax.swing.JLabel lbProblemas;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
