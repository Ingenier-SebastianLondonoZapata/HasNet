package Vista.Restaurante;

import Controlador.Alertas.ControladorAlertas;
import Enums.TipoDocumento;
import Utilidades.Utilidades;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VistaGruposProductos extends JDialog {

    private final Instancias instancias;
    private final metodosGenerales metodos = new metodosGenerales();
    private final String tipoProceso;

    private DefaultTableModel modeloProductos;
    private TableRowSorter<DefaultTableModel> sorter;
    private JTable tblProductos;
    private JLabel lblGrupo;
    private JTextField txtBuscar;
    private JButton btnCargar;
    private PanelGrupos panelGruposRef;

    private final Map<String, Object[]> seleccionGlobal = new LinkedHashMap<>();

    public VistaGruposProductos(Frame parent, boolean modal, String tipoProceso) {
        super(parent, modal);
        this.tipoProceso = tipoProceso;
        instancias = Instancias.getInstancias();

        setTitle("Productos por grupo");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBackground(Color.WHITE);

        construirUI();
        cargarGrupos();
        registrarAtajos();

        setSize(1050, 660);
        setLocationRelativeTo(parent);
    }

    private void construirUI() {
        JScrollPane scrollGrupos = construirPanelGrupos();
        JPanel panelDerecho = construirPanelProductos();

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollGrupos, panelDerecho);
        split.setDividerLocation(430);
        split.setDividerSize(3);
        split.setBorder(BorderFactory.createEmptyBorder());
        split.setBackground(Color.WHITE);

        getContentPane().setBackground(Color.WHITE);
        getContentPane().add(split);
    }

    private JScrollPane construirPanelGrupos() {
        PanelGrupos panelGrupos = new PanelGrupos();
        panelGrupos.setListener(new PanelGrupos.ListenerGrupo() {
            @Override
            public void grupoSeleccionado(String codigo, String nombre) {
                cargarProductosGrupo(codigo, nombre);
            }
        });
        this.panelGruposRef = panelGrupos;

        JScrollPane scroll = new JScrollPane(panelGrupos);
        scroll.setPreferredSize(new Dimension(430, 600));
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JPanel construirPanelProductos() {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(14, 12, 14, 16));

        lblGrupo = new JLabel("Seleccione un grupo");
        lblGrupo.setFont(new Font("Century Gothic", Font.BOLD, 22));
        lblGrupo.setHorizontalAlignment(SwingConstants.CENTER);
        lblGrupo.setForeground(new Color(50, 50, 60));

        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        txtBuscar.setPreferredSize(new Dimension(0, 38));
        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (sorter != null) {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtBuscar.getText(), 1));
                }
            }
        });

        tblProductos = construirTablaProductos();
        JScrollPane scrollProductos = new JScrollPane(tblProductos);

        JPanel centro = new JPanel(new BorderLayout(0, 6));
        centro.setBackground(Color.WHITE);
        centro.add(txtBuscar, BorderLayout.NORTH);
        centro.add(scrollProductos, BorderLayout.CENTER);

        panel.add(lblGrupo, BorderLayout.NORTH);
        panel.add(centro, BorderLayout.CENTER);
        panel.add(construirBotones(), BorderLayout.SOUTH);
        return panel;
    }

    private JTable construirTablaProductos() {
        modeloProductos = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Codigo", "Descripcion", "Valor", "Cant"}
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloProductos);
        tabla.setFont(new Font("Century Gothic", Font.PLAIN, 17));
        tabla.setRowHeight(38);
        tabla.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 13));
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setSelectionBackground(new Color(52, 152, 219, 60));
        tabla.setGridColor(new Color(230, 230, 235));

        tabla.getColumnModel().getColumn(0).setMinWidth(0);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(0).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabla.getColumnModel().getColumn(2).setMaxWidth(130);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(3).setMaxWidth(80);

        sorter = new TableRowSorter<>(modeloProductos);
        tabla.setRowSorter(sorter);

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int viewRow = tblProductos.getSelectedRow();
                if (viewRow < 0) {
                    return;
                }
                aumentar(viewRow);
            }
        });

        tabla.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int viewRow = tblProductos.getSelectedRow();
                if (viewRow < 0) {
                    return;
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DELETE:
                    case KeyEvent.VK_BACK_SPACE:
                        int modelRowDel = tblProductos.convertRowIndexToModel(viewRow);
                        String codigoDel = modeloProductos.getValueAt(modelRowDel, 0).toString();
                        modeloProductos.setValueAt("", modelRowDel, 3);
                        seleccionGlobal.remove(codigoDel);
                        actualizarBotonCargar();
                        break;
                    case KeyEvent.VK_SUBTRACT:
                    case KeyEvent.VK_MINUS:
                        disminuir(viewRow);
                        break;
                }
            }
        });

        return tabla;
    }

    private JPanel construirBotones() {
        btnCargar = new JButton("CARGAR");
        btnCargar.setFont(new Font("Century Gothic", Font.BOLD, 17));
        btnCargar.setBackground(new Color(46, 204, 113));
        btnCargar.setForeground(Color.BLACK);
        btnCargar.setFocusPainted(false);
        btnCargar.setBorderPainted(false);
        btnCargar.setOpaque(true);
        btnCargar.setPreferredSize(new Dimension(180, 48));
        btnCargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargar();
            }
        });

        JButton btnMenos = new JButton("-1");
        btnMenos.setFont(new Font("Century Gothic", Font.BOLD, 15));
        btnMenos.setPreferredSize(new Dimension(70, 48));
        btnMenos.setFocusPainted(false);
        btnMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int viewRow = tblProductos.getSelectedRow();
                if (viewRow >= 0) {
                    disminuir(viewRow);
                }
            }
        });

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
        panel.setBackground(Color.WHITE);
        panel.add(btnMenos);
        panel.add(btnCargar);
        return panel;
    }

    private void registrarAtajos() {
        getRootPane().registerKeyboardAction(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cargar();
                    }
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
        getRootPane().registerKeyboardAction(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                },
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    // ─── Lógica ────────────────────────────────────────────────────────────────
    private void cargarGrupos() {
        Object[][] datos = instancias.getSql().getGruposVisualizarFactura();
        panelGruposRef.setGrupos(datos);
    }

    public void seleccionarGrupo(String codigo, String nombre) {
        cargarProductosGrupo(codigo, nombre);
    }

    private void cargarProductosGrupo(String codigo, String nombre) {
        lblGrupo.setText(nombre);
        txtBuscar.setText("");
        if (sorter != null) {
            sorter.setRowFilter(null);
        }

        while (modeloProductos.getRowCount() > 0) {
            modeloProductos.removeRow(0);
        }

        boolean conImpuestos = instancias.isPvpConIva();
        Object[][] prods = instancias.getSql().getDatosProductosGrupo(codigo, conImpuestos);

        for (int i = 0; i < prods.length; i++) {
            String codProd = prods[i][0].toString();
            String desc = prods[i][1].toString();
            String valorStr = big.setMonedaExacta(big.getBigDecimal(prods[i][3]));

            // Restaurar cantidad si ya fue seleccionada en esta sesión
            Object cantCell = "";
            if (seleccionGlobal.containsKey(codProd)) {
                cantCell = seleccionGlobal.get(codProd)[0];
            }
            modeloProductos.addRow(new Object[]{codProd, desc, valorStr, cantCell});
        }
        txtBuscar.requestFocus();
    }

    private void aumentar(int viewRow) {
        int modelRow = tblProductos.convertRowIndexToModel(viewRow);
        String codigo = modeloProductos.getValueAt(modelRow, 0).toString();
        String desc = modeloProductos.getValueAt(modelRow, 1).toString();
        String valor = modeloProductos.getValueAt(modelRow, 2).toString();

        int cant;
        Object actual = modeloProductos.getValueAt(modelRow, 3);
        try {
            cant = Integer.parseInt(actual.toString());
        } catch (NumberFormatException e) {
            cant = 0;
        }
        cant++;

        modeloProductos.setValueAt(cant, modelRow, 3);
        seleccionGlobal.put(codigo, new Object[]{cant, desc, valor});
        actualizarBotonCargar();
    }

    private void disminuir(int viewRow) {
        int modelRow = tblProductos.convertRowIndexToModel(viewRow);
        String codigo = modeloProductos.getValueAt(modelRow, 0).toString();

        int cant;
        Object actual = modeloProductos.getValueAt(modelRow, 3);
        try {
            cant = Integer.parseInt(actual.toString());
        } catch (NumberFormatException e) {
            cant = 0;
        }
        cant--;

        if (cant <= 0) {
            modeloProductos.setValueAt("", modelRow, 3);
            seleccionGlobal.remove(codigo);
        } else {
            modeloProductos.setValueAt(cant, modelRow, 3);
            Object[] entrada = seleccionGlobal.get(codigo);
            if (entrada != null) {
                entrada[0] = cant;
            }
        }
        actualizarBotonCargar();
    }

    private void actualizarBotonCargar() {
        int totalItems = 0;
        for (Object[] v : seleccionGlobal.values()) {
            try {
                totalItems += (Integer) v[0];
            } catch (Exception e) {
            }
        }
        if (totalItems > 0) {
            btnCargar.setText("CARGAR (" + totalItems + ")");
        } else {
            btnCargar.setText("CARGAR");
        }
    }

    private void cargar() {
        if (seleccionGlobal.isEmpty()) {
            ControladorAlertas.bigAlert("Debe seleccionar al menos un producto (haga click en los productos deseados)");
            return;
        }

        for (Map.Entry<String, Object[]> entry : seleccionGlobal.entrySet()) {
            String codigo = entry.getKey();
            BigDecimal cantidad = Utilidades.convertirBigDecimal(entry.getValue()[0].toString());
            cargarEnDocumento(codigo, cantidad);
        }

        dispose();
    }

    private void cargarEnDocumento(String codigo, BigDecimal cantidad) {
        if (TipoDocumento.MESA.getValor().equals(this.tipoProceso)) {
            instancias.getMesa().getPnlFactura().cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
        } else if (TipoDocumento.PEDIDO.getValor().equals(tipoProceso)) {
            instancias.getPedido().cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
        } else if (TipoDocumento.COTIZACION.getValor().equals(tipoProceso)) {
            instancias.getCotiza().cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
        } else if (TipoDocumento.PLAN_SEPARE.getValor().equals(tipoProceso)) {
            instancias.getPlanSepare().cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
        } else if (TipoDocumento.ORDER_SERVICIO.getValor().equals(tipoProceso)) {
            instancias.getOrdenServicio().cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
        } else if (TipoDocumento.FACTURACION.getValor().equals(tipoProceso)) {
            instancias.getFactura().cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
        }
    }
}
