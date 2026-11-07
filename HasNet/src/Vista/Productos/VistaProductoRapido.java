package Vista.Productos;

import Dao.Productos.DaoCatalogosProducto;
import Dao.Productos.DaoProducto;
import Modelo.Productos.ModeloOpcionCatalogo;
import Modelo.Productos.ModeloProducto;
import Utilidades.Constantes;
import Utilidades.Contactos.Bodegas;
import Validaciones.Productos.SquemaProductoRapido;
import clases.Instancias;
import clases.big;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class VistaProductoRapido extends javax.swing.JDialog {

    private static final String UNIDAD_PREDETERMINADA = "Und";
    private static final String OPCION_SI = "SI";
    private static final String TIPO_PRODUCTO_SISTEMA = "ADMIN";

    private final SquemaProductoRapido validaciones = new SquemaProductoRapido();
    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoCatalogosProducto daoCatalogos = new DaoCatalogosProducto();

    private boolean aceptado;
    private boolean regimenComun;
    private ModeloProducto producto;

    public VistaProductoRapido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarNavegacion();
        this.getRootPane().registerKeyboardAction(e -> cancelar(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void preparar(String busqueda, boolean regimenComun) {
        this.aceptado = false;
        this.producto = null;
        this.regimenComun = regimenComun;

        lbIva.setVisible(regimenComun);
        cmbIva.setVisible(regimenComun);
        lbImpoconsumo.setVisible(regimenComun);
        cmbImpoconsumo.setVisible(regimenComun);

        cargarTarifas(cmbIva, Constantes.TARIFAS_IVAS_PERMITIDOS);
        cargarTarifas(cmbImpoconsumo, Constantes.TARIFAS_IMPOCONSUMO_PERMITIDOS);
        cargarGrupos();
        cargarSubgrupos();
        cargarMedidas();
        cmbInventario.setSelectedItem(OPCION_SI);
        txtPrecio.setText(big.setMoneda(BigDecimal.ZERO));

        precargarBusqueda(busqueda == null ? "" : busqueda.trim());

        pack();
        setLocationRelativeTo(null);
    }

    public boolean isAceptado() {
        return aceptado;
    }

    public ModeloProducto getProducto() {
        return producto;
    }

    private void precargarBusqueda(String busqueda) {
        boolean esDescripcion = busqueda.contains(" ");

        txtCodigo.setText(esDescripcion ? "" : busqueda);
        txtDescripcion.setText(esDescripcion ? busqueda : "");

        if (busqueda.isEmpty() || esDescripcion) {
            txtCodigo.requestFocus();
        } else {
            txtDescripcion.requestFocus();
        }
    }

    private void cargarTarifas(JComboBox combo, List<Integer> tarifas) {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        for (Integer tarifa : tarifas) {
            modelo.addElement(String.valueOf(tarifa));
        }
        combo.setModel(modelo);
    }

    private void cargarGrupos() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement(ModeloOpcionCatalogo.vacia("Sin grupo"));
        for (ModeloOpcionCatalogo grupo : daoCatalogos.listarGrupos()) {
            modelo.addElement(grupo);
        }
        cmbGrupo.setModel(modelo);
    }

    private void cargarSubgrupos() {
        List<ModeloOpcionCatalogo> subgrupos = daoCatalogos.listarSubgrupos(codigoSeleccionado(cmbGrupo));

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement(ModeloOpcionCatalogo.vacia("Sin subgrupo"));
        for (ModeloOpcionCatalogo subgrupo : subgrupos) {
            modelo.addElement(subgrupo);
        }
        cmbSubgrupo.setModel(modelo);
        cmbSubgrupo.setEnabled(!subgrupos.isEmpty());
    }

    private void cargarMedidas() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String predeterminada = null;
        for (String medida : daoCatalogos.listarMedidas()) {
            modelo.addElement(medida);
            if (predeterminada == null && UNIDAD_PREDETERMINADA.equalsIgnoreCase(medida)) {
                predeterminada = medida;
            }
        }
        cmbUnidad.setModel(modelo);
        if (predeterminada != null) {
            cmbUnidad.setSelectedItem(predeterminada);
        }
    }

    private void configurarNavegacion() {
        Component[] orden = {txtCodigo, txtDescripcion, txtPrecio, cmbIva, cmbImpoconsumo, cmbInventario,
            cmbGrupo, cmbSubgrupo, cmbUnidad};

        for (int i = 0; i < orden.length; i++) {
            final int posicion = i;
            orden[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent evt) {
                    if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
                        return;
                    }
                    Component siguiente = siguienteDisponible(orden, posicion);
                    if (siguiente == null) {
                        guardar();
                    } else {
                        siguiente.requestFocus();
                    }
                }
            });
        }

        txtPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char tecla = evt.getKeyChar();
                if (!Character.isDigit(tecla) && tecla != ',') {
                    evt.consume();
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                if (evt.getKeyCode() != KeyEvent.VK_ENTER) {
                    formatearPrecio();
                }
            }
        });
    }

    private Component siguienteDisponible(Component[] orden, int posicion) {
        for (int i = posicion + 1; i < orden.length; i++) {
            if (orden[i].isVisible() && orden[i].isEnabled()) {
                return orden[i];
            }
        }
        return null;
    }

    private void formatearPrecio() {
        BigDecimal precio = precioVenta();
        txtPrecio.setText(big.setMoneda(precio == null ? BigDecimal.ZERO : precio));
    }

    private void guardar() {
        ModeloProducto capturado = leerFormulario();
        BigDecimal precio = precioVenta();
        boolean requiereUnidad = cmbUnidad.getItemCount() > 0;

        if (!validaciones.validarProducto(capturado, precio, existeCodigo(capturado.getCodigo()), requiereUnidad)) {
            return;
        }

        capturado.setL1(precio.toPlainString());
        producto = capturado;
        aceptado = true;
        dispose();
    }

    private void cancelar() {
        aceptado = false;
        dispose();
    }

    private boolean existeCodigo(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            return false;
        }
        ModeloProducto existente = daoProducto.obtenerDatosProducto(codigo, Bodegas.predeterminada());
        return existente.getCodigo() != null || existente.getIdSistema() != null;
    }

    private ModeloProducto leerFormulario() {
        Instancias instancias = Instancias.getInstancias();
        String descripcion = txtDescripcion.getText().trim();

        ModeloProducto datos = new ModeloProducto();
        datos.setIdSistema("");
        datos.setCodigo(txtCodigo.getText().trim().replace("'", "//"));
        datos.setCodigoBarras("");
        datos.setDescripcion(descripcion);
        datos.setDescripcionLarga(descripcion);
        datos.setGrupo(vacioANulo(codigoSeleccionado(cmbGrupo)));
        datos.setSubgrupo(vacioANulo(codigoSeleccionado(cmbSubgrupo)));
        datos.setProveedor(null);
        datos.setUsuario(TIPO_PRODUCTO_SISTEMA);
        datos.setUnd(seleccion(cmbUnidad));
        datos.setManejaInventario(OPCION_SI.equals(seleccion(cmbInventario)));

        datos.setIva(regimenComun ? seleccion(cmbIva) : "0");
        datos.setImpoconsumoVenta(regimenComun ? seleccion(cmbImpoconsumo) : "0");
        datos.setL1("0");

        datos.setReferencia("");
        datos.setUbicacion1("");
        datos.setCosto("0");
        datos.setPonderado("0");
        datos.setMinimo("1");
        datos.setMinima("20");
        datos.setMaxima("30");
        datos.setTerminal(instancias.getTerminal());
        datos.setClaseBuscador("");
        datos.setPorcentaje("");
        datos.setIndVentas("1");
        datos.setTipoProducto("");
        datos.inicializarTextosOpcionales();

        return datos;
    }

    private BigDecimal precioVenta() {
        String texto = txtPrecio.getText().trim();
        String simbolo = Instancias.getInstancias().getSimbolo();
        if (texto.isEmpty() || texto.equals(simbolo)) {
            return BigDecimal.ZERO;
        }
        try {
            return big.getMoneda(texto);
        } catch (RuntimeException e) {
            return null;
        }
    }

    private String codigoSeleccionado(JComboBox combo) {
        Object item = combo.getSelectedItem();
        return item instanceof ModeloOpcionCatalogo ? ((ModeloOpcionCatalogo) item).getCodigo() : "";
    }

    private String seleccion(JComboBox combo) {
        Object item = combo.getSelectedItem();
        return item == null ? "" : item.toString().trim();
    }

    private static String vacioANulo(String valor) {
        return valor == null || valor.isEmpty() ? null : valor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContenido = new javax.swing.JPanel();
        lbCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbDescripcion = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        lbPrecio = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lbIva = new javax.swing.JLabel();
        cmbIva = new javax.swing.JComboBox();
        lbImpoconsumo = new javax.swing.JLabel();
        cmbImpoconsumo = new javax.swing.JComboBox();
        lbInventario = new javax.swing.JLabel();
        cmbInventario = new javax.swing.JComboBox();
        lbGrupo = new javax.swing.JLabel();
        cmbGrupo = new javax.swing.JComboBox();
        lbSubgrupo = new javax.swing.JLabel();
        cmbSubgrupo = new javax.swing.JComboBox();
        lbUnidad = new javax.swing.JLabel();
        cmbUnidad = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo producto");
        setResizable(false);

        pnlContenido.setBackground(new java.awt.Color(255, 255, 255));

        lbCodigo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbCodigo.setText("Código:");

        txtCodigo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbDescripcion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDescripcion.setText("Descripción:");

        txtDescripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbPrecio.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbPrecio.setText("Precio de venta:");

        txtPrecio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lbIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbIva.setText("IVA (%):");

        cmbIva.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbImpoconsumo.setText("Impoconsumo (%):");

        cmbImpoconsumo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbInventario.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbInventario.setText("Maneja inventario:");

        cmbInventario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbInventario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SI", "NO" }));

        lbGrupo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbGrupo.setText("Grupo:");

        cmbGrupo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbGrupo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbGrupoItemStateChanged(evt);
            }
        });

        lbSubgrupo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubgrupo.setText("Subgrupo:");

        cmbSubgrupo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        lbUnidad.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbUnidad.setText("Unidad de medida:");

        cmbUnidad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

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
                    .addGroup(pnlContenidoLayout.createSequentialGroup()
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSubgrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(txtDescripcion)
                            .addComponent(txtPrecio)
                            .addComponent(cmbIva, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbImpoconsumo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbInventario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbGrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSubgrupo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbUnidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContenidoLayout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrecio)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbIva)
                    .addComponent(cmbIva, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbImpoconsumo)
                    .addComponent(cmbImpoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbInventario)
                    .addComponent(cmbInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGrupo)
                    .addComponent(cmbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSubgrupo)
                    .addComponent(cmbSubgrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbUnidad)
                    .addComponent(cmbUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cmbGrupoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbGrupoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarSubgrupos();
        }
    }//GEN-LAST:event_cmbGrupoItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        guardar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbGrupo;
    private javax.swing.JComboBox cmbImpoconsumo;
    private javax.swing.JComboBox cmbInventario;
    private javax.swing.JComboBox cmbIva;
    private javax.swing.JComboBox cmbSubgrupo;
    private javax.swing.JComboBox cmbUnidad;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbGrupo;
    private javax.swing.JLabel lbImpoconsumo;
    private javax.swing.JLabel lbInventario;
    private javax.swing.JLabel lbIva;
    private javax.swing.JLabel lbPrecio;
    private javax.swing.JLabel lbSubgrupo;
    private javax.swing.JLabel lbUnidad;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
