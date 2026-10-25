package Vista.Productos;

import Utilidades.Contactos.Bodegas;

import Controlador.Alertas.ControladorAlertas;

import Modelo.Productos.ModeloListaPrecio;
import Modelo.Productos.ModeloProducto;
import clases.big;
import clases.metodosGenerales;
import Dao.Productos.DaoListaPrecio;
import Dao.Productos.DaoProducto;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class VistaListasPrecio extends JPanel {

    private static final int COL_LISTA = 0;
    private static final int COL_PRECIO = 1;
    private static final int COL_CODIGO = 2;
    private static final int COL_DESCRIPCION = 3;
    private static final int COL_UBICACION = 4;
    private static final int COL_CANTIDAD = 5;
    private static final int COL_PLU = 6;
    private static final int COL_UTILIDAD = 7;
    private static final int COL_PORCENTAJE = 8;

    private final metodosGenerales metodos = new metodosGenerales();
    private final DaoProducto daoProducto = new DaoProducto();
    private final DaoListaPrecio daoListaPrecio = new DaoListaPrecio();

    private final JTable tabla = new JTable();
    private final JButton btnAgregar = new JButton("Agregar lista");
    private final JButton btnModificar = new JButton("Modificar");
    private final JButton btnEliminar = new JButton("Eliminar");

    private final ModeloListaPrecio listaPrincipal = new ModeloListaPrecio(ModeloListaPrecio.LISTA_PRINCIPAL);
    private final List<ModeloListaPrecio> listas = new ArrayList<>();

    private String idSistema = "";
    private String codigoProducto = "";
    private String descripcionProducto = "";
    private BigDecimal costoBase = BigDecimal.ZERO;
    private Runnable alCambiar;

    public VistaListasPrecio() {
        super(new BorderLayout(0, 6));

        tabla.setFont(new Font("Century Gothic", Font.PLAIN, 12));
        tabla.setRowHeight(24);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    modificarSeleccionada();
                }
            }
        });

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        botones.setOpaque(false);
        botones.add(btnAgregar);
        botones.add(btnModificar);
        botones.add(btnEliminar);

        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                agregar();
            }
        });
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                modificarSeleccionada();
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                eliminarSeleccionada();
            }
        });

        add(botones, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        setOpaque(false);
        refrescarTabla();
    }

    public void setAlCambiar(Runnable alCambiar) {
        this.alCambiar = alCambiar;
    }

    public void setProducto(ModeloProducto producto) {
        idSistema = producto.getIdSistema() == null ? "" : producto.getIdSistema();
        codigoProducto = producto.getCodigo() == null ? "" : producto.getCodigo();
        descripcionProducto = producto.getDescripcion() == null ? "" : producto.getDescripcion();

        listaPrincipal.setPrecio(producto.getPrecioLista(ModeloListaPrecio.LISTA_PRINCIPAL));
        listaPrincipal.setUbicacion(producto.getUbicacion1());

        listas.clear();
        listas.addAll(producto.getListas());
        refrescarTabla();
    }

    public void limpiar() {
        idSistema = "";
        codigoProducto = "";
        descripcionProducto = "";
        listaPrincipal.setPrecio(BigDecimal.ZERO);
        listaPrincipal.setUbicacion("");
        listas.clear();
        refrescarTabla();
    }

    public void setDatosProducto(String idSistema, String codigo, String descripcion) {
        this.idSistema = idSistema == null ? "" : idSistema;
        this.codigoProducto = codigo == null ? "" : codigo;
        this.descripcionProducto = descripcion == null ? "" : descripcion;
        refrescarTabla();
    }

    public void setCostoBase(BigDecimal costoBase) {
        this.costoBase = costoBase == null ? BigDecimal.ZERO : costoBase;
        refrescarTabla();
    }

    public BigDecimal getPrecioPrincipal() {
        return listaPrincipal.getPrecio();
    }

    public String getUbicacionPrincipal() {
        return listaPrincipal.getUbicacion() == null ? "" : listaPrincipal.getUbicacion();
    }

    public List<ModeloListaPrecio> getListas() {
        List<ModeloListaPrecio> activas = new ArrayList<>();
        for (ModeloListaPrecio lista : listas) {
            if (!lista.esVacia()) {
                activas.add(lista);
            }
        }

        return activas;
    }

    public void aplicarA(ModeloProducto producto) {
        producto.setL1(listaPrincipal.getPrecio().toPlainString());
        producto.setUbicacion1(getUbicacionPrincipal());
        producto.setListas(getListas());
    }

    public void setHabilitado(boolean habilitado) {
        btnAgregar.setEnabled(habilitado);
        btnModificar.setEnabled(habilitado);
        btnEliminar.setEnabled(habilitado);
        tabla.setEnabled(habilitado);
    }

    private void agregar() {
        if (siguienteNumero() > ModeloListaPrecio.MAXIMO_LISTA) {
            ControladorAlertas.alert("Ya existen las " + ModeloListaPrecio.MAXIMO_LISTA + " listas de precios permitidas");
            return;
        }

        ModeloListaPrecio nueva = new ModeloListaPrecio(siguienteNumero());
        nueva.setCodigo(codigoProducto.isEmpty() ? "" : codigoProducto + "-" + nueva.getNumeroLista());
        nueva.setDescripcion(descripcionProducto);
        nueva.setPrecio(listaPrincipal.getPrecio());

        DialogoListaPrecio dialogo = new DialogoListaPrecio(nueva, true);
        if (dialogo.mostrar()) {
            listas.add(dialogo.getResultado());
            ordenarListas();
            notificarCambio();
        }
    }

    private void modificarSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            ControladorAlertas.alert("Seleccione la lista que desea modificar");
            return;
        }

        if (fila == 0) {
            DialogoListaPrecio dialogo = new DialogoListaPrecio(copiarPrincipal(), false);
            if (dialogo.mostrar()) {
                listaPrincipal.setPrecio(dialogo.getResultado().getPrecio());
                listaPrincipal.setUbicacion(dialogo.getResultado().getUbicacion());
                notificarCambio();
            }
            return;
        }

        ModeloListaPrecio original = listas.get(fila - 1);
        DialogoListaPrecio dialogo = new DialogoListaPrecio(copiar(original), false);
        if (dialogo.mostrar()) {
            listas.set(fila - 1, dialogo.getResultado());
            notificarCambio();
        }
    }

    private void eliminarSeleccionada() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            ControladorAlertas.alert("Seleccione la lista que desea eliminar");
            return;
        }

        if (fila == 0) {
            ControladorAlertas.alert("La lista 1 es el precio por defecto del producto y no se puede eliminar");
            return;
        }

        ModeloListaPrecio lista = listas.get(fila - 1);
        if (ControladorAlertas.option("¿Desea eliminar la lista " + lista.getNombre() + "?")) {
            listas.remove(fila - 1);
            notificarCambio();
        }
    }

    private void notificarCambio() {
        refrescarTabla();
        if (alCambiar != null) {
            alCambiar.run();
        }
    }

    private int siguienteNumero() {
        int numero = ModeloListaPrecio.LISTA_PRINCIPAL + 1;
        boolean ocupado = true;
        while (ocupado) {
            ocupado = false;
            for (ModeloListaPrecio lista : listas) {
                if (lista.getNumeroLista() == numero) {
                    ocupado = true;
                    numero++;
                    break;
                }
            }
        }

        return numero;
    }

    private void ordenarListas() {
        java.util.Collections.sort(listas, new java.util.Comparator<ModeloListaPrecio>() {
            @Override
            public int compare(ModeloListaPrecio a, ModeloListaPrecio b) {
                return Integer.compare(a.getNumeroLista(), b.getNumeroLista());
            }
        });
    }

    private ModeloListaPrecio copiarPrincipal() {
        ModeloListaPrecio copia = copiar(listaPrincipal);
        copia.setCodigo(codigoProducto);
        copia.setDescripcion(descripcionProducto);
        return copia;
    }

    private static ModeloListaPrecio copiar(ModeloListaPrecio origen) {
        ModeloListaPrecio copia = new ModeloListaPrecio(origen.getNumeroLista());
        copia.setPrecio(origen.getPrecio());
        copia.setCodigo(origen.getCodigo());
        copia.setDescripcion(origen.getDescripcion());
        copia.setUbicacion(origen.getUbicacion());
        copia.setCantidad(origen.getCantidad());
        copia.setPlu(origen.isPlu());
        return copia;
    }

    private void refrescarTabla() {
        String[] columnas = {"Lista", "Precio", "Código", "Descripción", "Ubicación", "Cantidad", "PLU", "Utilidad", "% Utilidad"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == COL_PLU ? Boolean.class : Object.class;
            }
        };

        modelo.addRow(fila(listaPrincipal, codigoProducto, descripcionProducto, "1", false));
        for (ModeloListaPrecio lista : listas) {
            modelo.addRow(fila(lista, lista.getCodigo(), lista.getDescripcion(),
                    lista.getCantidad().stripTrailingZeros().toPlainString(), lista.isPlu()));
        }

        tabla.setModel(modelo);
        tabla.getColumnModel().getColumn(COL_LISTA).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(COL_PRECIO).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(COL_CODIGO).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(COL_DESCRIPCION).setPreferredWidth(220);
        tabla.getColumnModel().getColumn(COL_UBICACION).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(COL_CANTIDAD).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(COL_PLU).setPreferredWidth(45);
        tabla.getColumnModel().getColumn(COL_UTILIDAD).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(COL_PORCENTAJE).setPreferredWidth(80);
    }

    private Object[] fila(ModeloListaPrecio lista, String codigo, String descripcion, String cantidad, boolean plu) {
        String utilidad;
        String porcentaje;
        if (costoBase.signum() == 0) {
            utilidad = big.setMoneda(BigDecimal.ZERO);
            porcentaje = "% 0";
        } else {
            utilidad = big.setMoneda(lista.getUtilidad(costoBase));
            porcentaje = "% " + lista.getPorcentajeUtilidad(costoBase).doubleValue();
        }

        return new Object[]{lista.getNombre(), big.setMoneda(lista.getPrecio()), codigo, descripcion, lista.getUbicacion(), cantidad, plu, utilidad, porcentaje};
    }

    private class DialogoListaPrecio extends javax.swing.JDialog {

        private final ModeloListaPrecio lista;
        private final boolean esNueva;
        private final boolean esPrincipal;
        private boolean aceptado;

        private final javax.swing.JTextField txtPrecio = new javax.swing.JTextField(14);
        private final javax.swing.JTextField txtCodigo = new javax.swing.JTextField(14);
        private final javax.swing.JTextField txtDescripcion = new javax.swing.JTextField(28);
        private final javax.swing.JTextField txtUbicacion = new javax.swing.JTextField(14);
        private final javax.swing.JTextField txtCantidad = new javax.swing.JTextField(8);
        private final javax.swing.JCheckBox chkPlu = new javax.swing.JCheckBox("PLU (presentación o paquete con código propio)");
        private final javax.swing.JLabel lbUtilidad = new javax.swing.JLabel(" ");

        DialogoListaPrecio(ModeloListaPrecio lista, boolean esNueva) {
            super((java.awt.Frame) null, true);
            this.lista = lista;
            this.esNueva = esNueva;
            this.esPrincipal = lista.getNumeroLista() == ModeloListaPrecio.LISTA_PRINCIPAL;

            setTitle((esNueva ? "Agregar lista " : "Modificar lista ") + lista.getNombre());
            construir();
            cargar();
            pack();
            setLocationRelativeTo(null);
        }

        private void construir() {
            JPanel contenido = new JPanel(new java.awt.GridBagLayout());
            contenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
            java.awt.GridBagConstraints c = new java.awt.GridBagConstraints();
            c.insets = new java.awt.Insets(3, 3, 3, 3);
            c.anchor = java.awt.GridBagConstraints.WEST;
            c.fill = java.awt.GridBagConstraints.HORIZONTAL;

            agregarFila(contenido, c, 0, "Precio *", txtPrecio);
            agregarFila(contenido, c, 1, "Código", txtCodigo);
            agregarFila(contenido, c, 2, "Descripción", txtDescripcion);
            agregarFila(contenido, c, 3, "Ubicación", txtUbicacion);
            agregarFila(contenido, c, 4, "Cantidad (paquete)", txtCantidad);

            c.gridx = 1;
            c.gridy = 5;
            contenido.add(chkPlu, c);
            c.gridy = 6;
            contenido.add(lbUtilidad, c);

            JButton btnGuardar = new JButton("Guardar");
            JButton btnCancelar = new JButton("Cancelar");
            btnGuardar.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    if (validarYAplicar()) {
                        aceptado = true;
                        dispose();
                    }
                }
            });
            btnCancelar.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            });

            JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            botones.add(btnCancelar);
            botones.add(btnGuardar);

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(contenido, BorderLayout.CENTER);
            getContentPane().add(botones, BorderLayout.SOUTH);
            getRootPane().setDefaultButton(btnGuardar);

            txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyReleased(java.awt.event.KeyEvent e) {
                    mostrarUtilidad();
                }
            });
            chkPlu.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    actualizarHabilitados();
                }
            });

            getRootPane().registerKeyboardAction(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dispose();
                }
            }, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), JComponent_WHEN_IN_FOCUSED_WINDOW);
        }

        private void agregarFila(JPanel panel, java.awt.GridBagConstraints c, int fila, String texto, java.awt.Component campo) {
            c.gridx = 0;
            c.gridy = fila;
            c.weightx = 0;
            panel.add(new javax.swing.JLabel(texto), c);
            c.gridx = 1;
            c.weightx = 1;
            panel.add(campo, c);
        }

        private void cargar() {
            txtPrecio.setText(big.setMoneda(lista.getPrecio()));
            txtCodigo.setText(lista.getCodigo() == null ? "" : lista.getCodigo());
            txtDescripcion.setText(lista.getDescripcion() == null ? "" : lista.getDescripcion());
            txtUbicacion.setText(lista.getUbicacion() == null ? "" : lista.getUbicacion());
            txtCantidad.setText(lista.getCantidad().stripTrailingZeros().toPlainString());
            chkPlu.setSelected(lista.isPlu());
            actualizarHabilitados();
            mostrarUtilidad();
        }

        private void actualizarHabilitados() {
            txtCodigo.setEnabled(!esPrincipal);
            txtDescripcion.setEnabled(!esPrincipal);
            chkPlu.setEnabled(!esPrincipal);
            txtCantidad.setEnabled(!esPrincipal && chkPlu.isSelected());
            if (esPrincipal || !chkPlu.isSelected()) {
                txtCantidad.setText("1");
            }
        }

        private void mostrarUtilidad() {
            try {
                BigDecimal precio = big.getMoneda(txtPrecio.getText());
                if (costoBase.signum() == 0) {
                    lbUtilidad.setText("Utilidad: sin costo");
                } else {
                    lbUtilidad.setText("Utilidad: " + big.setMoneda(precio.subtract(costoBase)) + "   % "
                            + precio.subtract(costoBase).divide(costoBase, 2, java.math.RoundingMode.HALF_UP).multiply(new BigDecimal("100")).doubleValue());
                }
            } catch (Exception e) {
                lbUtilidad.setText(" ");
            }
        }

        private boolean validarYAplicar() {
            BigDecimal precio;
            try {
                precio = big.getMoneda(txtPrecio.getText().trim());
            } catch (Exception e) {
                ControladorAlertas.alert("El precio no es válido");
                txtPrecio.requestFocus();
                return false;
            }

            if (precio.signum() < 0) {
                ControladorAlertas.alert("El precio no puede ser negativo");
                return false;
            }

            if (esPrincipal && precio.signum() == 0) {
                ControladorAlertas.alert("Debe ingresar el precio de la lista 1");
                return false;
            }

            BigDecimal cantidad = BigDecimal.ONE;
            String codigo = txtCodigo.getText().trim();

            if (!esPrincipal) {
                if (chkPlu.isSelected()) {
                    try {
                        cantidad = new BigDecimal(txtCantidad.getText().trim().replace(",", "."));
                    } catch (NumberFormatException e) {
                        ControladorAlertas.alert("Ingrese la cantidad del paquete");
                        txtCantidad.requestFocus();
                        return false;
                    }

                    if (cantidad.signum() <= 0) {
                        ControladorAlertas.alert("La cantidad del paquete debe ser mayor a cero");
                        return false;
                    }

                    if (codigo.isEmpty()) {
                        ControladorAlertas.alert("Un PLU necesita un código");
                        txtCodigo.requestFocus();
                        return false;
                    }
                }

                if (!codigo.isEmpty() && codigoEnUso(codigo)) {
                    return false;
                }
            }

            lista.setPrecio(precio);
            lista.setUbicacion(txtUbicacion.getText().trim());
            if (!esPrincipal) {
                lista.setCodigo(codigo.isEmpty() ? null : codigo);
                lista.setDescripcion(txtDescripcion.getText().trim());
                lista.setCantidad(cantidad);
                lista.setPlu(chkPlu.isSelected());
            }

            return true;
        }

        private boolean codigoEnUso(String codigo) {
            if (codigo.equalsIgnoreCase(codigoProducto)) {
                ControladorAlertas.alert("El código ya es el del producto");
                return true;
            }

            for (ModeloListaPrecio otra : listas) {
                if (otra.getNumeroLista() != lista.getNumeroLista() && otra.getCodigo() != null && otra.getCodigo().equalsIgnoreCase(codigo)) {
                    ControladorAlertas.alert("El código ya lo usa la lista " + otra.getNombre());
                    return true;
                }
            }

            if (daoListaPrecio.obtenerProductoConCodigo(codigo, idSistema) != null) {
                ControladorAlertas.alert("El código del PLU ya existe en otro producto");
                return true;
            }

            ModeloProducto existente = daoProducto.obtenerDatosProducto(codigo, enumBodegaPrincipal());
            if (existente.getIdSistema() != null && !existente.getIdSistema().equals(idSistema)) {
                ControladorAlertas.alert("El código del PLU ya existe en otro producto");
                return true;
            }

            return false;
        }

        boolean mostrar() {
            setVisible(true);
            return aceptado;
        }

        ModeloListaPrecio getResultado() {
            return lista;
        }
    }

    private static final int JComponent_WHEN_IN_FOCUSED_WINDOW = javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

    private static String enumBodegaPrincipal() {
        return Bodegas.predeterminada();
    }
}
