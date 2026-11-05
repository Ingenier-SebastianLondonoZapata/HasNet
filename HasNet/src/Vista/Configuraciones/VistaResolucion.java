package Vista.Configuraciones;

import Modelo.Maestra.ModeloResolucion;
import Utilidades.Fechas;
import Validaciones.Configuraciones.squemaResoluciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class VistaResolucion extends javax.swing.JDialog {

    private final squemaResoluciones validaciones = new squemaResoluciones();

    private boolean aceptado;
    private ModeloResolucion resolucion;

    public VistaResolucion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getRootPane().registerKeyboardAction(accionCerrar(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void preparar(ModeloResolucion resolucion, boolean facturaElectronica) {
        this.aceptado = false;
        this.resolucion = resolucion;

        lbDisenho.setVisible(facturaElectronica);
        txtDisenho.setVisible(facturaElectronica);

        mostrarResolucion(resolucion);
        setTitle(resolucion == null ? "Agregar resolución" : "Modificar resolución");
        lbTitulo.setText(resolucion == null ? "Nueva resolución" : "Modificar resolución");

        pack();
        setLocationRelativeTo(null);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public ModeloResolucion getResolucion() {
        return resolucion;
    }

    private ActionListener accionCerrar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        };
    }

    private void mostrarResolucion(ModeloResolucion datos) {
        if (datos == null) {
            txtDescripcion.setText("");
            cmbTipoComprobante.setSelectedIndex(0);
            txtDisenho.setText("");
            txtNumeroResolucion.setText("");
            txtPrefijo.setText("");
            dtFechaInicio.setDate(null);
            dtFechaFinal.setDate(null);
            txtNumeracionDel.setText("");
            txtNumeracionHasta.setText("");
        } else {
            txtDescripcion.setText(texto(datos.getDescripcionResolucion()));
            cmbTipoComprobante.setSelectedItem(texto(datos.getTipoResolucion()));
            txtDisenho.setText(texto(datos.getDisenho()));
            txtNumeroResolucion.setText(texto(datos.getNumeroResolucion()));
            txtPrefijo.setText(texto(datos.getPrefijo()));
            dtFechaInicio.setDate(fecha(datos.getFechaInicio()));
            dtFechaFinal.setDate(fecha(datos.getFechaFinal()));
            txtNumeracionDel.setText(numero(datos.getNumeracionDel()));
            txtNumeracionHasta.setText(numero(datos.getNumeracionHasta()));
        }
        txtDescripcion.requestFocus();
    }

    private String texto(String valor) {
        return valor == null ? "" : valor;
    }

    private String numero(Integer valor) {
        return valor == null ? "" : String.valueOf(valor);
    }

    /**
     * La base de datos entrega las fechas como yyyy-MM-dd.
     */
    private Date fecha(String valor) {
        return valor == null || valor.length() < 10 ? null : Fechas.formatearFecha(valor);
    }

    private void guardar() {
        String descripcion = txtDescripcion.getText().trim();
        String tipo = cmbTipoComprobante.getSelectedItem() == null ? "" : cmbTipoComprobante.getSelectedItem().toString();
        String disenho = txtDisenho.getText().trim();
        String numeroResolucion = txtNumeroResolucion.getText().trim();
        String prefijo = txtPrefijo.getText().trim().toUpperCase();
        Date fechaInicio = dtFechaInicio.getDate();
        Date fechaFinal = dtFechaFinal.getDate();
        String numeracionDel = txtNumeracionDel.getText().trim();
        String numeracionHasta = txtNumeracionHasta.getText().trim();

        if (!validaciones.validarResolucion(descripcion, tipo, disenho, numeroResolucion, prefijo, fechaInicio, fechaFinal,
                numeracionDel, numeracionHasta)) {
            return;
        }

        int idResolucion = resolucion == null ? 0 : resolucion.getIdResolucion();

        ModeloResolucion capturada = new ModeloResolucion(idResolucion, descripcion, tipo, disenho, numeroResolucion, prefijo,
                Fechas.convertirFechaAString(fechaInicio), Fechas.convertirFechaAString(fechaFinal),
                Integer.parseInt(numeracionDel), Integer.parseInt(numeracionHasta));

        if (resolucion != null) {
            capturada.setConsecutivo(resolucion.getConsecutivo());
        }

        resolucion = capturada;
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
        lbDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbTipoComprobante = new javax.swing.JLabel();
        cmbTipoComprobante = new javax.swing.JComboBox();
        lbDisenho = new javax.swing.JLabel();
        txtDisenho = new javax.swing.JTextField();
        lbNumeroResolucion = new javax.swing.JLabel();
        txtNumeroResolucion = new javax.swing.JTextField();
        lbPrefijo = new javax.swing.JLabel();
        txtPrefijo = new javax.swing.JTextField();
        lbFechaInicio = new javax.swing.JLabel();
        dtFechaInicio = new com.toedter.calendar.JDateChooser();
        lbFechaFinal = new javax.swing.JLabel();
        dtFechaFinal = new com.toedter.calendar.JDateChooser();
        lbNumeracionDel = new javax.swing.JLabel();
        txtNumeracionDel = new javax.swing.JTextField();
        lbNumeracionHasta = new javax.swing.JLabel();
        txtNumeracionHasta = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos de la resolución");
        setResizable(false);

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbTitulo.setText("Datos de la resolución");

        lbDescripcion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDescripcion.setText("Descripción:");

        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbTipoComprobante.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTipoComprobante.setText("Tipo de comprobante:");

        cmbTipoComprobante.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoComprobante.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura Normal", "Facturación Electrónica", "Facturación Electrónica POS", "Compra Normal", "Documento Soporte" }));

        lbDisenho.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDisenho.setText("Diseño:");

        txtDisenho.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbNumeroResolucion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNumeroResolucion.setText("Número resolución:");

        txtNumeroResolucion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPrefijo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPrefijo.setText("Prefijo:");

        txtPrefijo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbFechaInicio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbFechaInicio.setText("Fecha inicio:");

        dtFechaInicio.setDateFormatString("dd/MM/yyyy");
        dtFechaInicio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbFechaFinal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbFechaFinal.setText("Fecha final:");

        dtFechaFinal.setDateFormatString("dd/MM/yyyy");
        dtFechaFinal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbNumeracionDel.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNumeracionDel.setText("Numeración desde:");

        txtNumeracionDel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbNumeracionHasta.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbNumeracionHasta.setText("Numeración hasta:");

        txtNumeracionHasta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDescripcion)
                            .addComponent(lbTipoComprobante)
                            .addComponent(lbDisenho)
                            .addComponent(lbNumeroResolucion)
                            .addComponent(lbPrefijo)
                            .addComponent(lbFechaInicio)
                            .addComponent(lbFechaFinal)
                            .addComponent(lbNumeracionDel)
                            .addComponent(lbNumeracionHasta))
                        .addGap(10, 10, 10)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(cmbTipoComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDisenho)
                            .addComponent(txtNumeroResolucion)
                            .addComponent(txtPrefijo)
                            .addComponent(dtFechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtFechaFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumeracionDel)
                            .addComponent(txtNumeracionHasta)))
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
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbTipoComprobante)
                    .addComponent(cmbTipoComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbDisenho)
                    .addComponent(txtDisenho, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbNumeroResolucion)
                    .addComponent(txtNumeroResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbPrefijo)
                    .addComponent(txtPrefijo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbFechaInicio)
                    .addComponent(dtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbFechaFinal)
                    .addComponent(dtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbNumeracionDel)
                    .addComponent(txtNumeracionDel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbNumeracionHasta)
                    .addComponent(txtNumeracionHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbTipoComprobante;
    private com.toedter.calendar.JDateChooser dtFechaFinal;
    private com.toedter.calendar.JDateChooser dtFechaInicio;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbDisenho;
    private javax.swing.JLabel lbFechaFinal;
    private javax.swing.JLabel lbFechaInicio;
    private javax.swing.JLabel lbNumeracionDel;
    private javax.swing.JLabel lbNumeracionHasta;
    private javax.swing.JLabel lbNumeroResolucion;
    private javax.swing.JLabel lbPrefijo;
    private javax.swing.JLabel lbTipoComprobante;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDisenho;
    private javax.swing.JTextField txtNumeracionDel;
    private javax.swing.JTextField txtNumeracionHasta;
    private javax.swing.JTextField txtNumeroResolucion;
    private javax.swing.JTextField txtPrefijo;
    // End of variables declaration//GEN-END:variables
}
