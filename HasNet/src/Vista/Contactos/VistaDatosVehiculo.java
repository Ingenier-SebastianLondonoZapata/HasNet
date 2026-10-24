package Vista.Contactos;

import Controlador.Alertas.ControladorAlertas;
import Dao.Terceros.DaoVehiculos;
import Modelo.Terceros.ModeloDatosVehiculo;
import Validaciones.Contactos.SquemaDatosVehiculo;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class VistaDatosVehiculo extends javax.swing.JDialog {

    private final DaoVehiculos daoVehiculos = new DaoVehiculos();
    private final SquemaDatosVehiculo validaciones = new SquemaDatosVehiculo();
    private final metodosGenerales metodos = new metodosGenerales();

    private boolean aceptado;
    private ModeloDatosVehiculo vehiculo;
    private List<String> placasRegistradas = new ArrayList<>();
    private String idSistemaContacto = "";

    public VistaDatosVehiculo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dtFechaCompra.setNothingAllowed(true);
        this.getRootPane().registerKeyboardAction(accionCerrar(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void preparar(ModeloDatosVehiculo vehiculo, List<String> placasRegistradas, String idSistemaContacto) {
        this.aceptado = false;
        this.vehiculo = vehiculo;
        this.placasRegistradas = placasRegistradas == null ? new ArrayList<String>() : placasRegistradas;
        this.idSistemaContacto = idSistemaContacto == null ? "" : idSistemaContacto;

        cargarCatalogos();
        mostrarVehiculo(vehiculo);

        String titulo = vehiculo == null ? "Agregar vehiculo" : "Modificar vehiculo";
        setTitle(titulo);

        pack();
        setLocationRelativeTo(null);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public ModeloDatosVehiculo getVehiculo() {
        return vehiculo;
    }

    private ActionListener accionCerrar() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelar();
            }
        };
    }

    private void cargarCatalogos() {
        llenar(cmbTipoVehiculo, daoVehiculos.listarTiposVehiculo());
        llenar(cmbMarca, daoVehiculos.listarMarcas());
        llenar(cmbColor, daoVehiculos.listarColores());
    }

    private void llenar(JComboBox combo, List<String> valores) {
        combo.removeAllItems();
        combo.addItem("");

        for (String valor : valores) {
            combo.addItem(valor);
        }
        combo.setSelectedItem("");
    }

    private void mostrarVehiculo(ModeloDatosVehiculo datos) {
        if (datos == null) {
            txtPlaca.setText("");
            txtModelo.setText("");
            txtChasis.setText("");
            txtMotor.setText("");
            cmbTipoVehiculo.setSelectedItem("");
            cmbMarca.setSelectedItem("");
            cmbColor.setSelectedItem("");
            dtFechaCompra.setSelectedDate(null);
            txtPlaca.requestFocus();
            return;
        }

        txtPlaca.setText(texto(datos.getPlaca()));
        txtModelo.setText(texto(datos.getModelo()));
        txtChasis.setText(texto(datos.getNumeroChasis()));
        txtMotor.setText(texto(datos.getNumeroMotor()));
        cmbTipoVehiculo.setSelectedItem(texto(datos.getTipoVehiculo()));
        cmbMarca.setSelectedItem(texto(datos.getMarca()));
        cmbColor.setSelectedItem(texto(datos.getColor()));
        dtFechaCompra.setSelectedDate(metodos.haciaDate(texto(datos.getFechaCompra())));
        txtPlaca.requestFocus();
    }

    private ModeloDatosVehiculo leerFormulario() {
        return new ModeloDatosVehiculo(txtPlaca.getText().trim().toUpperCase(), seleccion(cmbTipoVehiculo),
                txtChasis.getText().trim(), txtMotor.getText().trim(), txtModelo.getText().trim(),
                seleccion(cmbMarca), leerFechaCompra(), seleccion(cmbColor));
    }

    private String leerFechaCompra() {
        Calendar fecha = dtFechaCompra.getSelectedDate();
        return fecha == null ? "" : metodos.desdeDate(fecha);
    }

    private String seleccion(JComboBox combo) {
        Object valor = combo.getSelectedItem();
        return valor == null ? "" : valor.toString().trim().toUpperCase();
    }

    private String texto(String valor) {
        return valor == null ? "" : valor;
    }

    private void guardar() {
        ModeloDatosVehiculo capturado = leerFormulario();

        if (!validaciones.validarVehiculo(capturado, placasRegistradas, idSistemaContacto)) {
            return;
        }

        if (!registrarEnCatalogos(capturado)) {
            return;
        }

        vehiculo = capturado;
        aceptado = true;
        dispose();
    }

    private boolean registrarEnCatalogos(ModeloDatosVehiculo capturado) {
        return agregarSiNoExiste(cmbTipoVehiculo, capturado.getTipoVehiculo(), "tipo de vehiculo")
                && agregarSiNoExiste(cmbMarca, capturado.getMarca(), "marca")
                && agregarSiNoExiste(cmbColor, capturado.getColor(), "color");
    }

    private boolean agregarSiNoExiste(JComboBox combo, String valor, String catalogo) {
        if (valor.isEmpty() || existeEnCombo(combo, valor)) {
            return true;
        }

        if (!ControladorAlertas.option("El " + catalogo + " " + valor + " no existe. ¿Desea crearlo?")) {
            return false;
        }

        boolean agregado;
        if (combo == cmbTipoVehiculo) {
            agregado = daoVehiculos.agregarTipoVehiculo(valor);
        } else if (combo == cmbMarca) {
            agregado = daoVehiculos.agregarMarca(valor);
        } else {
            agregado = daoVehiculos.agregarColor(valor);
        }

        if (!agregado) {
            ControladorAlertas.alertFail("No se pudo crear el " + catalogo + " " + valor);
            return false;
        }

        combo.addItem(valor);
        combo.setSelectedItem(valor);
        return true;
    }

    private boolean existeEnCombo(JComboBox combo, String valor) {
        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            if (item != null && valor.equalsIgnoreCase(item.toString().trim())) {
                return true;
            }
        }
        return false;
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
        lbPlaca = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lbTipoVehiculo = new javax.swing.JLabel();
        cmbTipoVehiculo = new javax.swing.JComboBox();
        lbModelo = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        lbMarca = new javax.swing.JLabel();
        cmbMarca = new javax.swing.JComboBox();
        lbChasis = new javax.swing.JLabel();
        txtChasis = new javax.swing.JTextField();
        lbColor = new javax.swing.JLabel();
        cmbColor = new javax.swing.JComboBox();
        lbMotor = new javax.swing.JLabel();
        txtMotor = new javax.swing.JTextField();
        lbFechaCompra = new javax.swing.JLabel();
        dtFechaCompra = new datechooser.beans.DateChooserCombo();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos del vehiculo");
        setResizable(false);

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbTitulo.setText("Datos del vehículo");

        lbPlaca.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPlaca.setText("Placa:");

        txtPlaca.setBackground(new java.awt.Color(255, 204, 204));
        txtPlaca.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        lbTipoVehiculo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTipoVehiculo.setText("Tipo de vehículo:");

        cmbTipoVehiculo.setEditable(true);
        cmbTipoVehiculo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbModelo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbModelo.setText("Modelo:");

        txtModelo.setBackground(new java.awt.Color(255, 204, 204));
        txtModelo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtModelo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloKeyTyped(evt);
            }
        });

        lbMarca.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbMarca.setText("Marca:");

        cmbMarca.setEditable(true);
        cmbMarca.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbChasis.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbChasis.setText("# de chasis:");

        txtChasis.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbColor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbColor.setText("Color:");

        cmbColor.setEditable(true);
        cmbColor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbMotor.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbMotor.setText("# de motor:");

        txtMotor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbFechaCompra.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbFechaCompra.setText("Fecha compra:");

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
                            .addComponent(lbPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbChasis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMotor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addComponent(txtModelo)
                            .addComponent(txtChasis)
                            .addComponent(txtMotor))
                        .addGap(25, 25, 25)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTipoVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoVehiculo, 0, 190, Short.MAX_VALUE)
                            .addComponent(cmbMarca, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbColor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dtFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                    .addComponent(lbPlaca)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTipoVehiculo)
                    .addComponent(cmbTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbModelo)
                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMarca)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbChasis)
                    .addComponent(txtChasis, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbColor)
                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMotor)
                    .addComponent(txtMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFechaCompra)
                    .addComponent(dtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        txtPlaca.setText(txtPlaca.getText().toUpperCase());

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTipoVehiculo.requestFocus();
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void txtModeloKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtModeloKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtModeloKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbColor;
    private javax.swing.JComboBox cmbMarca;
    private javax.swing.JComboBox cmbTipoVehiculo;
    private datechooser.beans.DateChooserCombo dtFechaCompra;
    private javax.swing.JLabel lbChasis;
    private javax.swing.JLabel lbColor;
    private javax.swing.JLabel lbFechaCompra;
    private javax.swing.JLabel lbMarca;
    private javax.swing.JLabel lbModelo;
    private javax.swing.JLabel lbMotor;
    private javax.swing.JLabel lbPlaca;
    private javax.swing.JLabel lbTipoVehiculo;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JTextField txtChasis;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtMotor;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
