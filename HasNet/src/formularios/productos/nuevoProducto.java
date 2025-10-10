package formularios.productos;

import Vista.Productos.vistaIngresos;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import Vista.Ventas.vistaFactura;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

public class nuevoProducto extends javax.swing.JDialog {

    private metodosGenerales metodos;
    private Instancias instancias;
    private String opc, simbolo = "";
    private vistaFactura factura;
    private vistaIngresos ingreso;

    public vistaFactura getFactura() {
        return factura;
    }

    public void setFactura(vistaFactura factura) {
        this.factura = factura;
    }

    public vistaIngresos getIngreso() {
        return ingreso;
    }

    public void setIngreso(vistaIngresos ingreso) {
        this.ingreso = ingreso;
    }

    public nuevoProducto(java.awt.Frame parent, boolean modal, metodosGenerales metodos, Instancias instancias, String opc) {
        super(parent, modal);
        initComponents();
        this.instancias = instancias;
        this.metodos = metodos;
        this.opc = opc;
        this.setLocationRelativeTo(null);

        if (instancias.getRegimen().equals("SinIva")) {
            pnlImpuestos.setVisible(false);
        }

        simbolo = instancias.getSimbolo();

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
        txtCodigo = new javax.swing.JTextField();
        lbRazon = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbDireccion = new javax.swing.JLabel();
        txtL1 = new javax.swing.JTextField();
        lbTelefono2 = new javax.swing.JLabel();
        rdInventarioNO = new javax.swing.JRadioButton();
        rdInventarioSI = new javax.swing.JRadioButton();
        pnlImpuestos = new javax.swing.JPanel();
        lbIvacompra = new javax.swing.JLabel();
        txtIvaCompra = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        txtIva = new javax.swing.JTextField();
        txtIva1 = new javax.swing.JTextField();
        lbTelefono1 = new javax.swing.JLabel();
        txtIvaCompra1 = new javax.swing.JTextField();
        lbIvacompra1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbNit1.setFont(new java.awt.Font("Century Gothic", 1, 30)); // NOI18N
        lbNit1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNit1.setText("NUEVO PRODUCTO");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        lbNit.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbNit.setText("Codigo: *");

        txtCodigo.setName("Nit"); // NOI18N

        lbRazon.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbRazon.setText("Descripción: *");

        txtDescripcion.setName("Razón social"); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
        });

        lbDireccion.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbDireccion.setText("Precio venta: *");

        txtL1.setName("precio de venta"); // NOI18N
        txtL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtL1ActionPerformed(evt);
            }
        });
        txtL1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtL1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtL1KeyTyped(evt);
            }
        });

        lbTelefono2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono2.setText("Maneja Inv. *");

        rdInventarioNO.setBackground(new java.awt.Color(255, 255, 255));
        rdInventarioNO.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdInventarioNO.setText("NO");
        rdInventarioNO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        rdInventarioSI.setBackground(new java.awt.Color(255, 255, 255));
        rdInventarioSI.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rdInventarioSI.setText("SI");
        rdInventarioSI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pnlImpuestos.setBackground(new java.awt.Color(255, 255, 255));
        pnlImpuestos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Impuestos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 12))); // NOI18N

        lbIvacompra.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbIvacompra.setText("Iva compra: *");

        txtIvaCompra.setName("Teléfono"); // NOI18N
        txtIvaCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaCompraKeyTyped(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono.setText("Iva venta: *");

        txtIva.setName("Teléfono"); // NOI18N
        txtIva.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaKeyTyped(evt);
            }
        });

        txtIva1.setName("Teléfono"); // NOI18N
        txtIva1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIva1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIva1KeyTyped(evt);
            }
        });

        lbTelefono1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbTelefono1.setText("IMPO venta: *");

        txtIvaCompra1.setName("Teléfono"); // NOI18N
        txtIvaCompra1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIvaCompra1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIvaCompra1KeyTyped(evt);
            }
        });

        lbIvacompra1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbIvacompra1.setText("IMPO compra: *");

        javax.swing.GroupLayout pnlImpuestosLayout = new javax.swing.GroupLayout(pnlImpuestos);
        pnlImpuestos.setLayout(pnlImpuestosLayout);
        pnlImpuestosLayout.setHorizontalGroup(
            pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpuestosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbIvacompra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbIvacompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIvaCompra1, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
                    .addComponent(txtIvaCompra))
                .addGap(41, 41, 41)
                .addGroup(pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(txtIva1))
                .addGap(23, 23, 23))
        );
        pnlImpuestosLayout.setVerticalGroup(
            pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImpuestosLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIvacompra, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIvaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIva1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIvaCompra1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbIvacompra1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR     ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(241, 148, 138));
        btnEliminar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlImpuestos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbRazon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(lbNit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtL1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lbTelefono2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdInventarioSI, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdInventarioNO))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbNit1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNit, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRazon, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtL1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdInventarioSI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdInventarioNO, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pnlImpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnGuardar))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIva.requestFocus();
        }
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void txtIvaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyReleased

    }//GEN-LAST:event_txtIvaKeyReleased

    private void txtIvaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaKeyTyped

    private void txtL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtL1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL1ActionPerformed

    private void txtL1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyReleased
        if (txtL1.getText().equals("") || txtL1.getText().equals(this.simbolo) || txtL1.getText().equals(this.simbolo + " ")) {
            txtL1.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardar.requestFocus();
        } else {
            txtL1.setText(big.setMoneda(big.getMoneda(txtL1.getText())));
        }
    }//GEN-LAST:event_txtL1KeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (rdInventarioNO.isSelected() == false && rdInventarioSI.isSelected() == false) {
            metodos.msgError(null, "Seleccione el manejo del producto");
            return;
        }

        Object[] campos = {txtCodigo, txtDescripcion, txtL1};
        String faltantes = metodos.camposVacios(campos);

        if (!faltantes.equals("")) {
            metodos.msgAdvertencia(null, "No puede continuar, faltan los siguientes campos: " + faltantes);
            return;
        }

        if (metodos.msgPregunta(null, "¿Desea continuar?") == 0) {

            //try {
            ndProducto nodo = instancias.getSql().getDatosProducto(txtCodigo.getText().replace("'", "//"), "bdProductos");

            if (nodo.getCodigo() != null) {
                metodos.msgError(null, "El producto ya existe");
                txtCodigo.requestFocus();
                return;
            }

            if (txtIva.getText().equals("")) {
                txtIva.setText("0");
            }

            if (txtIvaCompra.getText().equals("")) {
                txtIvaCompra.setText("0");
            }

            String consecutivo = "";
            int num = Integer.parseInt(instancias.getSql().getNumConsecutivo("PROD")[0].toString());
            consecutivo = String.valueOf(num);
            for (int i = 0; i < 8 - String.valueOf(num).length(); i++) {
                consecutivo = "0" + consecutivo;
            }

            Object[] vector = {"PROD-" + consecutivo, txtCodigo.getText().replace("'", "//"), "", txtDescripcion.getText(), null, null, null, txtIva.getText(),
                big.getMoneda(txtL1.getText()), big.getMoneda(txtL1.getText()), big.getMoneda(txtL1.getText()), big.getMoneda(txtL1.getText()),
                big.getMoneda(txtL1.getText()), big.getMoneda(txtL1.getText()),
                big.getMoneda(txtL1.getText()), big.getMoneda(txtL1.getText()), instancias.getUsuario(), "1", "Und",
                "", big.getMoneda("0"), 20, 30, "",
                txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "",
                txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "", txtDescripcion.getText(), "", "", false, false, false, false, false, false, false,
                "0", 0, instancias.getTerminal(), "0", "",
                rdInventarioSI.isSelected(), "", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", false,
                "", "", "", "", "", "", "", "", "", "0", "", "", "", "", "", "", "", "", "", "", "", "", "", "1", "0", "0", txtDescripcion.getText(), "0"};
            nodo = metodos.llenarProducto(vector);

            if (!instancias.getSql().agregarProducto(nodo, "bdProductos")) {
                metodos.msgError(null, "Hubo un problema al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega1")) {
                metodos.msgError(null, "Hubo un problema al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega2")) {
                metodos.msgError(null, "Hubo un problema al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega3")) {
                metodos.msgError(null, "Hubo un problema al guardar el producto");
                return;
            }
            if (!instancias.getSql().agregarProducto(nodo, "bdProductosBodega4")) {
                metodos.msgError(null, "Hubo un problema al guardar el producto");
                return;
            }

            if (!instancias.getSql().agregarPonderado(metodos.fechaConsulta(metodosGenerales.fechaHora()), "PROD-" + consecutivo,
                    BigDecimal.ZERO, "0", "0", BigDecimal.ZERO, "0", instancias.getUsuario(), BigDecimal.ZERO, "CREACIÓN PROD")) {
                metodos.msgError(null, "Error al guardar en el consecutivo del producto");
            }

            if (!instancias.getSql().agregarUltimoPonderado(metodos.fechaConsulta(metodosGenerales.fechaHora()), "PROD-" + consecutivo,
                    BigDecimal.ZERO, "0", "0", BigDecimal.ZERO, "0", instancias.getUsuario(), BigDecimal.ZERO, "CREACIÓN PROD")) {
                metodos.msgError(null, "Error al guardar en el consecutivo del producto");
            }

            if (!instancias.getSql().aumentarConsecutivo("PROD", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PROD")[0]) + 1)) {
                metodos.msgError(null, "Error al guardar en el consecutivo del producto");
            }

            metodos.msgExito(null, "Producto registrado con éxito");

            switch (opc) {
                case "factura":
                    instancias.getBusProductos().dispose();
                    factura.nuevoProducto(txtCodigo.getText());
                    this.dispose();
                    break;
                case "ingreso":
                    instancias.getBusProductos().dispose();
                    ingreso.nuevoProducto(txtCodigo.getText());
                    this.dispose();
                    break;
                case "ordenCompra":
                    instancias.getBusProductos().dispose();
                    ingreso.nuevoProducto(txtCodigo.getText());
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

    private void txtIvaCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompraKeyReleased

    }//GEN-LAST:event_txtIvaCompraKeyReleased

    private void txtIvaCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompraKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtIvaCompraKeyTyped

    private void txtL1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtL1KeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtL1KeyTyped

    private void txtIva1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIva1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIva1KeyReleased

    private void txtIva1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIva1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIva1KeyTyped

    private void txtIvaCompra1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompra1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaCompra1KeyReleased

    private void txtIvaCompra1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIvaCompra1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIvaCompra1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbIvacompra;
    private javax.swing.JLabel lbIvacompra1;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JLabel lbTelefono1;
    private javax.swing.JLabel lbTelefono2;
    private javax.swing.JPanel pnlImpuestos;
    private javax.swing.JRadioButton rdInventarioNO;
    private javax.swing.JRadioButton rdInventarioSI;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtIva;
    private javax.swing.JTextField txtIva1;
    private javax.swing.JTextField txtIvaCompra;
    private javax.swing.JTextField txtIvaCompra1;
    private javax.swing.JTextField txtL1;
    // End of variables declaration//GEN-END:variables
}
