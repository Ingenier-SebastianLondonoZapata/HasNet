package Vista.Tesoreria;

import Consumidor.DocumentoSoporte.consumidorDocumentoSoporte;
import Controlador.Alertas.ControladorAlertas;
import DAO.Configuraciones.DaoResoluciones;
import DAO.Egresos.DaoEgresos;
import Enums.enumTipoDocumento;
import Modelo.DocumentoSoporte.Entrada.ModeloDocumentoSoporte;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.Maestra.ModeloResolucion;
import clases.Egresos.ndEgreso;
import Modelo.Egresos.ModeloDetalleEgreso;
import clases.Instancias;
import Modelo.Terceros.ModeloContacto;
import Utilidades.Constantes;
import Utilidades.DocumentosElectronicos;
import Utilidades.Numeros;
import Utilidades.Utilidades;
import Validaciones.DocumentoSoporte.squemaDocumentoSoporte;
import Validaciones.Tesoreria.squemaEgresos;
import clases.big;
import clases.convertirNumeroALetras;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import formularios.Tesoreria.buscEgresos;
import formularios.Tesoreria.dlgMovimientosBanco;
import formularios.terceros.buscClientes;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class vistaEgresos extends javax.swing.JInternalFrame {

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();
    private final DaoEgresos daoEgresos = new DaoEgresos();

    private final DocumentosElectronicos documentosElectronicos = new DocumentosElectronicos();
    private final consumidorDocumentoSoporte consumidorDocumentoSoporte = new consumidorDocumentoSoporte();
    private final squemaDocumentoSoporte squemaDocumentoSoporte = new squemaDocumentoSoporte();
    private final ControladorAlertas alertas = new ControladorAlertas();
    private final squemaEgresos squemaEgresos = new squemaEgresos();
    private ModeloContacto DATOS_CLIENTE_CARGADO = null;

    metodosGenerales metodos = new metodosGenerales();
    private Instancias instancias;
    private Boolean mensaje = true;
    private JComponent Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
    private Dimension dimBarra = null;
    private TreeMap<String, String[]> codigos;
    convertirNumeroALetras convertirNumeroALetras = new convertirNumeroALetras();
    String ingresoAsociado = "", tipoMovimiento = "", consecutivoBanco = "", simbolo = "";
    Boolean saltarPasos = false, cancelarEgreso = false;
    DecimalFormat df = new DecimalFormat("#.00");

    public String getConsecutivoBanco() {
        return consecutivoBanco;
    }

    public void setConsecutivoBanco(String consecutivoBanco) {
        this.consecutivoBanco = consecutivoBanco;
    }

    public String getIngresoAsociado() {
        return ingresoAsociado;
    }

    public void setIngresoAsociado(String ingresoAsociado) {
        this.ingresoAsociado = ingresoAsociado;
    }

    public Boolean getSaltarPasos() {
        return saltarPasos;
    }

    public void setSaltarPasos(Boolean saltarPasos) {
        this.saltarPasos = saltarPasos;
    }

    public Boolean getCancelarEgreso() {
        return cancelarEgreso;
    }

    public void setCancelarEgreso(Boolean cancelarEgreso) {
        this.cancelarEgreso = cancelarEgreso;
    }

    public vistaEgresos() {

        initComponents();
        codigos = new TreeMap();

        instancias = Instancias.getInstancias();

        simbolo = instancias.getSimbolo();
        txtCheque.setText(this.simbolo + " 0");
        txtBanco.setText(this.simbolo + " 0");

        Barra = ((javax.swing.plaf.basic.BasicInternalFrameUI) getUI()).getNorthPane();
        dimBarra = Barra.getPreferredSize();
        Barra.setSize(0, 0);
        Barra.setPreferredSize(new Dimension(0, 0));
        setBorder(null);
        repaint();

        btnLimpiarActionPerformed(null);
        Object[][] codigos = instancias.getSql().getCodsEgresos();

        pnlFormulario.registerKeyboardAction(accion("guardar"), "guardar", KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_L, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("proveedor"), "proveedor", KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        pnlFormulario.registerKeyboardAction(accion("terceros"), "terceros", KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        actualizarTablaResoluciones();
        establecerTipoImpresion();
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "limpiar":
                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
                            btnLimpiarActionPerformed(null);
                        }
                        break;
                    case "guardar":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
//                    case "proveedor":
//                        if ((btnBuscTerceros1.isEnabled()) && (btnBuscTerceros1.isVisible())) {
//                            btnBuscTerceros1ActionPerformed(null);
//                        }
//                        break;
                    case "terceros":
                        if ((btnBuscTerceros.isEnabled()) && (btnBuscTerceros.isVisible())) {
                            btnBuscTercerosActionPerformed(null);
                        }
                        break;
                }
            }
        };
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        scrFormulario = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        pnlCliente = new javax.swing.JPanel();
        txtTelefono = new javax.swing.JTextField();
        lbTelefono = new javax.swing.JLabel();
        lbRazon = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lbDireccion = new javax.swing.JLabel();
        lbNit = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        pnlValores = new javax.swing.JPanel();
        lbBanco = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        lbEfectivo = new javax.swing.JLabel();
        lbCheque = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        txtCheque = new javax.swing.JTextField();
        lbLetras4 = new javax.swing.JLabel();
        cmbTipoImpresion = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEgresos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbLetras = new javax.swing.JLabel();
        txtTotalLetras = new javax.swing.JTextField();
        lbLetras8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnBuscTerceros2 = new javax.swing.JButton();
        btnBuscTerceros3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbLetras2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        lbLetras9 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JLabel();
        lbLetras3 = new javax.swing.JLabel();
        txtIVA = new javax.swing.JLabel();
        lbLetras1 = new javax.swing.JLabel();
        cmbTipoEgreso = new javax.swing.JComboBox();
        lbLetras5 = new javax.swing.JLabel();
        lbNoEgreso = new javax.swing.JLabel();
        jtblComprobantes = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setTitle("Egreso");

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        pnlCliente.setBackground(new java.awt.Color(255, 255, 255));
        pnlCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del egreso", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        txtTelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTelefono.setEnabled(false);
        txtTelefono.setName("Teléfono"); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        lbTelefono.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTelefono.setText("Teléfono:");

        lbRazon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbRazon.setText("Razón social:");

        txtDireccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtDireccion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDireccion.setEnabled(false);
        txtDireccion.setName("Dirección"); // NOI18N
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });

        lbDireccion.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbDireccion.setText("Dirección:");

        lbNit.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbNit.setText("CC/Nit:");
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtNombreCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNombreCliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombreCliente.setEnabled(false);
        txtNombreCliente.setName("Razón social"); // NOI18N
        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyReleased(evt);
            }
        });

        txtIdCliente.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIdCliente.setName("CC/NIT"); // NOI18N
        txtIdCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdClienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdClienteKeyTyped(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlClienteLayout = new javax.swing.GroupLayout(pnlCliente);
        pnlCliente.setLayout(pnlClienteLayout);
        pnlClienteLayout.setHorizontalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addComponent(txtNombreCliente)
                    .addComponent(txtDireccion)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(5, 5, 5))
        );
        pnlClienteLayout.setVerticalGroup(
            pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClienteLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscTerceros, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCliente)
                    .addComponent(lbRazon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClienteLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lbTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pnlValores.setBackground(new java.awt.Color(255, 255, 255));
        pnlValores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma de pago", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbBanco.setBackground(new java.awt.Color(255, 255, 255));
        lbBanco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbBanco.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbBanco.setText("Banco");
        lbBanco.setOpaque(true);

        txtBanco.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtBanco.setName("Banco"); // NOI18N
        txtBanco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBancoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBancoKeyTyped(evt);
            }
        });

        lbEfectivo.setBackground(new java.awt.Color(255, 255, 255));
        lbEfectivo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbEfectivo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbEfectivo.setText("Efectivo");
        lbEfectivo.setOpaque(true);

        lbCheque.setBackground(new java.awt.Color(255, 255, 255));
        lbCheque.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbCheque.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCheque.setText("Cheque");
        lbCheque.setOpaque(true);

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtEfectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEfectivoActionPerformed(evt);
            }
        });

        txtCheque.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtCheque.setName("Cheque"); // NOI18N
        txtCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChequeActionPerformed(evt);
            }
        });
        txtCheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChequeKeyTyped(evt);
            }
        });

        lbLetras4.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lbLetras4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbLetras4.setText("Impresión");
        lbLetras4.setOpaque(true);

        cmbTipoImpresion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoImpresion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CARTA", "POS" }));
        cmbTipoImpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoImpresionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlValoresLayout = new javax.swing.GroupLayout(pnlValores);
        pnlValores.setLayout(pnlValoresLayout);
        pnlValoresLayout.setHorizontalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlValoresLayout.createSequentialGroup()
                        .addComponent(lbLetras4, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(cmbTipoImpresion, 0, 114, Short.MAX_VALUE))
                    .addGroup(pnlValoresLayout.createSequentialGroup()
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbBanco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbCheque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbEfectivo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                        .addGap(5, 5, 5)
                        .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCheque, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(txtBanco)
                            .addComponent(txtEfectivo))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlValoresLayout.setVerticalGroup(
            pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlValoresLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLetras4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(pnlValoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        tblEgresos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblEgresos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "CONCEPTO", "DESCRIPCIÓN", "SUBTOTAL", "% IVA", "IVA", "TOTAL", "FACTURA", "CODIGOINTERNO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEgresos.setComponentPopupMenu(jPopupMenu1);
        tblEgresos.setRowHeight(24);
        tblEgresos.getTableHeader().setReorderingAllowed(false);
        tblEgresos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblEgresosMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblEgresosMouseReleased(evt);
            }
        });
        tblEgresos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblEgresosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblEgresosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblEgresos);
        if (tblEgresos.getColumnModel().getColumnCount() > 0) {
            tblEgresos.getColumnModel().getColumn(0).setMinWidth(0);
            tblEgresos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEgresos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblEgresos.getColumnModel().getColumn(3).setMinWidth(100);
            tblEgresos.getColumnModel().getColumn(3).setPreferredWidth(130);
            tblEgresos.getColumnModel().getColumn(3).setMaxWidth(150);
            tblEgresos.getColumnModel().getColumn(4).setMinWidth(60);
            tblEgresos.getColumnModel().getColumn(4).setPreferredWidth(60);
            tblEgresos.getColumnModel().getColumn(4).setMaxWidth(60);
            tblEgresos.getColumnModel().getColumn(5).setMinWidth(100);
            tblEgresos.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblEgresos.getColumnModel().getColumn(5).setMaxWidth(120);
            tblEgresos.getColumnModel().getColumn(6).setMinWidth(100);
            tblEgresos.getColumnModel().getColumn(6).setPreferredWidth(130);
            tblEgresos.getColumnModel().getColumn(6).setMaxWidth(150);
            tblEgresos.getColumnModel().getColumn(7).setMinWidth(75);
            tblEgresos.getColumnModel().getColumn(7).setPreferredWidth(100);
            tblEgresos.getColumnModel().getColumn(7).setMaxWidth(125);
            tblEgresos.getColumnModel().getColumn(8).setMinWidth(0);
            tblEgresos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblEgresos.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbLetras.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbLetras.setText("Total (en letras)");
        lbLetras.setOpaque(true);

        txtTotalLetras.setEditable(false);
        txtTotalLetras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalLetrasActionPerformed(evt);
            }
        });

        lbLetras8.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbLetras8.setText("Concepto:");
        lbLetras8.setOpaque(true);

        txtCodigo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCodigo.setName("combo"); // NOI18N
        txtCodigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoFocusGained(evt);
            }
        });
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbLetras8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbLetras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTotalLetras))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLetras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotalLetras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(lbLetras8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("GUARDAR  ");
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscTerceros2.setBackground(new java.awt.Color(241, 148, 138));
        btnBuscTerceros2.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar2.png"))); // NOI18N
        btnBuscTerceros2.setText("ANULAR  ");
        btnBuscTerceros2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros2.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros2ActionPerformed(evt);
            }
        });

        btnBuscTerceros3.setBackground(new java.awt.Color(247, 220, 111));
        btnBuscTerceros3.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        btnBuscTerceros3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnBuscTerceros3.setText("REIMPRIMIR");
        btnBuscTerceros3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBuscTerceros3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros3.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTerceros3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(255, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscTerceros3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscTerceros2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnBuscTerceros3)
                    .addComponent(btnBuscTerceros2))
                .addGap(5, 5, 5))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Totales del egreso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        lbLetras2.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbLetras2.setText("TOTAL");
        lbLetras2.setOpaque(true);

        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txtTotal.setText("0");
        txtTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTotal.setOpaque(true);

        lbLetras9.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbLetras9.setText("Subtotal");
        lbLetras9.setOpaque(true);

        txtSubTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtSubTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtSubTotal.setText("0");
        txtSubTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtSubTotal.setOpaque(true);

        lbLetras3.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbLetras3.setText("IVA");
        lbLetras3.setOpaque(true);

        txtIVA.setBackground(new java.awt.Color(255, 255, 255));
        txtIVA.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtIVA.setText("0");
        txtIVA.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtIVA.setOpaque(true);

        lbLetras1.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbLetras1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbLetras1.setText("T. Egreso:");
        lbLetras1.setOpaque(true);

        cmbTipoEgreso.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cmbTipoEgreso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "LOCAL", "GENERAL" }));
        cmbTipoEgreso.setName("Tipo"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbLetras1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLetras2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLetras9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLetras3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
                    .addComponent(txtIVA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoEgreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbLetras2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lbLetras9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIVA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbLetras3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbLetras1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbLetras5.setBackground(new java.awt.Color(255, 255, 255));
        lbLetras5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lbLetras5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbLetras5.setText("Egreso No.");
        lbLetras5.setOpaque(true);

        lbNoEgreso.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lbNoEgreso.setForeground(new java.awt.Color(255, 0, 0));
        lbNoEgreso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNoEgreso.setText("1");
        lbNoEgreso.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblComprobantes.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tblComprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Comprobante", "", "Resolucion", "fechaFin", "desde", "hasta", "tipo", "prefijo", "conse", "plantilla"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblComprobantes.setRowHeight(25);
        tblComprobantes.getTableHeader().setResizingAllowed(false);
        tblComprobantes.getTableHeader().setReorderingAllowed(false);
        tblComprobantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tblComprobantesMouseExited(evt);
            }
        });
        jtblComprobantes.setViewportView(tblComprobantes);
        if (tblComprobantes.getColumnModel().getColumnCount() > 0) {
            tblComprobantes.getColumnModel().getColumn(0).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(0).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(2).setMinWidth(20);
            tblComprobantes.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblComprobantes.getColumnModel().getColumn(2).setMaxWidth(20);
            tblComprobantes.getColumnModel().getColumn(3).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(3).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(4).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(4).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(4).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(5).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(5).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(6).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(6).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(7).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(7).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(8).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(8).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(9).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(9).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(9).setMaxWidth(0);
            tblComprobantes.getColumnModel().getColumn(10).setMinWidth(0);
            tblComprobantes.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblComprobantes.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlFormularioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(pnlCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(pnlValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlFormularioLayout.createSequentialGroup()
                                .addComponent(lbLetras5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNoEgreso, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jtblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(jtblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbLetras5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNoEgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(pnlValores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        scrFormulario.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrFormulario)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrFormulario)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteKeyTyped

    }//GEN-LAST:event_txtIdClienteKeyTyped

    private void txtIdClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtIdCliente.getText());
        } else if (!txtNombreCliente.getText().equals("")) {
            txtNombreCliente.setText("");
            DATOS_CLIENTE_CARGADO = null;
            txtTelefono.setText("");
            txtDireccion.setText("");
        }
    }//GEN-LAST:event_txtIdClienteKeyReleased

    private void txtNombreClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreClienteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtTelefono.requestFocus();
        }
    }//GEN-LAST:event_txtNombreClienteKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased

    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased

    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChequeActionPerformed

    private void tblEgresosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEgresosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            int filaSeleccionada = tblEgresos.getSelectedRow();
            if (filaSeleccionada < 0) {
                return;
            }

            switch (tblEgresos.getSelectedColumn()) {
                case 2:
                    tblEgresos.editCellAt(filaSeleccionada, 3);
                    tblEgresos.setColumnSelectionInterval(3, 3);
                    tblEgresos.transferFocus();
                    break;
                case 3:
                    tblEgresos.editCellAt(filaSeleccionada, 4);
                    tblEgresos.setColumnSelectionInterval(4, 4);
                    tblEgresos.transferFocus();
                    break;
                case 4:
                    tblEgresos.editCellAt(filaSeleccionada, 7);
                    tblEgresos.setColumnSelectionInterval(7, 7);
                    tblEgresos.transferFocus();
                    break;
                case 7:
                    txtCodigo.requestFocus();
                    break;
                default:
                    break;
            }

            calcularValorFila();
            calcularValores();
        }
    }//GEN-LAST:event_tblEgresosKeyReleased

    private void txtEfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEfectivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoActionPerformed

    private void tblEgresosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblEgresosKeyTyped

    }//GEN-LAST:event_tblEgresosKeyTyped

    private void txtChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyReleased
        if (txtCheque.getText().equals("") || txtCheque.getText().equals(this.simbolo) || txtCheque.getText().equals(this.simbolo + " ")) {
            txtCheque.setText("0");
        }

        txtCheque.setText(big.setMoneda(big.getMoneda(txtCheque.getText())));
        calcularValores();
    }//GEN-LAST:event_txtChequeKeyReleased

    private void txtBancoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyReleased
        if (txtBanco.getText().equals("") || txtBanco.getText().equals(this.simbolo) || txtBanco.getText().equals(this.simbolo + " ")) {
            txtBanco.setText("0");
        }

        txtBanco.setText(big.setMoneda(big.getMoneda(txtBanco.getText())));
        calcularValores();
    }//GEN-LAST:event_txtBancoKeyReleased

    private void txtChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChequeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtChequeKeyTyped

    private void txtBancoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBancoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtBancoKeyTyped

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        tblEgresos.removeEditor();

        if (!saltarPasos) {
            ModeloContacto datosCliente = DATOS_CLIENTE_CARGADO == null ? new ModeloContacto() : DATOS_CLIENTE_CARGADO;
            if (!squemaEgresos.validacionesEgreso(datosCliente, cmbTipoEgreso.getSelectedItem().toString())) {
                return;
            }

            if (Constantes.esDocumentoSoporte(obtenerTipoComprobante())) {
                if (!squemaDocumentoSoporte.validacionesDocumentoSoporte(datosCliente, true)) {
                    return;
                }
            }

            if (!squemaEgresos.validacionesDetalleEgreso(tblEgresos, obtenerTipoComprobante())) {
                return;
            }

            if (metodos.msgPregunta(null, "¿Desea continuar?") != 0) {
                return;
            }
        }

        guardarEgreso();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void guardarEgreso() {

        cancelarEgreso = false;
        generarMovimientoBancario();

        if (cancelarEgreso) {
            return;
        }

        if (!consecutivoBanco.equals("")) {
            ingresoAsociado = consecutivoBanco;
        }

        String prefijo = instancias.getIdEgreso() != null ? instancias.getIdEgreso() : "";
        String consecutivo = instancias.getSql().getNumConsecutivo("EGR")[0].toString();
        String identificadorEgreso = "EGR-" + consecutivo;
        String egreso2 = "EGR-" + prefijo + consecutivo;
        String estado = cmbTipoEgreso.getSelectedIndex() == 2 ? "REALIZADO" : "PENDIENTE";

        if (instancias.getConfiguraciones().isFacturaElectronica() && Constantes.esDocumentoSoporte(obtenerTipoComprobante())) {
            boolean documentoSoporteExitoso = false;
            ModeloDocumentoSoporte modeloDocumentoSoporte = crearModeloDocumentoSoporte(identificadorEgreso, DATOS_CLIENTE_CARGADO);

            try {
                documentoSoporteExitoso = consumidorDocumentoSoporte.generarDocumentoSoporte(modeloDocumentoSoporte, false);
            } catch (Exception ex) {
                System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
            }

            if (!documentoSoporteExitoso) {
                return;
            }
        }

        Object[] vector = {identificadorEgreso, txtIdCliente.getText(), txtNombreCliente.getText(), txtTelefono.getText(),
            txtDireccion.getText(), big.getMoneda(txtTotal.getText()), big.getMoneda(txtSubTotal.getText()), big.getMoneda(txtIVA.getText()),
            DATOS_CLIENTE_CARGADO.getIdSistema(), "", "", "", big.getMoneda(txtCheque.getText()),
            big.getMoneda(txtBanco.getText()), big.getMoneda(txtEfectivo.getText()), metodos.fechaConsulta(metodosGenerales.fecha()),
            identificadorEgreso.replace("EGR-", ""), instancias.getUsuario(), instancias.getTerminal(), estado, cmbTipoEgreso.getSelectedItem(), ingresoAsociado,
            egreso2, ""};

        ndEgreso nodo = metodos.llenarEgreso(vector);

        if (!instancias.getSql().agregarEgreso(nodo)) {

            boolean noPuedaGuardar = false;

            instancias.getSql().eliminarEgreso(identificadorEgreso);
            while (!noPuedaGuardar) {
                noPuedaGuardar = instancias.getSql().eliminarCodEgreso(identificadorEgreso);
            }

            metodos.msgError(this, "Hubo un problema al guardar el egreso");
            return;
        }

        //PROCESO GUARDAR VENTA
        for (int i = 0; i < tblEgresos.getRowCount(); i++) {

            ModeloDetalleEgreso detalleEgreso = obtenerDetalleEgreso(identificadorEgreso, i);

            if (!daoEgresos.agregarDetalleEgreso(detalleEgreso)) {
                boolean noPuedaGuardar = false;
                instancias.getSql().eliminarEgreso(identificadorEgreso);
                while (!noPuedaGuardar) {
                    noPuedaGuardar = instancias.getSql().eliminarCodEgreso(identificadorEgreso);
                }

                ControladorAlertas.alertFail("Error al guardar detalle del egreso");
            }

            /*Object vectCods[] = {identificadorEgreso, tblEgresos.getValueAt(i, 7), tblEgresos.getValueAt(i, 2),
                big.getMoneda((String) tblEgresos.getValueAt(i, 5)), fact, tblEgresos.getValueAt(i, 0), 
                big.getMoneda((String) tblEgresos.getValueAt(i, 3)), big.getMoneda((String) tblEgresos.getValueAt(i, 4))};*/
            //ModeloDetalleEgreso nodoCods = metodos.llenarEgresoCods(vectCods);
        }

        //CAMBIAR CONSECUTIVO FACTURA
        if (!instancias.getSql().aumentarConsecutivo("EGR", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("EGR")[0]) + 1)) {
            metodos.msgError(this, "Hubo un problema al guardar en el consecutivo del egreso");
        }

        lbNoEgreso.setText((String) instancias.getSql().getNumConsecutivo("EGR")[0]);

        if (!saltarPasos) {
            metodos.msgExito(this, "Egreso exitoso");
        }

        String tipo = "";
        if (cmbTipoImpresion.getSelectedIndex() == 1) {
            tipo = "Pos";
        }

        if (!saltarPasos) {
            if (metodos.msgPregunta(this, "¿Desea imprimir?") == 0) {
                instancias.getReporte().ver_Egreso(identificadorEgreso, instancias.getInformacionEmpresa(), txtTotalLetras.getText(), true, tipo);
            } else {
                instancias.getReporte().ver_Egreso(identificadorEgreso, instancias.getInformacionEmpresa(), txtTotalLetras.getText(), false, tipo);
            }
        } else {
            instancias.getReporte().ver_Egreso(identificadorEgreso, instancias.getInformacionEmpresa(), txtTotalLetras.getText(), false, tipo);
        }

        saltarPasos = false;
        btnLimpiarActionPerformed(null);
    }

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        for (int x = 0; x < pnlCliente.getComponentCount(); x++) {
            if (pnlCliente.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) pnlCliente.getComponent(x);
                textField.setText("");
                textField.setEditable(true);
            }
        }

        for (int x = 0; x < pnlValores.getComponentCount(); x++) {
            if (pnlValores.getComponent(x) instanceof JTextField) {
                JTextField textField = (JTextField) pnlValores.getComponent(x);
                textField.setText("");
            }
        }

        consecutivoBanco = "";

        txtCheque.setText(this.simbolo + " 0");
        txtBanco.setText(this.simbolo + " 0");
        txtEfectivo.setText(this.simbolo + " 0");
        txtSubTotal.setText(this.simbolo + " 0");
        txtIVA.setText(this.simbolo + " 0");
        txtTotal.setText(this.simbolo + " 0");
        ingresoAsociado = "";

        DefaultTableModel x = (DefaultTableModel) tblEgresos.getModel();
        int i, j = tblEgresos.getRowCount();

        for (i = 0; i < j; i++) {
            x.removeRow(0);
        }

        cmbTipoEgreso.setSelectedIndex(0);
        txtTotalLetras.setText("");

        String prefijo = "";
        if (instancias.getIdEgreso() != null) {
            prefijo = instancias.getIdEgreso();
        }
        lbNoEgreso.setText(prefijo + (String) instancias.getSql().getNumConsecutivo("EGR")[0]);
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed

        if (tblEgresos.getSelectedRow() > -1) {
            int fila = tblEgresos.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tblEgresos.getModel();
            modelo.removeRow(fila);

            calcularValores();
        } else {
            metodos.msgAdvertencia(this, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void btnBuscTerceros3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros3ActionPerformed
        String consecutivo = "EGR-" + metodos.msgIngresarEnter(this, "Documento a reimprimir");

        if (consecutivo.equals("EGR-")) {
            return;
        }

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdEgreso", "Where egreso ='" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(this, "El egreso no existe");
            return;
        }

        if (anulado) {
            metodos.msgError(this, "El egreso ya esta anulado");
            return;
        }

        ndEgreso nodo = instancias.getSql().getDatosEgreso(consecutivo);

        if (nodo.getId() == null) {
            metodos.msgError(this, "Este egreso no existe.");
            return;
        }

        String tipo = "";
        if (cmbTipoImpresion.getSelectedIndex() == 1) {
            tipo = "Pos";
        }

        instancias.getReporte().ver_Egreso(consecutivo, instancias.getInformacionEmpresa(), convertirNumeroALetras.Convertir(nodo.getTotal()), false, tipo);
    }//GEN-LAST:event_btnBuscTerceros3ActionPerformed

    private void btnBuscTerceros2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTerceros2ActionPerformed
        String consecutivo = "EGR-" + metodos.msgIngresarEnter(this, "Documento a anular");

        if (consecutivo.equals("EGR-")) {
            return;
        }

        boolean anulado = false;
        try {
            anulado = instancias.getSql().getDocumentoAnulado("bdEgreso", "Where egreso = '" + consecutivo + "' ");
        } catch (Exception e) {
            metodos.msgError(this, "El egreso no existe");
            return;
        }

        if (anulado) {
            metodos.msgError(this, "El egreso ya esta anulado");
            return;
        }

        String pago = "";

        try {
            pago = instancias.getSql().getIngresoAsociado(consecutivo);
        } catch (Exception e) {
        }

        String mensaje = "";
        if (!pago.equals("")) {
            mensaje = "Se anulará tambien el " + pago;
        } else {
            mensaje = "¿Anular egreso?";
        }

        metodos.msgAdvertenciaAjustado(this, mensaje);

        if (metodos.msgPregunta(this, "¿Desea continuar?") == 0) {

            if (!instancias.getSql().anularEgreso(consecutivo)) {
                metodos.msgError(this, "Hubo un problema al anular el egreso");
                return;
            }

            if (pago.contains("MOVBANC")) {

                Object[][] mov = instancias.getSql().getMovimientoBanco(pago);

                String monto = "0";
                try {
                    monto = instancias.getSql().montoBanco(mov[0][3].toString());
                } catch (Exception e) {
                }
                monto = big.setMoneda(big.getMoneda(monto));

                String tipo = mov[0][1].toString();
                BigDecimal total = BigDecimal.ZERO;

                if (tipo.equals("Entrada")) {
                    tipo = "Salida";
                    total = big.getMoneda(monto).subtract(big.getBigDecimal(mov[0][0]));
                } else if (tipo.equals("Salida")) {
                    tipo = "Entrada";
                    total = big.getMoneda(monto).add(big.getBigDecimal(mov[0][0]));
                }

                String consecutivoMovimiento = "MOVBANC-" + instancias.getSql().getNumConsecutivo("MOVBANC")[0];
                Object[] vector = {consecutivoMovimiento, mov[0][2], mov[0][3], "ANULACIÓN DEL MOVIMIENTO DEL BANCO '" + pago + "' ", instancias.getUsuario(),
                    metodos.fechaConsulta(metodosGenerales.fecha()), tipo, "", metodosGenerales.hora(), ""};

                Object[] vector1 = {big.getMoneda(monto), big.getBigDecimal(mov[0][0]), big.getMoneda(big.setMoneda(total))};

                if (!instancias.getSql().agregarMovimientoBanco(vector, vector1)) {
                    metodos.msgError(null, "Hubo un problema al guardar el movimiento");
                    return;
                }

                instancias.getSql().modificarMontoBanco(big.getMoneda(big.setMoneda(total)), mov[0][3].toString());
                instancias.getSql().aumentarConsecutivo("MOVBANC", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("MOVBANC")[0]) + 1);

            } else if (pago.contains("PAGO")) {
                String estadoCuenta, id;
                try {
                    id = instancias.getSql().getIdCxp("Where recibo ='" + pago + "' ");
                    estadoCuenta = instancias.getSql().getEstadoPago("Where ingreso = '" + id + "' and tipo <> 'PAGO' ");

                    if (!estadoCuenta.equals("PEND")) {
                        instancias.getSql().modificarRegistroCxp(" where ingreso = '" + id + "' and tipo <> 'PAGO' ");
                    }
                    instancias.getSql().modificarRegistroCxp1(pago, "ANULADO");
                } catch (Exception e) {
                }
            } else {
                if (!instancias.getSql().anularDocumento(pago, "bdIngreso")) {
                    metodos.msgError(null, "Hubo un problema al anular la compra");
                    return;
                }

                instancias.getSql().eliminarPonderadoIngreso(" bdPonderado ", pago);
//                instancias.getSql().eliminarPonderadoIngreso(" bdUltimoPonderado ", pago);

                if (!instancias.getSql().modificarRegistroCxp(pago, "ANULADA")) {
                    metodos.msgError(null, "Hubo un problema al anular la Cxp");
                    return;
                }

                Object[][] Productos = instancias.getSql().getProductosCompra(pago);
                for (Object[] Producto : Productos) {

                    String idPonderado = instancias.getSql().getConsecutivoPonderado(Producto[0].toString());
                    Object[] ponderados = instancias.getSql().getUltimoPonderado1(idPonderado);

                    String ingreso = "";
                    try {
                        ingreso = ponderados[9].toString();
                    } catch (Exception e) {
                    }

                    if (!instancias.getSql().modificarPonderado(ponderados[8].toString(), Producto[0].toString(),
                            big.getBigDecimal(ponderados[1].toString()), String.valueOf(ponderados[2]), ponderados[3].toString(),
                            big.getBigDecimal(ponderados[4]), String.valueOf(ponderados[5]), instancias.getUsuario(),
                            big.getBigDecimal(ponderados[7]), ingreso)) {
                        metodos.msgError(null, "Error al modificar ponderado");
                    }

                    ndProducto producto = instancias.getSql().getDatosProducto(Producto[0].toString(), "bdProductos");
                    double cantidad;
                    double inventario;
                    double fisicoInventario;

                    try {
                        cantidad = Double.parseDouble(producto.getCompras().replace(",", "."));
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    try {
                        inventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                    } catch (Exception e) {
                        inventario = 0;
                    }

                    try {
                        fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
                    } catch (Exception e) {
                        fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
                    }

                    double inv = Double.parseDouble(Producto[1].toString().replace(",", "."));

                    inventario = inventario - inv;
                    fisicoInventario = fisicoInventario - inv;
                    double total = cantidad - inv;

                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                    String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                    instancias.getSql().modificarInventario("compras", total1, Producto[0].toString(), "bdProductos");
                    instancias.getSql().modificarInventario("inventario", inventario1, Producto[0].toString(), "bdProductos");
                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, Producto[0].toString(), "bdProductos");

                    if (instancias.getConfiguraciones().isProductosDetallados()) {
                        instancias.getSql().anularCompraDetalladoInventario(consecutivo);
                    }
                }
            }

//                if (banco.length > 0) {
//
//                    Object[] vector = {banco[0][0], banco[0][1], "Anulación del egreso " + idEgreso, instancias.getUsuario(), metodosGenerales.fechaHora(), "Entrada", ""};
//                    Object[] vector1 = {big.getMoneda(banco[0][2].toString()), big.getMoneda(txtValor.getText()), big.getMoneda(txtTotal.getText())};
//
//                    if (!instancias.getSql().agregarMovimientoBanco(vector, vector1)) {
//                        metodos.msgError(null, "Hubo un problema al guardar el movimiento");
//                        return;
//                    } else {
//                        metodos.msgExito(null, "Movimiento registrado con exito");
//                        instancias.getSql().modificarMontoBanco(big.getMoneda(txtTotal.getText()), banco[0][1].toString());
//                        instancias.getBancos().refrescarDatos();
//                        this.dispose();
//                    }
//                }
            if (pago.equals("")) {
                metodos.msgExito(this, "Egreso anulado con éxito");
            } else {
                if (pago.contains("MOVBANC")) {
                    metodos.msgExito(this, "Mov.Banco y pago anulado con éxito");
                } else if (pago.contains("PAGO")) {
                    metodos.msgExito(this, "Egreso y pago anulado con éxito");
                } else {
                    metodos.msgExito(this, "Egreso y compra anulado con éxito");
                }
            }
        }
    }//GEN-LAST:event_btnBuscTerceros2ActionPerformed

    private void txtTotalLetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalLetrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalLetrasActionPerformed

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtIdCliente.requestFocus();
        }
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoFocusGained

    }//GEN-LAST:event_txtCodigoFocusGained

    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoKeyPressed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Object[][] datos = instancias.getSql().getDatosCodsEgreso(txtCodigo.getText());

            if (datos.length > 0) {
                Object[] fila = {datos[0][1].toString(), datos[0][2].toString(), "", this.simbolo + " 0", "0", this.simbolo + " 0", this.simbolo + " 0", "", datos[0][0].toString()};

                DefaultTableModel modelo = (DefaultTableModel) tblEgresos.getModel();
                modelo.addRow(fila);

                tblEgresos.setColumnSelectionInterval(2, 2);
                tblEgresos.setRowSelectionInterval(tblEgresos.getRowCount() - 1, tblEgresos.getRowCount() - 1);
                tblEgresos.editCellAt(tblEgresos.getRowCount() - 1, 2);
                tblEgresos.transferFocus();
                txtCodigo.setText("");
            } else {
                ventanaEgresos(txtCodigo.getText());
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        }
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        ventanaEgresos(txtCodigo.getText());
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void cmbTipoImpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoImpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoImpresionActionPerformed

    private void tblEgresosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEgresosMouseExited
        calcularValorFila();
    }//GEN-LAST:event_tblEgresosMouseExited

    private void tblEgresosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEgresosMouseReleased
        calcularValorFila();
    }//GEN-LAST:event_tblEgresosMouseReleased

    private void tblComprobantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseClicked
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
            actualizarResolucion(0);
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
            actualizarResolucion(tblComprobantes.getSelectedRow());
        }
    }//GEN-LAST:event_tblComprobantesMouseClicked

    private void tblComprobantesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseEntered

    }//GEN-LAST:event_tblComprobantesMouseEntered

    private void tblComprobantesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblComprobantesMouseExited
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }

        if (tblComprobantes.getSelectedRow() == -1) {
            tblComprobantes.setValueAt(true, 0, 2);
            actualizarResolucion(0);
        } else {
            tblComprobantes.setValueAt(true, tblComprobantes.getSelectedRow(), 2);
            actualizarResolucion(tblComprobantes.getSelectedRow());
        }
    }//GEN-LAST:event_tblComprobantesMouseExited

    private ModeloDetalleEgreso obtenerDetalleEgreso(String identificadorEgreso, int fila) {
        String facturaRegistro = "";
        if (tblEgresos.getValueAt(fila, 6) != null) {
            facturaRegistro = tblEgresos.getValueAt(fila, 7).toString();
        }

        return new ModeloDetalleEgreso(
                identificadorEgreso,
                tblEgresos.getValueAt(fila, 8).toString(),
                tblEgresos.getValueAt(fila, 2).toString(),
                facturaRegistro,
                tblEgresos.getValueAt(fila, 0).toString(),
                big.getMoneda((String) tblEgresos.getValueAt(fila, 3)),
                big.getMoneda((String) tblEgresos.getValueAt(fila, 5)),
                big.getMoneda((String) tblEgresos.getValueAt(fila, 6)),
                Integer.parseInt(tblEgresos.getValueAt(fila, 4).toString()));
    }

    private Object[][] obtenerImpuestosPorProducto(ModeloDetalleProductos detalleProducto, int filaProducto) {

        int secuencia = 0;
        Object[][] informacionImpuestosFactura = new Object[1][4];

        BigDecimal baseProducto = big.getMoneda(tblEgresos.getValueAt(filaProducto, 3).toString());

        if (detalleProducto.getValorIva().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorIva();
            informacionImpuestosFactura[secuencia][2] = Numeros.formatoDosDecimales.format(detalleProducto.getPorcentajeIva()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "IVA";
            secuencia++;
        }

        return informacionImpuestosFactura;
    }

    private ModeloDetalleImpuestos obtenerImpuestosEgreso() {

        List<Integer> ivas = new ArrayList<>();
        for (int i = 0; i < tblEgresos.getRowCount(); i++) {
            int porcentajeIva = Integer.parseInt(tblEgresos.getValueAt(i, 4).toString());
            if (porcentajeIva != 0 && !ivas.contains(porcentajeIva)) {
                ivas.add(porcentajeIva);
            }
        }

        Object[][] informacionImpuestoIva = new Object[ivas.size()][4];

        for (int i = 0; i < ivas.size(); i++) {
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal impuesto = BigDecimal.ZERO;
            for (int j = 0; j < tblEgresos.getRowCount(); j++) {
                if (Integer.parseInt(tblEgresos.getValueAt(j, 4).toString()) == ivas.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblEgresos.getValueAt(j, 3).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblEgresos.getValueAt(j, 5).toString()));
                }
            }

            informacionImpuestoIva[i][0] = subtotal;
            informacionImpuestoIva[i][1] = impuesto;
            informacionImpuestoIva[i][2] = Numeros.formatoDosDecimales.format(ivas.get(i)).replace(",", ".");
            informacionImpuestoIva[i][3] = "IVA";
        }

        ModeloDetalleImpuestos detalleImpuestos = new ModeloDetalleImpuestos();
        detalleImpuestos.setImpuestosIvas(informacionImpuestoIva);
        detalleImpuestos.setImpuestosImpoconsumo(Utilidades.objetoVacio());
        detalleImpuestos.setImpuestosReteIva(Utilidades.objetoVacio());
        detalleImpuestos.setImpuestosReteFuente(Utilidades.objetoVacio());

        return detalleImpuestos;
    }

    private ModeloDetalleProductos[] obtenerDetalleProductos(String egreso) {

        int cantidadTotal = tblEgresos.getRowCount();
        ModeloDetalleProductos[] detalladoProductos = new ModeloDetalleProductos[cantidadTotal];

        for (int i = 0; i < tblEgresos.getRowCount(); i++) {
            ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
            modeloIndividual.setNumeroFactura(egreso);
            modeloIndividual.setEstandarProducto("UNSPSC");
            modeloIndividual.setUnidadMedida("UNIDAD");

            modeloIndividual.setValorTotalBruto(big.getMoneda(tblEgresos.getValueAt(i, 3).toString()));
            modeloIndividual.setValorIva(big.getMoneda(tblEgresos.getValueAt(i, 5).toString()));
            modeloIndividual.setPorcentajeIva(big.getBigDecimal(tblEgresos.getValueAt(i, 4).toString()));
            modeloIndividual.setDescripcionArticulo(tblEgresos.getValueAt(i, 1).toString());
            modeloIndividual.setObservacionDetalle(tblEgresos.getValueAt(i, 2).toString());
            modeloIndividual.setUnidadesEmpaque(BigDecimal.ONE);

            modeloIndividual.setCodigoArticulo(tblEgresos.getValueAt(i, 0).toString());
            modeloIndividual.setCodigoVendedor(DATOS_CLIENTE_CARGADO.getId());
            modeloIndividual.setPrecioUnitario(big.getMoneda(tblEgresos.getValueAt(i, 3).toString()));
            modeloIndividual.setCantidad("1");

            modeloIndividual.setFechaInicio(metodos.fecha4(metodosGenerales.fecha()));
            modeloIndividual.setCodigoGeneracion("POR_OPERACION");

            Object[][] impuestosProducto = obtenerImpuestosPorProducto(modeloIndividual, i);
            modeloIndividual.setImpuestosProducto(impuestosProducto);

            ModeloDescuentos[] resultadosDescuentos = new ModeloDescuentos[0];
            modeloIndividual.setDescuentoProducto(resultadosDescuentos);

            detalladoProductos[i] = modeloIndividual;
        }

        return detalladoProductos;
    }

    private void actualizarTablaResoluciones() {
        DefaultTableModel modeloComprobantes = (DefaultTableModel) tblComprobantes.getModel();;
        while (tblComprobantes.getRowCount() > 0) {
            modeloComprobantes.removeRow(0);
        }

        modeloComprobantes.addRow(new Object[]{"0", "EGRESO NORMAL", true, "", "", "", "", "", "", "", ""});

        List<ModeloResolucion> resoluciones = daoResoluciones.obtenerResoluciones(enumTipoDocumento.TipoDocumento.EGRESO.getValue());
        for (ModeloResolucion resolucion : resoluciones) {
            modeloComprobantes.addRow(new Object[]{resolucion.getIdResolucion(), resolucion.getDescripcionResolucion(), false, resolucion.getNumeroResolucion(), resolucion.getFechaInicio(),
                resolucion.getNumeracionDel(), resolucion.getNumeracionHasta(), resolucion.getTipoResolucion(), resolucion.getPrefijo(), resolucion.getConsecutivo(), resolucion.getDisenho()});
        }

        tblComprobantes.setValueAt(true, 0, 2);
        actualizarResolucion(0);
    }

    private void actualizarResolucion(int filaSeleccionada) {
        if (filaSeleccionada <= 0) {
            String prefijo = "";
            if (instancias.getIdEgreso() != null) {
                prefijo = instancias.getIdEgreso();
            }
            lbNoEgreso.setText(prefijo + (String) instancias.getSql().getNumConsecutivo("EGR")[0]);
        } else {
            String prefijo = "", consecutivo;
            if (null != tblComprobantes.getValueAt(filaSeleccionada, 8)) {
                prefijo = tblComprobantes.getValueAt(filaSeleccionada, 8).toString();
            }

            if (null == tblComprobantes.getValueAt(filaSeleccionada, 9)) {
                alertas.alert("Resolución sin consecutivo, verifique para que pueda continuar");
                return;
            } else {
                consecutivo = tblComprobantes.getValueAt(filaSeleccionada, 9).toString();
            }

            lbNoEgreso.setText(prefijo + consecutivo);
        }
    }

    private String obtenerResolucionDocumentoSoporte() {
        int filaSeleccionada = 0;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                filaSeleccionada = i;
                break;
            }
        }

        String resolucionDocumentoSoporte = "";
        if (null != tblComprobantes.getValueAt(filaSeleccionada, 3)) {
            resolucionDocumentoSoporte = tblComprobantes.getValueAt(filaSeleccionada, 3).toString();
        }

        return resolucionDocumentoSoporte;
    }

    private String obtenerPrefijoDocumentoSoporte() {
        int filaSeleccionada = 0;
        String prefijoDocumentoSoporte = "";

        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                filaSeleccionada = i;
                break;
            }
        }

        if (null != tblComprobantes.getValueAt(filaSeleccionada, 8)) {
            prefijoDocumentoSoporte = tblComprobantes.getValueAt(filaSeleccionada, 8).toString();
        }

        return prefijoDocumentoSoporte;
    }

    private ModeloDocumentoSoporte crearModeloDocumentoSoporte(String egreso, ModeloContacto datosCliente) {

        ModeloDocumentoSoporte modeloDocumentoSoporte = new ModeloDocumentoSoporte();

        modeloDocumentoSoporte.setDsPrefijo(obtenerPrefijoDocumentoSoporte());
        modeloDocumentoSoporte.setDsNumeroFactura(egreso.replace("EGR-", ""));

        String tipoOperacion = cmbTipoImpresion.getSelectedItem().equals("POS") ? "POS" : "ESTANDAR";
        modeloDocumentoSoporte.setTipoOperacion(tipoOperacion);
        modeloDocumentoSoporte.setFechaEmision(metodos.fecha4(metodosGenerales.fecha()) + " " + metodosGenerales.fechaHora().split(" ")[1]);
        modeloDocumentoSoporte.setFechaVencimiento(metodos.fecha4(metodosGenerales.fecha()) + " " + metodosGenerales.fechaHora().split(" ")[1]);
        modeloDocumentoSoporte.setTipoDocumentoElectronico("SOPORTE_ADQUISICION");
        modeloDocumentoSoporte.setDsResolucionDian(obtenerResolucionDocumentoSoporte());

        documentosElectronicos.construirDatosCliente(modeloDocumentoSoporte, datosCliente);

        if (big.getMoneda(txtCheque.getText()).compareTo(BigDecimal.ZERO) > 0 || big.getMoneda(txtBanco.getText()).compareTo(BigDecimal.ZERO) > 0) {
            modeloDocumentoSoporte.setFormaPago("CONTADO");
            modeloDocumentoSoporte.setMedioPago("CHEQUE");
        } else {
            modeloDocumentoSoporte.setFormaPago("CONTADO");
            modeloDocumentoSoporte.setMedioPago("EFECTIVO");
        }

        modeloDocumentoSoporte.setValorBruto(big.getMoneda(txtSubTotal.getText()));
        modeloDocumentoSoporte.setValorBrutoMasTributos(big.getMoneda(txtTotal.getText()));

        BigDecimal valorBaseImponible = BigDecimal.ZERO;
        for (int i = 0; i < tblEgresos.getRowCount(); i++) {
            if (Integer.parseInt(tblEgresos.getValueAt(i, 4).toString()) > 0) {
                valorBaseImponible = valorBaseImponible.add(big.getMoneda(tblEgresos.getValueAt(i, 3).toString()));
            }
        }

        modeloDocumentoSoporte.setValorBaseImponible(valorBaseImponible);
        modeloDocumentoSoporte.setDescuentoTotal(BigDecimal.ZERO);
        modeloDocumentoSoporte.setCargoTotal(BigDecimal.ZERO);
        modeloDocumentoSoporte.setValorNeto(big.getMoneda(txtTotal.getText()));

        ModeloDetalleImpuestos resultadosImpuestos = obtenerImpuestosEgreso();
        modeloDocumentoSoporte.setImpuestosCompra(resultadosImpuestos);

        ModeloDetalleProductos[] detalleProductos = obtenerDetalleProductos(egreso);
        modeloDocumentoSoporte.setDetalleProductos(detalleProductos);

        ModeloDescuentos[] resultadosDescuentos = new ModeloDescuentos[0];
        modeloDocumentoSoporte.setDescuentosFactura(resultadosDescuentos);

        return modeloDocumentoSoporte;
    }

    private String obtenerTipoComprobante() {
        String tipoComprobante = "";
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                if (null != tblComprobantes.getValueAt(i, 7)) {
                    tipoComprobante = tblComprobantes.getValueAt(i, 7).toString();
                }
            }
        }

        return tipoComprobante;
    }

    private void generarMovimientoBancario() {
        if (instancias.getUsuario().equals("ADMIN")) {
            if (cmbTipoEgreso.getSelectedItem().equals("GENERAL")) {
                if (metodos.msgPregunta(this, "¿Sacar dinero del banco?") == 0) {

                    if (tipoMovimiento.equals("")) {
                        tipoMovimiento = "movEgreso";
                    }

                    dlgMovimientosBanco movimiento = new dlgMovimientosBanco(null, true, "", "", BigDecimal.ZERO, "Salida", "EGRESO", big.getMoneda(txtTotal.getText()),
                            "EGRESO NÚM " + lbNoEgreso.getText() + "," + " CONCEPTO " + tblEgresos.getValueAt(0, 1) + ", " + "No.Factura " + tblEgresos.getValueAt(0, 6),
                            tipoMovimiento);
                    movimiento.setLocationRelativeTo(null);
                    movimiento.setVisible(true);
                }
            }
        }

        tipoMovimiento = "";
    }

    private void establecerTipoImpresion() {
        if (instancias.getTipoImpresion() != null) {
            if (instancias.getTipoImpresion().equals("Pos")) {
                cmbTipoImpresion.setSelectedIndex(1);
            } else {
                cmbTipoImpresion.setSelectedIndex(0);
            }
        } else {
            cmbTipoImpresion.setSelectedIndex(0);
        }
    }

    public void limpiar() {
        btnLimpiarActionPerformed(null);
    }

    public void ventanaEgresos(String nit) {
        buscEgresos buscar = new buscEgresos(instancias.getMenu(), rootPaneCheckingEnabled);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarEgresos(buscar);
        instancias.setCampoActual(txtCodigo);
        txtCodigo.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarEgreso(String nit, BigDecimal total, String factura, String codEgreso, String concepto, String tipo, String ingresoAsociado,
            BigDecimal iva, BigDecimal subtotal, BigDecimal efectivo, BigDecimal tarjeta, BigDecimal cheque, BigDecimal rtf, BigDecimal rtIva, String desde) {

        if (tipo.equals("GENERAL")) {
            cmbTipoEgreso.setSelectedIndex(2);
        } else {
            cmbTipoEgreso.setSelectedIndex(1);
        }

        txtIdCliente.setText(nit);

        txtCheque.setText(big.setMonedaExacta(cheque));
        txtBanco.setText(big.setMonedaExacta(tarjeta));
        txtEfectivo.setText(big.setMonedaExacta(efectivo));

        cargarCliente(txtIdCliente.getText());
        this.ingresoAsociado = ingresoAsociado;
        Object dato[] = instancias.getSql().getInfoCodEgreso(codEgreso);

        Object[] fila = {dato[2].toString(), dato[1].toString(), concepto, big.setMoneda(subtotal), big.setMoneda(iva),
            big.setMoneda(total), factura, dato[0].toString()};

        DefaultTableModel modelo = (DefaultTableModel) tblEgresos.getModel();
        modelo.addRow(fila);
        calcularValores();

        if (desde.equals("registrandoCompra")) {
            tipoMovimiento = "movCompra";
        } else if (desde.equals("registrandoPago")) {
            tipoMovimiento = "movPago";
        }

        guardarEgreso();
    }

    public void desdeLavadero(String nit, String valor, String factura, String codEgreso, String concepto, String ingresoAsociado, String tipo) {

        if (tipo.equals("GENERAL")) {
            cmbTipoEgreso.setSelectedIndex(2);
        } else {
            cmbTipoEgreso.setSelectedIndex(1);
        }

        txtIdCliente.setText(nit);
        cargarCliente(txtIdCliente.getText());

        this.ingresoAsociado = ingresoAsociado;
        Object dato[] = instancias.getSql().getInfoCodEgreso(codEgreso);

        Object[] fila = {dato[2].toString(), dato[1].toString(), concepto, valor, this.simbolo + " 0", valor, factura, dato[0].toString()};

        DefaultTableModel modelo = (DefaultTableModel) tblEgresos.getModel();
        modelo.addRow(fila);
        calcularValores();

        guardarEgreso();
    }

    private void calcularValorFila() {
        int filaSeleccionada = tblEgresos.getSelectedRow();
        if (filaSeleccionada < 0) {
            return;
        }

        try {
            tblEgresos.setValueAt(big.setMonedaExacta(big.getMoneda(((String) tblEgresos.getValueAt(filaSeleccionada, 3)))), filaSeleccionada, 3);
        } catch (NumberFormatException e) {
            tblEgresos.setValueAt(this.simbolo + " 0", filaSeleccionada, 3);
        }

        BigDecimal subtotal = big.getBigDecimal("0");
        try {
            subtotal = big.getMoneda(tblEgresos.getValueAt(filaSeleccionada, 3).toString());
        } catch (Exception e) {
            tblEgresos.setValueAt(this.simbolo + " 0", filaSeleccionada, 3);
        }

        try {
            subtotal = big.getMoneda(tblEgresos.getValueAt(filaSeleccionada, 3).toString());
        } catch (Exception e) {
            tblEgresos.setValueAt(this.simbolo + " 0", filaSeleccionada, 3);
        }

        int ivaSeleccionado = 0;
        try {
            ivaSeleccionado = Integer.parseInt(tblEgresos.getValueAt(filaSeleccionada, 4).toString());
        } catch (Exception e) {
            tblEgresos.setValueAt(this.simbolo + " 0", filaSeleccionada, 4);
        }

        BigDecimal totalIva = BigDecimal.valueOf(ivaSeleccionado).multiply(subtotal).divide(big.getBigDecimal(100), RoundingMode.HALF_UP);
        BigDecimal total = subtotal.add(totalIva);

        tblEgresos.setValueAt(ivaSeleccionado, filaSeleccionada, 4);
        tblEgresos.setValueAt(big.setMonedaExacta(totalIva), filaSeleccionada, 5);
        tblEgresos.setValueAt(big.setMonedaExacta(total), filaSeleccionada, 6);
    }

    public void calcularValores() {

        BigDecimal subtotal = big.getBigDecimal("0");
        BigDecimal iva = big.getBigDecimal("0");
        BigDecimal total = big.getBigDecimal("0");

        for (int i = 0; i < tblEgresos.getRowCount(); i++) {
            subtotal = subtotal.add(big.getMoneda(((String) tblEgresos.getValueAt(i, 3))));
            iva = iva.add(big.getMoneda(((String) tblEgresos.getValueAt(i, 5))));
            total = total.add(big.getMoneda(((String) tblEgresos.getValueAt(i, 6))));
        }

        txtSubTotal.setText(big.setMoneda(subtotal));
        txtIVA.setText(big.setMoneda(iva));
        txtTotal.setText(big.setMoneda(total));

        total = (total.subtract(big.getMoneda(txtBanco.getText()))).subtract(big.getMoneda(txtCheque.getText()));

        int res = total.compareTo(big.getBigDecimal("0"));

        if (res == 1) {
            txtEfectivo.setText(big.setMoneda(total));
        } else {
            txtEfectivo.setText(this.simbolo + " 0");
        }

        txtTotalLetras.setText(convertirNumeroALetras.Convertir(big.getMoneda(txtTotal.getText()).toString()));
    }

    public void cargarCliente(String nit) {

        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);
//            ndProveedor nodPro = instancias.getSql().getDatosProveedor(nit);

        if (nodo.getId() != null) {

            if (nodo.isActivo()) {
                metodos.msgError(this, "Este cliente esta inactivado");
                lbNit.requestFocus();
                return;
            }

            DATOS_CLIENTE_CARGADO = nodo;
            txtNombreCliente.setText(nodo.getNombre());
            txtTelefono.setText(nodo.getTelefono());
            txtDireccion.setText(nodo.getDireccion());
            txtNombreCliente.setEditable(false);
            txtTelefono.setEditable(false);
            txtDireccion.setEditable(false);
            return;
        }
//            else if (nodPro.getId() != null) {
//                if (nodPro.isActivo()) {
//                    metodos.msgError(this, "Este cliente esta inactivado");
//                    return;
//                }
//                txtRazon.setText(nodPro.getNombre());
//                txtTelefono.setText(nodPro.getTelefono());
//                txtDireccion.setText(nodPro.getDireccion());
//                txtRazon.setEditable(false);
//                txtTelefono.setEditable(false);
//                txtDireccion.setEditable(false);
//                return;
//            }

        txtNombreCliente.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtNombreCliente.setEditable(true);
        txtTelefono.setEditable(true);
        txtDireccion.setEditable(true);

        ventanaTerceros("");

    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), rootPaneCheckingEnabled, false, null, "");
        buscar.setOpc("egreso");
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtIdCliente);
        txtIdCliente.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

//    public void ventanaProveedores(String nit) {
//        buscProveedores buscar = new buscProveedores(instancias.getMenu(), rootPaneCheckingEnabled);
//        buscar.setLocationRelativeTo(null);
//        instancias.setBusProveedores(buscar);
//        instancias.setCampoActual(txtNit);
//        txtNit.requestFocus();
//        buscar.noEncontrado(nit);
//        buscar.show();
//    }
//    public void actualizarConceptos() {
//        cmbConcepto.removeAllItems();
//        this.codigos = new TreeMap();
//        Object[][] codigos = instancias.getSql().getCodsEgresos();
//
//        for (int i = 0; i < codigos.length; i++) {
//            cmbConcepto.addItem(codigos[i][1]);
//            this.codigos.put(codigos[i][1].toString(), new String[]{codigos[i][0].toString(), codigos[i][2].toString()});
//        }
//
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnBuscTerceros2;
    private javax.swing.JButton btnBuscTerceros3;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox cmbTipoEgreso;
    private javax.swing.JComboBox cmbTipoImpresion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jtblComprobantes;
    private javax.swing.JLabel lbBanco;
    private javax.swing.JLabel lbCheque;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbEfectivo;
    private javax.swing.JLabel lbLetras;
    private javax.swing.JLabel lbLetras1;
    private javax.swing.JLabel lbLetras2;
    private javax.swing.JLabel lbLetras3;
    private javax.swing.JLabel lbLetras4;
    private javax.swing.JLabel lbLetras5;
    private javax.swing.JLabel lbLetras8;
    private javax.swing.JLabel lbLetras9;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNoEgreso;
    private javax.swing.JLabel lbRazon;
    private javax.swing.JLabel lbTelefono;
    private javax.swing.JPanel pnlCliente;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlValores;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JScrollPane scrFormulario;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblEgresos;
    private javax.swing.JTextField txtBanco;
    private javax.swing.JTextField txtCheque;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JLabel txtIVA;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtTotalLetras;
    // End of variables declaration//GEN-END:variables
}
