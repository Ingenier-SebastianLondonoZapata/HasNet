package Vista.InicioSesion;

import Controlador.Alertas.ControladorAlertas;
import Controlador.InicioSesion.controladorInicioSesion;
import Modelo.Multiempresa.Empresa;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class vistaInicioSesion extends javax.swing.JFrame {

    private static final Color COLOR_BORDE = new Color(189, 195, 199);
    private static final Color COLOR_FOCO = new Color(93, 173, 226);
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(110, 110, 110);
    private static final char CARACTER_OCULTO = '•';
    private static final String PREFERENCIA_EMPRESA = "ultimaEmpresa";

    private static ServerSocket instanciaUnica;

    private final controladorInicioSesion controlador = new controladorInicioSesion();
    private final Preferences preferencias = Preferences.userNodeForPackage(vistaInicioSesion.class);
    private Point inicioArrastre;

    public vistaInicioSesion() {
        initComponents();
        Utilidades.IconoAplicacion.aplicar(this);
        aplicarEstilo();
        cargarEmpresas();
        pack();
        this.setLocationRelativeTo(null);
        txtUsuario.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondo = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        lbSubtitulo = new javax.swing.JLabel();
        pnlEmpresa = new javax.swing.JPanel();
        lbEmpresa = new javax.swing.JLabel();
        cmbEmpresa = new javax.swing.JComboBox<>();
        lbUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lbContrasena = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        chkMostrar = new javax.swing.JCheckBox();
        lbBloqMayus = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        lbProblemas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HasNet - Iniciar sesión");
        setUndecorated(true);
        setResizable(false);

        pnlFondo.setBackground(new java.awt.Color(255, 255, 255));

        btnCerrar.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        btnCerrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCerrar.setText("×");
        btnCerrar.setToolTipText("Salir");
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
        });

        lbLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lbTitulo.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("Bienvenido a HasNet");

        lbSubtitulo.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbSubtitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbSubtitulo.setText("Ingrese sus credenciales para continuar");

        pnlEmpresa.setOpaque(false);
        pnlEmpresa.setLayout(new java.awt.BorderLayout(0, 4));

        lbEmpresa.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbEmpresa.setText("Empresa");
        pnlEmpresa.add(lbEmpresa, java.awt.BorderLayout.NORTH);

        cmbEmpresa.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        cmbEmpresa.setPreferredSize(new java.awt.Dimension(340, 38));
        pnlEmpresa.add(cmbEmpresa, java.awt.BorderLayout.CENTER);

        lbUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbUsuario.setText("Usuario");

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

        lbContrasena.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbContrasena.setText("Contraseña");

        txtPassword.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        chkMostrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        chkMostrar.setText("Mostrar contraseña");
        chkMostrar.setOpaque(false);
        chkMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMostrarActionPerformed(evt);
            }
        });

        lbBloqMayus.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lbBloqMayus.setForeground(new java.awt.Color(192, 57, 43));
        lbBloqMayus.setText("Bloq Mayús activado");

        btnIngresar.setBackground(new java.awt.Color(46, 204, 113));
        btnIngresar.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        lbProblemas.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        lbProblemas.setForeground(new java.awt.Color(93, 173, 226));
        lbProblemas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProblemas.setText("¿Problemas para iniciar sesión?");
        lbProblemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbProblemasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFondoLayout = new javax.swing.GroupLayout(pnlFondo);
        pnlFondo.setLayout(pnlFondoLayout);
        pnlFondoLayout.setHorizontalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFondoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlFondoLayout.createSequentialGroup()
                        .addComponent(chkMostrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbBloqMayus))
                    .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbProblemas, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        pnlFondoLayout.setVerticalGroup(
            pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondoLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbTitulo)
                .addGap(4, 4, 4)
                .addComponent(lbSubtitulo)
                .addGap(24, 24, 24)
                .addComponent(pnlEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lbUsuario)
                .addGap(4, 4, 4)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbContrasena)
                .addGap(4, 4, 4)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(pnlFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkMostrar)
                    .addComponent(lbBloqMayus))
                .addGap(20, 20, 20)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(lbProblemas)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void lbProblemasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProblemasMouseClicked
        vistaProblemasInicioSesion problemasInicio = new vistaProblemasInicioSesion();
        problemasInicio.setVisible(true);
    }//GEN-LAST:event_lbProblemasMouseClicked

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtPassword.requestFocusInWindow();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ingresar();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        actualizarAvisoMayusculas();
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void chkMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMostrarActionPerformed
        txtPassword.setEchoChar(chkMostrar.isSelected() ? (char) 0 : CARACTER_OCULTO);
        txtPassword.requestFocusInWindow();
    }//GEN-LAST:event_chkMostrarActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        ingresar();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void ingresar() {
        if (!btnIngresar.isEnabled()) {
            return;
        }
        btnIngresar.setEnabled(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        char[] contrasena = txtPassword.getPassword();
        try {
            Empresa empresa = pnlEmpresa.isVisible() ? (Empresa) cmbEmpresa.getSelectedItem() : null;
            if (controlador.validarIniciarSesion(txtUsuario.getText(), contrasena, empresa)) {
                recordarEmpresa(empresa);
                txtPassword.setText("");
                dispose();
                return;
            }
            txtPassword.setText("");
            txtPassword.requestFocusInWindow();
        } finally {
            Arrays.fill(contrasena, ' ');
            setCursor(Cursor.getDefaultCursor());
            btnIngresar.setEnabled(true);
        }
    }

    private void cargarEmpresas() {
        List<Empresa> empresas = controlador.empresasDisponibles();
        DefaultComboBoxModel<Empresa> modelo = new DefaultComboBoxModel<>();
        String ultima = preferencias.get(PREFERENCIA_EMPRESA, "");
        Empresa seleccionada = null;
        for (Empresa empresa : empresas) {
            modelo.addElement(empresa);
            if (empresa.getId().equals(ultima)) {
                seleccionada = empresa;
            }
        }
        cmbEmpresa.setModel(modelo);
        if (seleccionada != null) {
            cmbEmpresa.setSelectedItem(seleccionada);
        }
        pnlEmpresa.setVisible(controlador.multiempresaHabilitada() && empresas.size() > 1);
    }

    private void recordarEmpresa(Empresa empresa) {
        try {
            preferencias.put(PREFERENCIA_EMPRESA, empresa == null ? Empresa.ID_PRINCIPAL : empresa.getId());
        } catch (IllegalStateException | SecurityException ex) {
            java.util.logging.Logger.getLogger(vistaInicioSesion.class.getName()).log(java.util.logging.Level.FINE, "No se pudo recordar la empresa", ex);
        }
    }

    private void aplicarEstilo() {
        pnlFondo.setBorder(BorderFactory.createLineBorder(COLOR_BORDE));
        pnlEmpresa.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        lbSubtitulo.setForeground(COLOR_TEXTO_SECUNDARIO);
        btnCerrar.setForeground(COLOR_TEXTO_SECUNDARIO);
        btnCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbProblemas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setOpaque(true);
        txtPassword.setEchoChar(CARACTER_OCULTO);
        lbBloqMayus.setVisible(false);
        estiloCampo(txtUsuario);
        estiloCampo(txtPassword);

        java.net.URL imagen = getClass().getResource("/imagenes/InicioSesion/calculadora.png");
        if (imagen != null) {
            lbLogo.setIcon(new ImageIcon(new ImageIcon(imagen).getImage().getScaledInstance(140, 140, Image.SCALE_SMOOTH)));
        }

        MouseAdapter arrastre = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inicioArrastre = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (inicioArrastre != null) {
                    Point actual = getLocation();
                    setLocation(actual.x + e.getX() - inicioArrastre.x, actual.y + e.getY() - inicioArrastre.y);
                }
            }
        };
        pnlFondo.addMouseListener(arrastre);
        pnlFondo.addMouseMotionListener(arrastre);
    }

    private void estiloCampo(JComponent campo) {
        Border relleno = BorderFactory.createEmptyBorder(4, 10, 4, 10);
        campo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE), relleno));
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_FOCO, 2),
                        BorderFactory.createEmptyBorder(3, 9, 3, 9)));
                if (campo == txtPassword) {
                    actualizarAvisoMayusculas();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                campo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(COLOR_BORDE), relleno));
            }
        });
    }

    private void actualizarAvisoMayusculas() {
        try {
            lbBloqMayus.setVisible(Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK));
        } catch (UnsupportedOperationException ex) {
            lbBloqMayus.setVisible(false);
        }
    }

    public static void mostrar() {
        vistaInicioSesion vista = new vistaInicioSesion();
        vista.setVisible(true);
    }

    public static void iniciarAplicacion() {
        controladorInicioSesion controlador = new controladorInicioSesion();
        switch (controlador.validarPermisosCliente()) {
            case ERROR:
                ControladorAlertas.alertFail(controlador.getMensajeArranque());
                System.exit(0);
                break;
            case BLOQUEADO:
                new vistaBloqueoPrograma().setVisible(true);
                break;
            case CUOTA_PENDIENTE:
                new vistaCuotaPendiente(controlador.getMensajeArranque()).setVisible(true);
                break;
            default:
                mostrar();
        }
    }

    public static void main(String args[]) {
        java.util.Locale.setDefault(new java.util.Locale("es", "CO"));

        java.awt.EventQueue.invokeLater(() -> {
            try {
                instanciaUnica = new ServerSocket(1779);
            } catch (IOException x) {
                ControladorAlertas.alertFail("EL SISTEMA YA SE ENCUENTRA EN EJECUCIÓN");
                System.exit(0);
            }
            iniciarAplicacion();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCerrar;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JCheckBox chkMostrar;
    private javax.swing.JComboBox<Modelo.Multiempresa.Empresa> cmbEmpresa;
    private javax.swing.JLabel lbBloqMayus;
    private javax.swing.JLabel lbContrasena;
    private javax.swing.JLabel lbEmpresa;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lbProblemas;
    private javax.swing.JLabel lbSubtitulo;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbUsuario;
    private javax.swing.JPanel pnlEmpresa;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
