package Vista.Contactos;

import Modelo.Contactos.ModeloBodega;
import Validaciones.Contactos.SquemaBodegaContacto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class VistaBodegaContacto extends javax.swing.JDialog {

    private static final String CODIGO_AUTOMATICO = "Se asigna al guardar";

    private final SquemaBodegaContacto validaciones = new SquemaBodegaContacto();

    private boolean aceptado;
    private ModeloBodega bodega;
    private List<String> nombresRegistrados = new ArrayList<>();
    private String idSistemaContacto = "";
    private String tipoBodega = "";

    public VistaBodegaContacto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getRootPane().registerKeyboardAction(accionCerrar(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void preparar(ModeloBodega bodega, List<String> nombresRegistrados, String idSistemaContacto, String tipoBodega) {
        this.aceptado = false;
        this.bodega = bodega;
        this.nombresRegistrados = nombresRegistrados == null ? new ArrayList<String>() : nombresRegistrados;
        this.idSistemaContacto = idSistemaContacto == null ? "" : idSistemaContacto;
        this.tipoBodega = tipoBodega == null ? "" : tipoBodega;

        mostrarBodega(bodega);
        setTitle(bodega == null ? "Agregar bodega" : "Modificar bodega");

        pack();
        setLocationRelativeTo(null);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public ModeloBodega getBodega() {
        return bodega;
    }

    private ActionListener accionCerrar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        };
    }

    private void mostrarBodega(ModeloBodega datos) {
        if (datos == null) {
            txtCodigo.setText(CODIGO_AUTOMATICO);
            txtNombre.setText("");
        } else {
            txtCodigo.setText(texto(datos.getCodigo()));
            txtNombre.setText(texto(datos.getNombre()));
        }
        txtNombre.requestFocus();
    }

    private String texto(String valor) {
        return valor == null ? "" : valor;
    }

    private void guardar() {
        ModeloBodega capturada = bodega == null ? new ModeloBodega() : bodega;
        capturada.setContacto(idSistemaContacto);
        capturada.setTipoBodega(tipoBodega);
        capturada.setNombre(txtNombre.getText().trim().toUpperCase());

        if (!validaciones.validarBodega(capturada, nombresRegistrados)) {
            return;
        }

        bodega = capturada;
        aceptado = true;
        dispose();
    }

    private void cancelar() {
        aceptado = false;
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenido = new javax.swing.JPanel();
        lbTitulo = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de la bodega");
        setResizable(false);

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbTitulo.setText("Datos de la bodega");

        lbCodigo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCodigo.setText("Código:");

        txtCodigo.setEditable(false);
        txtCodigo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCodigo.setFocusable(false);

        lbNombre.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNombre.setText("Nombre:");

        txtNombre.setBackground(new java.awt.Color(255, 204, 204));
        txtNombre.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(241, 148, 138));
        btnCancelar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(46, 204, 113));
        btnAceptar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnAceptar.setText("Guardar");
        btnAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTitulo)
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(txtNombre)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbTitulo)
                .addGap(12, 12, 12)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        txtNombre.setText(txtNombre.getText().toUpperCase());

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            guardar();
        }
    }//GEN-LAST:event_txtNombreKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
