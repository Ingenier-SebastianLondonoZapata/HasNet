package formularios.Ventas;

import Controlador.Alertas.ControladorAlertas;
import Enums.TipoDocumento;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class dlgTipoDescuento extends javax.swing.JDialog {

    private Instancias instancias = Instancias.getInstancias();
    private metodosGenerales metodos = new metodosGenerales();

    private static final String SIN_DESCUENTO = "Sin-Permiso";
    private static final String OPCION_GENERAL = "Opcion-General";
    private String lugarPeticion;
    private String permisoAsignado = "";
    private Integer numeroFilaSeleccionada = null;
    private BigDecimal subtotalProductoSeleccionado;

    public dlgTipoDescuento(java.awt.Frame parent, String porcentajeActual, String descuentoActual, Integer filaSeleccionada,
            String descuentoSeleccionado, BigDecimal subtotalProducto, String tipoProceso) {
        super(parent, true);
        initComponents();

        this.setLocationRelativeTo(null);
        btnQuitarDesc.setVisible(false);

        if (instancias.getDescuento().equals("peso")) {
            txtPorcentajeDescuento.setEnabled(false);
        } else {
            txtDescuento.setEnabled(false);
        }

        lugarPeticion = tipoProceso;
        txtPorcentajeDescuento.setText(porcentajeActual);
        txtDescuento.setText(descuentoActual);

        if (null != filaSeleccionada) {
            numeroFilaSeleccionada = filaSeleccionada;
        }

        subtotalProductoSeleccionado = subtotalProducto;

        System.out.println("Linea actual: " + descuentoSeleccionado);
        if (!(descuentoSeleccionado.equals(SIN_DESCUENTO) || descuentoSeleccionado.equals(OPCION_GENERAL) || descuentoSeleccionado.equals(""))) {
            cmbTipoDescuento.setSelectedItem(descuentoSeleccionado.split("///")[0]);
            cmbRazonDescuento.setSelectedItem(descuentoSeleccionado.split("///")[1]);
            int cantidadMaxima = descuentoSeleccionado.split("///").length;
            try {
                permisoAsignado = descuentoSeleccionado.split("///")[cantidadMaxima - 1];
            } catch (Exception e) {
            }

            btnQuitarDesc.setVisible(true);
        }

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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtDescuento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPorcentajeDescuento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbTipoDescuento = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cmbRazonDescuento = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnQuitarDesc = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DESCUENTO AL PRODUCTO");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtDescuento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Descuento en valor:");

        txtPorcentajeDescuento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPorcentajeDescuento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentajeDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeDescuentoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeDescuentoKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Descuento en %:");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Tipo descuento:");

        cmbTipoDescuento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbTipoDescuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DESCUENTO_NO_CONDICIONADO", "DESCUENTO_CONDICIONADO" }));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Razón:");

        cmbRazonDescuento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbRazonDescuento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "IMPUESTO_ASUMIDO", "PAGUE_UNO_LLEVE_OTRO", "DESCUENTOS_CONTRACTUALES", "DESCUENTOS_PRONTO_PAGO", "ENVIO_GRATIS", "DESCUENTOS_ESPECIFICOS_INVENTARIOS", "DESCUENTO_MONTO_COMPRA", "DESCUENTO_TEMPORADA", "DESCUENTO_ACTUALIZACION_PRODUCTOS_SERVICIOS", "DESCUENTO_GENERAL", "DESCUENTO_VOLUMEN", "OTRO_DESCUENTO", "CUOTA_COPAGO", "CUOTA_MODERADORA" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTipoDescuento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPorcentajeDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRazonDescuento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTipoDescuento)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbRazonDescuento)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPorcentajeDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("ACEPTAR");
        btnGuardar.setToolTipText("Ctrl+G");
        btnGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        btnGuardar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardarKeyReleased(evt);
            }
        });

        btnQuitarDesc.setBackground(new java.awt.Color(241, 148, 138));
        btnQuitarDesc.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnQuitarDesc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnQuitarDesc.setText("QUITAR DESCUENTO");
        btnQuitarDesc.setToolTipText("Ctrl+G");
        btnQuitarDesc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnQuitarDesc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuitarDesc.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnQuitarDesc.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnQuitarDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarDescActionPerformed(evt);
            }
        });
        btnQuitarDesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnQuitarDescKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnQuitarDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuitarDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("X");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (!validarDatosEnCero()) {
            return;
        }

        boolean mostrarAlerta = false;
        BigDecimal porcentajeDescuento = null;
        BigDecimal descuento = null;

        if (instancias.getDescuento().equals("peso")) {
            descuento = big.getMoneda(txtDescuento.getText());
            BigDecimal porcentajeAplicado = descuento.multiply(big.getBigDecimal("100")).divide(subtotalProductoSeleccionado, 2, RoundingMode.HALF_DOWN);
            if (porcentajeAplicado.compareTo(big.getBigDecimal("100")) >= 0) {
                mostrarAlerta = true;
            }

        } else {
            porcentajeDescuento = big.getBigDecimal(txtPorcentajeDescuento.getText().replace(".", ""));
            if (porcentajeDescuento.compareTo(big.getBigDecimal("100")) >= 0) {
                mostrarAlerta = true;
            }
        }

        if (mostrarAlerta) {
            ControladorAlertas.alert("Descuento no válido");
            return;
        }

        if (lugarPeticion.equals(TipoDocumento.FACTURACION.getValor())) {
            instancias.getFactura().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.COTIZACION.getValor())) {
            instancias.getCotiza().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.ORDER_SERVICIO.getValor())) {
            instancias.getOrdenServicio().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.PEDIDO.getValor())) {
            instancias.getPedido().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.PLAN_SEPARE.getValor())) {
            instancias.getPlanSepare().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.CREDITO.getValor())) {
            instancias.getFacturaCreditos().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.CUENTA_COBRO.getValor())) {
            instancias.getCuentaCobro().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.MESA.getValor())) {
            instancias.getMesa1().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        } else if (lugarPeticion.equals(TipoDocumento.COMPRA.getValor())) {
            instancias.getIngresos().cargarDescuento(numeroFilaSeleccionada, porcentajeDescuento, descuento, obtenerDescripcionDescuento());
        }

        this.dispose();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void txtPorcentajeDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeDescuentoKeyReleased
        if (txtPorcentajeDescuento.getText().equals("")) {
            txtPorcentajeDescuento.setText("0");
        }

        txtPorcentajeDescuento.setText(big.setNumero(big.getBigDecimal(txtPorcentajeDescuento.getText().replace(".", ""))));

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_txtPorcentajeDescuentoKeyReleased

    private void txtPorcentajeDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeDescuentoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPorcentajeDescuentoKeyTyped

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void btnQuitarDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarDescActionPerformed
        if (lugarPeticion.equals(TipoDocumento.FACTURACION.getValor())) {
            instancias.getFactura().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.COTIZACION.getValor())) {
            instancias.getCotiza().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.ORDER_SERVICIO.getValor())) {
            instancias.getOrdenServicio().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.PEDIDO.getValor())) {
            instancias.getPedido().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.PLAN_SEPARE.getValor())) {
            instancias.getPlanSepare().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.CREDITO.getValor())) {
            instancias.getFacturaCreditos().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.CUENTA_COBRO.getValor())) {
            instancias.getCuentaCobro().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.MESA.getValor())) {
            instancias.getMesa1().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        } else if (lugarPeticion.equals(TipoDocumento.COMPRA.getValor())) {
            instancias.getIngresos().cargarDescuento(numeroFilaSeleccionada, null, null, SIN_DESCUENTO);
        }

        this.dispose();
    }//GEN-LAST:event_btnQuitarDescActionPerformed

    private void btnQuitarDescKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnQuitarDescKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnQuitarDescKeyReleased

    private void txtDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyReleased
        try {
            txtDescuento.setText(big.setMoneda(big.getMoneda(txtDescuento.getText())));
        } catch (Exception e) {
            txtDescuento.setText(big.setMoneda(BigDecimal.ZERO));
        }
    }//GEN-LAST:event_txtDescuentoKeyReleased

    private boolean validarDatosEnCero() {
        if (instancias.getDescuento().equals("peso")
                && ("".equals(txtDescuento.getText()) || "$ 0".equals(txtDescuento.getText()))) {

            ControladorAlertas.alert("Debe ingresar el valor del descuento");
            txtDescuento.requestFocus();
            return false;

        } else if (instancias.getDescuento().equals("porcentaje")
                && ("".equals(txtPorcentajeDescuento.getText()) || "0".equals(txtPorcentajeDescuento.getText()))) {

            ControladorAlertas.alert("Debe ingresar el procentaje del descuento");
            txtPorcentajeDescuento.requestFocus();
            return false;
        }

        return true;
    }

    private String obtenerDescripcionDescuento() {
        return cmbTipoDescuento.getSelectedItem().toString() + "///" + cmbRazonDescuento.getSelectedItem().toString() + "///" + this.permisoAsignado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitarDesc;
    private javax.swing.JComboBox cmbRazonDescuento;
    private javax.swing.JComboBox cmbTipoDescuento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtPorcentajeDescuento;
    // End of variables declaration//GEN-END:variables
}
