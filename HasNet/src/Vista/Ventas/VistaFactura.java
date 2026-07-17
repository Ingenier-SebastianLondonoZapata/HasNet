package Vista.Ventas;

import Vista.Restaurante.PanelGruposCompacto;
import Vista.Restaurante.VistaImpresionComanda;
import Vista.Restaurante.VistaGruposProductos;
import Consumidor.FacturacionElectronica.consumidorFacturacionElectronica;
import Controlador.Alertas.ControladorAlertas;
import Enums.DetalleTipoProducto;
import Enums.EstadosTipoDocumento;
import Enums.TipoDocumento;
import Enums.TipoProducto;
import Enums.enumBodegas;
import Enums.enumTipoIdentificacion;
import Enums.enumTipoPersona;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.FacturacionElectronica.Entrada.ModeloFacturacionElectronica;
import Modelo.InicioSesion.Terminal;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.UltimoPonderado;
import Modelo.Maestra.ModeloResolucion;
import Modelo.Terceros.ModeloContacto;
import Modelo.Ventas.CabeceraDocumento;
import Modelo.Ventas.DocumentoMovimiento;
import Modelo.Ventas.FilaProductoTabla;
import Modelo.Ventas.LineaProducto;
import Modelo.Ventas.ModeloComanda;
import Modelo.Ventas.ModeloValidacionFactura;
import Modelo.Ventas.MovimientoDocumento;
import Modelo.Ventas.OpcionPreparacion;
import Modelo.Ventas.ResultadoValidacionInventario;
import Utilidades.Constantes;
import Utilidades.DatosMaestra;
import Utilidades.Utilidades;
import Utilidades.Ventas.ParserPreparacion;
import Validaciones.Facturacion.squemaFacturacion;
import Validaciones.FacturacionElectronica.squemaFacturacionElectronica;
import Vista.Productos.VistaInventarioInicial;
import Vista.Solicitudes.vistaSolicitarPermisos;
import clases.Cartera.ndCxc;
import clases.IconCellRenderer;
import clases.Instancias;
import clases.Ventas.ndCongelada;
import clases.Ventas.ndCotizacion;
import clases.Ventas.ndFactura;
import clases.Ventas.ndOServicio;
import clases.Ventas.ndOServicio1;
import clases.Ventas.ndPedido;
import clases.Ventas.ndPlanSepare;
import clases.big;
import clases.credito.ndCuota;
import clases.credito.ndPrestamo;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import dao.Configuraciones.DaoResoluciones;
import dao.InicioSesion.DaoInicioSesion;
import dao.Ventas.DaoComanda;
import dao.Ventas.DaoFactura;
import dao.Ventas.DaoOrdenServicio;
import Modelo.Ventas.ModeloDetalleOrdenServicio;
import dao.Ventas.DaoCotizacion;
import dao.Ventas.DaoPedido;
import formularios.Parqueadero.buscPlacas;
import formularios.Ventas.buscProblemas;
import formularios.Ventas.buscTipoVehiculo;
import formularios.Ventas.dlgInformacionCliente;
import formularios.Ventas.dlgPedirPermiso;
import formularios.Ventas.dlgTipoDescuento;
import formularios.Ventas.infNuevaParte;
import formularios.productos.buscProductos;
import formularios.productos.seleccionarPLU;
import formularios.terceros.buscClientes;
import inventario.servicio.CargadorProducto;
import inventario.servicio.ServicioActualizacionPonderado;
import inventario.servicio.ServicioDiscosteo;
import inventario.servicio.ServicioProcesadorComandas;
import inventario.servicio.ServicioValidacionFactura;
import inventario.vista.VistaMovimientoDetalleProducto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public final class VistaFactura extends javax.swing.JPanel {

    DefaultTableModel modeloPro;
    DefaultTableModel modeloComprobantes;
    DefaultTableModel modeloCredito;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;

    private DecimalFormat formatoDosDecimales = new DecimalFormat("#.00");
    private String ID_CLIENTE_CARGADO = null;
    private ModeloContacto DATOS_CLIENTE_CARGADO = null;
    private boolean DESCUENTO_GENERAL_CARGADO = false;
    private final squemaFacturacion squemaFacturacion = new squemaFacturacion();
    private final squemaFacturacionElectronica squemaFacturacionElectronica = new squemaFacturacionElectronica();
    private final consumidorFacturacionElectronica consumidorFacturacionElectronica = new consumidorFacturacionElectronica();
    private final ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();
    private final FuncionalidadVentas funcionalidadVentas = new FuncionalidadVentas();

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();
    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    private final DaoFactura daoFactura = new DaoFactura();
    private final DaoOrdenServicio daoOrdenServicio = new DaoOrdenServicio();
    private final DaoCotizacion daoCotizacion = new DaoCotizacion();
    private final DaoPedido daoPedido = new DaoPedido();

    private ConversorDocumentoAFactura conversorDocumentoAFactura;

    boolean topeDescuento = false;
    private int cantDias = 0;

    private final boolean esFacturaCredito;
    private List<LineaProducto> lineasOriginales = new ArrayList<>();

    private boolean focusDiasPlazo = false, cambioMesa = false, plu = false, mesaCongelada = false,
            saltarPasosFactura = false, solicitudPermiso = false, pasandoACongelada = false;

    private String tipoProceso, credito1, loteGeneral = "", permisoNumero = "",
            terminal = "", loteCuentasCobro = "", fechaFacturaAutomatica = "";

    private PanelGruposCompacto panelGruposEmbebido;

    //NODOS
    private String ndPeluqueria = "", ndGuarderia = "", ndHospitalizacion = "", diasHospitalizacion = "",
            horasHospitalizacion = "", simbolo = "";

    public String getPermisoNumero() {
        return permisoNumero;
    }

    public void setPermisoNumero(String permisoNumero) {
        this.permisoNumero = permisoNumero;
    }

    public boolean isSolicitudPermiso() {
        return solicitudPermiso;
    }

    public void setSolicitudPermiso(boolean solicitudPermiso) {
        this.solicitudPermiso = solicitudPermiso;
    }

    public boolean isCambioMesa() {
        return cambioMesa;
    }

    public void setCambioMesa(boolean cambioMesa) {
        this.cambioMesa = cambioMesa;
    }

    public VistaFactura(String tipo) {
        initComponents();

        this.instancias = Instancias.getInstancias();
        this.terminal = instancias.getTerminal();
        this.simbolo = instancias.getSimbolo();

        this.conversorDocumentoAFactura = funcionalidadVentas.inicializarConversorDocumentos(instancias, daoOrdenServicio, daoPedido, daoCotizacion);
        setearOpcionesAlIniciar();

        if (tipo.equals(TipoDocumento.CREDITO.getValor())) {
            this.esFacturaCredito = true;
            this.tipoProceso = TipoDocumento.FACTURACION.getValor();
        } else {
            this.esFacturaCredito = false;
            this.tipoProceso = tipo;
        }

        modeloComprobantes = (DefaultTableModel) tblComprobantes.getModel();

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor()) || this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            actualizarTablaResoluciones();
            tblComprobantes.setValueAt(true, 0, 2);
        }

        modeloPro = (DefaultTableModel) tblProductos.getModel();

        TableColumn tc = tblProductos.getColumnModel().getColumn(37);
        TableCellEditor tce = new DefaultCellEditor(cmbListas);
        tc.setCellEditor(tce);

        txtFechaFactura.setText(metodosGenerales.fecha());
        txtVencimiento.setText(metodosGenerales.fecha());
        setBorder(null);
        repaint();

        txtNit.requestFocus();
        pnlOrdenServicio.setVisible(false);
        btnModificar.setVisible(false);
        btnReImprimir.setVisible(false);

        actualizarVistaSegunTipoDocumento(tipo);
        actualizarResolucion(0);
        actualizarConsecutivo(0);
        inicializarPanelGruposEmbebido();

        this.registerKeyboardAction(accion("guardarFactura"), "guardarFactura", KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("imprimir"), "imprimir", KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("productos"), "productos", KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("limpiar"), "limpiar", KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("aumentarCantidad"), "aumentarCantidad", KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("disminuirCantidad"), "disminuirCantidad", KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("terceros"), "terceros", KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("valor"), "valor", KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("reimprimir"), "reimprimir", KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.registerKeyboardAction(accion("subir"), "subir", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    private void setearOpcionesAlIniciar() {
        tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());
        tblProductos.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());

        chkSisteCredito.setEnabled(false);
        lbCupo.setVisible(false);
        pnlOcultar.setVisible(false);
        pnlCambiarMesa.setVisible(false);
        btnPasarACongelada.setVisible(false);

        pnlCuentaCobro.setVisible(false);
        jtblComprobantes.setVisible(false);

        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            txtDescGeneral.setVisible(false);
        } else {
            txtPorcentaje.setEditable(true);
        }

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            establecerPropiedadTabla(0, 0, 0, 0);
        }

        if (!instancias.getConfiguraciones().isMedico()) {
            establecerPropiedadTabla(17, 0, 0, 0);
            txtCopago.setVisible(false);
        }

        if (instancias.getConfiguraciones().isProductosDetallados() || instancias.getConfiguraciones().isParqueadero()) {
            establecerPropiedadTabla(27, 100, 200, 300);
        } else {
            establecerPropiedadTabla(27, 0, 0, 0);
        }

        if (DatosMaestra.getMostrarUbicacionProductos().equals("SI")) {
            establecerPropiedadTabla(10, 50, 125, 155);
        } else {
            establecerPropiedadTabla(10, 0, 0, 0);
        }

        if (DatosMaestra.getTipoDescuento().equals("porcentaje")) {
            establecerPropiedadTabla(5, 45, 45, 45);
            establecerPropiedadTabla(6, 0, 0, 0);
        } else {
            establecerPropiedadTabla(5, 0, 0, 0);
            establecerPropiedadTabla(6, 100, 100, 100);
        }
    }

    private void establecerPropiedadTabla(int columna, int min, int pref, int max) {
        tblProductos.getColumnModel().getColumn(columna).setMinWidth(min);
        tblProductos.getColumnModel().getColumn(columna).setPreferredWidth(pref);
        tblProductos.getColumnModel().getColumn(columna).setMaxWidth(max);
    }

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "imprimir":
                        if ((btnImprimir.isEnabled()) && (btnImprimir.isVisible())) {
                            btnImprimirActionPerformed(null);
                        }
                        break;
                    case "limpiar":
                        if ((btnLimpiar.isEnabled()) && (btnLimpiar.isVisible())) {
                            btnLimpiarActionPerformed(null);
                        }
                        break;
                    case "guardarFactura":
                        if ((btnGuardar.isEnabled()) && (btnGuardar.isVisible())) {
                            btnGuardarActionPerformed(null);
                        }
                        break;
                    case "productos":
                        if ((btnBusProd.isEnabled()) && (btnBusProd.isVisible())) {
                            btnBusProdActionPerformed(null);
                        }
                        break;
                    case "reimprimir": {
                        if (!tipoProceso.equals("mesa")) {
                            try {
                                instancias.getReimpresion().setSelected(true);
                            } catch (PropertyVetoException ex) {
                                Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    break;
                    case "terceros":
                        if ((btnBuscTerceros.isEnabled()) && (btnBuscTerceros.isVisible())) {
                            btnBuscTercerosActionPerformed(null);
                        }
                        break;
                    case "modificar":
                        if ((btnModificar.isEnabled()) && (btnModificar.isVisible())) {
                            btnModificarActionPerformed(null);
                        }
                        break;
                    case "valor":
                        if (tblProductos.getSelectedRow() > -1) {
                            tblProductos.editCellAt(tblProductos.getSelectedRow(), 2);
                            tblProductos.setColumnSelectionInterval(2, 2);
                            tblProductos.transferFocus();
                        }
                        break;
                    case "subir":
//                        if (tblProductos.getSelectedRow() > -1) {
////                            tblProductos.editCellAt(tblProductos.getSelectedRow());
//                            tblProductos.setColumnSelectionInterval(tblProductos.getSelectedRow() - 1, tblProductos.getSelectedRow() - 1);
//                            tblProductos.transferFocus();
//                        }
//                        break;
                    case "aumentarCantidad":
                        if (tblProductos.getSelectedRow() > -1) {
                            String baseUtilizada = "bdProductos";
                            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), baseUtilizada);

                            if (!nodo.getUsuario().equals("ADMIN") && !tipoProceso.equals("cotizacion")) {
                                ControladorAlertas.alert("La cantidad no se puede aumentar");
                                return;
                            }

                            BigDecimal num = big.getBigDecimal(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3));
                            num = num.add(BigDecimal.ONE);

                            if (num.compareTo(BigDecimal.ZERO) <= 0) {
                                tblProductos.setValueAt(BigDecimal.ONE, tblProductos.getSelectedRow(), 3);
                            } else {
                                tblProductos.setValueAt(num, tblProductos.getSelectedRow(), 3);
                            }

                            calcularTabla(tblProductos.getSelectedRow(), false);
                        }
                        break;
                    case "disminuirCantidad":
                        if (tblProductos.getSelectedRow() > -1) {
                            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), "bdProductos");

                            if (!nodo.getUsuario().equals("ADMIN") && !tipoProceso.equals("cotizacion")) {
                                return;
                            }

                            BigDecimal num = big.getBigDecimal(tblProductos.getValueAt(tblProductos.getSelectedRow(), 3));
                            num = num.subtract(BigDecimal.ONE);

                            if (num.compareTo(BigDecimal.ZERO) <= 0) {
                                tblProductos.setValueAt(BigDecimal.ONE, tblProductos.getSelectedRow(), 3);
                            } else {
                                tblProductos.setValueAt(num, tblProductos.getSelectedRow(), 3);
                            }
                            calcularTabla(tblProductos.getSelectedRow(), false);

                        }
                        break;
                }
            }
        };
        return a;
    }

    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {

        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), 30);

            if (getPreferredSize().height > 30) {
                if (table.getRowHeight(row) != getPreferredSize().height) {
                    table.setRowHeight(row, getPreferredSize().height);
                }
            } else {
                if (table.getRowHeight(row) != getPreferredSize().height) {
                    table.setRowHeight(row, 30);
                }
            }

            if (isSelected) {
                setForeground(new Color(0, 0, 0));
                setBackground(table.getSelectionBackground());
                Font font = new Font("Arial", Font.PLAIN, 17);
                setFont(font);
            } else {
                setForeground(new Color(0, 0, 0));
                setBackground(table.getBackground());
                Font font = new Font("Arial", Font.PLAIN, 17);
                setFont(font);
            }

            return this;
        }
    }

    public void actualizarVistaSegunTipoDocumento(String tipo) {
        tapControl.remove(pnlCredito);
        tapControl.remove(pnlOrdenServicio);
        tapControl.remove(pnlFacturacionAutomatica);

        if (this.esFacturaCredito) {
            tapControl.addTab("Datos Créditos", pnlCredito);
        }

        switch (tipo) {
            case "credito":
                lbTitulo.setText("CREDITOS");
                txtDiasPlazo.setEnabled(false);
                modeloCredito = (DefaultTableModel) tblCuotas.getModel();
                dtFechaDesenvolso.setFormat(2);
                dtFechaDesenvolso.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
                break;
            case "facturacion":
                lbTitulo.setText("FACTURACIÓN");
                lbFacturaNo.setText(instancias.getTituloFactura());
                jtblComprobantes.setVisible(true);
                break;
            case "cotizacion":
                lbTitulo.setText("COTIZACIÓN");
                lbFacturaNo.setText("Cotización No.");
                btnGuardar.setText("GUARDAR");
                btnReImprimir.setVisible(false);
                lbFechaVencimiento.setVisible(false);
                txtVencimiento.setVisible(false);
                lbDiasPlazo.setEnabled(false);
                txtDiasPlazo.setEnabled(false);
                chkSisteCredito.setEnabled(false);
                txtTurno.setVisible(false);
                lbOtroConsecutivo.setVisible(false);

                break;
            case "orden":
                lbTitulo.setText("ORDEN DE SERVICIO");
                lbFacturaNo.setText("Orden No.");
                btnGuardar.setText("GUARDAR");
                lbFechaVencimiento.setVisible(false);
                txtVencimiento.setVisible(false);
                lbDiasPlazo.setEnabled(false);
                txtDiasPlazo.setEnabled(false);
                chkSisteCredito.setEnabled(false);
                btnReImprimir.setVisible(false);

                if (instancias.getConfiguraciones().isServicioAutomotor()) {
                    tapControl.addTab("Registro Orden", pnlOrdenServicio);
                }

                break;
            case "pedido":
                lbTitulo.setText("PEDIDOS");
                lbFacturaNo.setText("Pedido No.");
                btnGuardar.setText("GUARDAR");
                lbFechaVencimiento.setVisible(false);
                txtVencimiento.setVisible(false);
                lbDiasPlazo.setEnabled(false);
                txtDiasPlazo.setEnabled(false);
                chkSisteCredito.setEnabled(false);
                txtDescGeneral.setVisible(false);
                break;
            case "separe":
                lbTitulo.setText("PLAN SEPARE");
                lbFacturaNo.setText("Separe No.");
                btnGuardar.setText("GENERAR");
                btnReImprimir.setVisible(false);
                lbCargarDocumento.setVisible(false);
                txtCargar.setVisible(false);
                break;
            case "cuentaCobro":
                lbTitulo.setText("CUENTAS DE COBRO");
                lbFacturaNo.setText("Plantilla No.");
                btnGuardar.setText("GUARDAR");
                pnlCuentaCobro.setVisible(true);
                txtDiasPlazo.setEnabled(false);
                txtVencimiento.setVisible(false);
                lbFechaVencimiento.setVisible(false);
                lbOtroConsecutivo.setVisible(false);
                txtTurno.setVisible(false);
                btnImprimir.setVisible(false);
                btnReImprimir.setVisible(false);
                break;
            case "mesa":
                btnGuardar.setText("GUARDAR");
                lbCargarDocumento.setVisible(false);
                txtCargar.setVisible(false);

                if (instancias.getConfiguraciones().isRestaurante()) {
                    lbTitulo.setText(instancias.getTitulo() != null ? instancias.getTitulo() : "MESAS");
                    lbFacturaNo.setText("MESA No.");
                } else {
                    lbTitulo.setText("CONGELADA");
                    lbFacturaNo.setText("CONGELADA No.");
                }
                jtblComprobantes.setVisible(true);
                break;
        }

        if (instancias.isVentasPredeterminado()) {
            cargar1010();
        }

        actualizarVistaConsecutivo();
    }

    public void borrarAdiciones() {
        int num = tblProductos.getRowCount();
        int num2 = 0;
        while (num2 <= num) {
            String desde = "";
            try {
                desde = tblProductos.getValueAt(num2, 31).toString();
            } catch (Exception e) {
            }

            if ("PRODUCTO-AGREGADO".equals(desde)) {
                modeloPro.removeRow(num2);
                num2 = num2 - 1;
            } else {
            }

            num2 = num2 + 1;
        }

        tblProductos.removeEditor();
    }

    public void actualizarConsecutivo(int fila) {
        String prefijo = "", consecutivo = "";

        if (null != tipoProceso) {
            switch (tipoProceso) {
                case "facturacion":
                    if (null != tblComprobantes.getValueAt(fila, 8)) {
                        prefijo = tblComprobantes.getValueAt(fila, 8).toString();
                    }

                    if (null == tblComprobantes.getValueAt(fila, 9)) {
                        ControladorAlertas.bigAlert("Resolución sin consecutivo, verifique para que pueda continuar");
                        return;
                    } else {
                        consecutivo = tblComprobantes.getValueAt(fila, 9).toString();
                    }

                    lbNoFactura.setText(prefijo + consecutivo);
                    break;
                case "orden":
                    lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("OSERV")[0]);
                    break;
                case "cotizacion":
                    lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("COTI")[0]);
                    break;
                case "pedido":
                    lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("PEDIDO")[0]);
                    break;
                case "separe":
                    lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("SEPARE")[0]);
                    break;
                case "cuentaCobro":
                    lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("CCOBRO")[0]);
                    break;
                case "mesa":
                    if (!mesaCongelada && !lbTitulo.getText().equals("DOMICILIO")) {
                        lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("CONGELADA")[0]);
                    }

                    if (lbTitulo.getText().equals("DOMICILIO")) {
                        if (null != tblComprobantes.getValueAt(fila, 8)) {
                            prefijo = tblComprobantes.getValueAt(fila, 8).toString();
                        }

                        if (null == tblComprobantes.getValueAt(fila, 9)) {
                            ControladorAlertas.bigAlert("Resolución sin consecutivo, verifique para que pueda continuar");
                            return;
                        } else {
                            consecutivo = tblComprobantes.getValueAt(fila, 9).toString();
                        }

                        lbNoFactura.setText(prefijo + consecutivo);
                    }
                    break;
            }
        }
    }

    public int obtenerDocumento() {
        int fila = 0;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                fila = i;
            }
        }
        return fila;
    }

    public void aumentarConsecutivo(int fila) {

        switch (tipoProceso) {
            case "facturacion":
                funcionalidadVentas.aumentarConsecutivoResolucion(Integer.parseInt(tblComprobantes.getValueAt(fila, 0).toString()));
                break;
            case "orden":
                if (!instancias.getSql().aumentarConsecutivo("OSERV", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("OSERV")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la cotizacion");
                }
                break;
            case "cotizacion":
                if (!instancias.getSql().aumentarConsecutivo("COTI", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("COTI")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la cotizacion");
                }
                break;
            case "pedido":
                if (!instancias.getSql().aumentarConsecutivo("PEDIDO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PEDIDO")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del pedido");
                }
                break;
            case "separe":
                if (!instancias.getSql().aumentarConsecutivo("SEPARE", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("SEPARE")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del pedido");
                }
                break;
            case "cuentaCobro":
                if (!instancias.getSql().aumentarConsecutivo("CCOBRO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CCOBRO")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la cuenta de cobro");
                }
                break;
            case "mesa":
                if (lbTitulo.getText().equals("DOMICILIO")) {
                    funcionalidadVentas.aumentarConsecutivoResolucion(Integer.parseInt(tblComprobantes.getValueAt(fila, 0).toString()));
                } else {
                    if (!instancias.getSql().aumentarConsecutivo("CONGELADA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONGELADA")[0]) + 1)) {
                        metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la congelada");
                    }
                }
                break;
        }
    }

    public void imprimir(String factura, String factura2) {

        String observaciones = txtObservaciones.getText();

        String legal, pie;

        try {
            legal = instancias.getLegal();
        } catch (Exception ex) {
            legal = "";
        }

        try {
            pie = instancias.getPie();
        } catch (Exception ex) {
            pie = "";
        }

        Boolean tipoDomicilio = false;
        if (tipoProceso.equals("mesa")) {
            tipoProceso = "facturacion";
            tipoDomicilio = true;
        }

        switch (tipoProceso) {
            case "facturacion":

                Object[] datosReporte = funcionalidadVentas.construirDatosReporteFactura(instancias, rdMediaCarta.isSelected(), rdCarta.isSelected(), rdPos.isSelected());

                //VALIDACIÓN PARA VER IMPOCONSUMO EN LA IMPRESION
                String impoconsumo = String.valueOf(DatosMaestra.isMostrarImpoconsumo());

                //VALIDACIÓN PARA VER RETENCIONES EN LA IMPRESION
                String retenciones = String.valueOf(DatosMaestra.isMostrarRetenciones());

                Object[] grupos = new Object[tblProductos.getRowCount()];
                int cantidadGrupos = 0;
                boolean porGrupo = DatosMaestra.isImpresionPorGrupo();
                if (porGrupo) {
                    for (int i = 0; i < tblProductos.getRowCount(); i++) {
                        String grupo = tblProductos.getValueAt(i, 34).toString();
                        boolean yaExiste = false;
                        for (int j = 0; j < cantidadGrupos; j++) {
                            if (grupo.equals(grupos[j])) {
                                yaExiste = true;
                                break;
                            }
                        }
                        if (!yaExiste) {
                            grupos[cantidadGrupos++] = grupo;
                        }
                    }
                }

                funcionalidadVentas.imprimirPasadaFactura(instancias, factura2, datosReporte[0].toString(),
                        observaciones, legal, pie, datosReporte[2].toString(), datosReporte[1].toString(),
                        impoconsumo, retenciones, "Original", porGrupo, grupos, cantidadGrupos);
                funcionalidadVentas.pausarImpresora();

                if (DatosMaestra.isCopiasFactura()) {
                    String copias = "1";
                    try {
                        copias = metodos.msgIngresarEnter(null, "Cantidad de copias");
                    } catch (Exception e) {
                    }

                    if (copias != null && !copias.isEmpty()) {
                        for (int i = 0; i < Integer.parseInt(copias); i++) {
                            funcionalidadVentas.imprimirPasadaFactura(instancias, factura2, datosReporte[0].toString(),
                                    observaciones, legal, pie, datosReporte[2].toString(), datosReporte[1].toString(), impoconsumo,
                                    retenciones, "Copia " + (i + 1), porGrupo, grupos, cantidadGrupos);
                        }
                    }
                } else {
                    int cantidad = Integer.parseInt(DatosMaestra.getNumFactura());
                    for (int i = 0; i < cantidad; i++) {
                        funcionalidadVentas.imprimirPasadaFactura(instancias, factura2, datosReporte[0].toString(),
                                observaciones, legal, pie, datosReporte[2].toString(), datosReporte[1].toString(), impoconsumo,
                                retenciones, "Copia " + (i + 1), porGrupo, grupos, cantidadGrupos);
                    }
                }

                if (tipoDomicilio) {
                    this.tipoProceso = "mesa";
                    tipoDomicilio = false;
                }

                break;

            case "cotizacion":

                instancias.getReporte().ver_Cotiza(factura, observaciones, instancias.getInformacionEmpresa(), legal, getTipo(), !DatosMaestra.isPrevisualizarCotizacion());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }

                if (DatosMaestra.isCopiasCotizacion()) {
                    String copias = JOptionPane.showInputDialog("Cantidad de copias");
                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Cotiza(factura, observaciones, instancias.getInformacionEmpresa(), legal, getTipo(), !DatosMaestra.isPrevisualizarCotizacion());
                            }
                        }
                    } catch (Exception e) {
                    }
                } else {

                    int cantidad = Integer.parseInt(DatosMaestra.getNumCotizacion());
                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Cotiza(factura, observaciones, instancias.getInformacionEmpresa(), legal, getTipo(), !DatosMaestra.isPrevisualizarCotizacion());
                        }
                    }
                }
                break;
            case "orden":

                if (instancias.getConfiguraciones().isServicioAutomotor()) {
                    instancias.getReporte().ver_oServicio(factura, observaciones, !DatosMaestra.isPrevisualizarOServicio(), txtTipoVehiculo.getText(), "");

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }

                    if (DatosMaestra.isCopiasOServicio()) {
                        String copias = JOptionPane.showInputDialog("Cantidad de copias");

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_oServicio(factura, observaciones, !DatosMaestra.isPrevisualizarOServicio(), txtTipoVehiculo.getText(), "");
                                }
                            }
                        } catch (Exception e) {
                        }
                    } else {

                        int cantidad = Integer.parseInt(DatosMaestra.getNumOServicio());
                        if (cantidad > 0) {
                            for (int i = 0; i < cantidad; i++) {
                                instancias.getReporte().ver_oServicio(factura, observaciones, !DatosMaestra.isPrevisualizarOServicio(), txtTipoVehiculo.getText(), "");
                            }
                        }
                    }
                } else {

                    String tipoImp = "";
                    if (rdMediaCarta.isSelected()) {
                        tipoImp = "OrdenNormal";
                    } else if (rdCarta.isSelected()) {
                        tipoImp = "OrdenNormalCompleta";
                    } else if (rdPos.isSelected()) {
                        tipoImp = "OrdenPos";
                    }

                    instancias.getReporte().ver_oServicio(factura, observaciones, !DatosMaestra.isPrevisualizarOServicio(), "", tipoImp);

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }

                    if (DatosMaestra.isCopiasOServicio()) {
                        String copias = JOptionPane.showInputDialog("Cantidad de copias");

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_oServicio(factura, observaciones, !DatosMaestra.isPrevisualizarOServicio(), "", "OrdenNormal");
                                }
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        int cantidad = Integer.parseInt(DatosMaestra.getNumOServicio());
                        if (cantidad > 0) {
                            for (int i = 0; i < cantidad; i++) {
                                instancias.getReporte().ver_oServicio(factura, observaciones, !DatosMaestra.isPrevisualizarOServicio(), "", "OrdenNormal");
                            }
                        }
                    }
                }

                break;
            case "pedido":
                String tipo1 = this.getTipo();

                instancias.getReporte().ver_Pedido(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !DatosMaestra.isPrevisualizarPedido());

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }

                if (DatosMaestra.isCopiasPedido()) {
                    String copias = JOptionPane.showInputDialog("Cantidad de copias");
                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Pedido(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Copia " + (i + 1), instancias.getPie(), this.getTipo(), !DatosMaestra.isPrevisualizarPedido());
                            }
                        }
                    } catch (NumberFormatException e) {
                    }
                } else {
                    int cantidad = Integer.parseInt(DatosMaestra.getNumPedido());
                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Pedido(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Copia " + (i + 1), instancias.getPie(), this.getTipo(), !DatosMaestra.isPrevisualizarPedido());
                        }
                    }
                }
                break;
            case "separe":
                instancias.getReporte().ver_Separe(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !DatosMaestra.isPrevisualizarPlanSepare());

                if (DatosMaestra.isCopiasPlanSepare()) {
                    String copias = JOptionPane.showInputDialog("Cantidad de copias");
                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Separe(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !DatosMaestra.isPrevisualizarPlanSepare());
                            }
                        }
                    } catch (NumberFormatException e) {
                    }
                } else {
                    int cantidad = Integer.parseInt(DatosMaestra.getNumPlanSepare());
                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Separe(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !DatosMaestra.isPrevisualizarPlanSepare());
                        }
                    }
                }
                break;
        }
    }

    public void setTipo() {
        if (instancias.getImpresion().equals("pos")) {
            rdPos.setSelected(true);
        } else if (instancias.getImpresion().equals("facturaCompleta")) {
            rdCarta.setSelected(true);
        } else {
            rdMediaCarta.setSelected(true);
        }
    }

    public int getCantidadProductos() {
        return tblProductos.getRowCount();
    }

    public boolean isPlu() {
        return plu;
    }

    public void setPlu(boolean plu) {
        this.plu = plu;
    }

    public void setCantDias(int dias) {
        cantDias = dias;
    }

    BigDecimal NC = null;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        popBorrar = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        grupoTipoFactura = new javax.swing.ButtonGroup();
        grupoTipoImpresion = new javax.swing.ButtonGroup();
        grpCopago = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        pnlFormulario = new javax.swing.JPanel();
        tapControl = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        scrProductos1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbProducto = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        lbProducto1 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        lbVendedor1 = new javax.swing.JLabel();
        txtCargar = new javax.swing.JTextField();
        lbCargarDocumento = new javax.swing.JLabel();
        btnPasarACongelada = new javax.swing.JButton();
        pnlGrupos = new javax.swing.JPanel();
        pnlCredito = new javax.swing.JPanel();
        lbVendedor8 = new javax.swing.JLabel();
        cmbTipoPlazo = new javax.swing.JComboBox();
        lbTelefono3 = new javax.swing.JLabel();
        txtCuotas = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCuotas = new javax.swing.JTable();
        txtInteres = new javax.swing.JTextField();
        lbDireccion3 = new javax.swing.JLabel();
        txtTotalIntereses = new javax.swing.JTextField();
        lbDireccion4 = new javax.swing.JLabel();
        txtValorCredito = new javax.swing.JTextField();
        lbDireccion5 = new javax.swing.JLabel();
        dtFechaDesenvolso = new datechooser.beans.DateChooserCombo();
        lbCelular1 = new javax.swing.JLabel();
        lbDireccion6 = new javax.swing.JLabel();
        txtCuotaInicial = new javax.swing.JTextField();
        txtTotalCredito = new javax.swing.JTextField();
        lbDireccion7 = new javax.swing.JLabel();
        lbDireccion8 = new javax.swing.JLabel();
        txtValorVenta = new javax.swing.JTextField();
        pnlOrdenServicio = new javax.swing.JPanel();
        lbNombre9 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        lbNombre10 = new javax.swing.JLabel();
        lbNombre5 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        lbNombre6 = new javax.swing.JLabel();
        lbNombre7 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        txtKm = new javax.swing.JTextField();
        lbNombre8 = new javax.swing.JLabel();
        txtMotor = new javax.swing.JTextField();
        txtNumChasis = new javax.swing.JTextField();
        lbNombre11 = new javax.swing.JLabel();
        lbNombre13 = new javax.swing.JLabel();
        txtColor = new javax.swing.JTextField();
        lbNombre14 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtProblema = new javax.swing.JTextArea();
        btnNuevaParte = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblArticulos = new javax.swing.JTable();
        btnNuevaParte1 = new javax.swing.JButton();
        txtTipoVehiculo = new javax.swing.JTextField();
        pnlFacturacionAutomatica = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lbVendedor10 = new javax.swing.JLabel();
        dtHasta = new datechooser.beans.DateChooserCombo();
        chkSinEstablecer = new javax.swing.JCheckBox();
        dtDesde = new datechooser.beans.DateChooserCombo();
        lbVendedor9 = new javax.swing.JLabel();
        lbVendedor7 = new javax.swing.JLabel();
        cmbPeriodicidad = new javax.swing.JComboBox();
        lbVendedor11 = new javax.swing.JLabel();
        lbVendedor12 = new javax.swing.JLabel();
        txtCantIncremento = new javax.swing.JTextField();
        txtCantFacturados = new javax.swing.JTextField();
        lbVendedor13 = new javax.swing.JLabel();
        txtUltimaFacturaFecha = new javax.swing.JTextField();
        pnlOcultar = new javax.swing.JPanel();
        cmbListas = new javax.swing.JComboBox();
        txtPlaca1 = new javax.swing.JTextField();
        txtCopago = new javax.swing.JTextField();
        lbTitulo = new javax.swing.JLabel();
        txtFechaFactura = new javax.swing.JTextField();
        pnlCuentaCobro = new javax.swing.JPanel();
        cmbMes = new javax.swing.JComboBox();
        txtCupo = new javax.swing.JTextField();
        txtCartera = new javax.swing.JTextField();
        cmbListaPrecio = new javax.swing.JComboBox();
        rdTipoNormal = new javax.swing.JRadioButton();
        rdTipoCopago = new javax.swing.JRadioButton();
        txtDescGeneral = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lbObservaciones = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        lbCar = new javax.swing.JLabel();
        jtblComprobantes = new javax.swing.JScrollPane();
        tblComprobantes = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        lbDiasPlazo = new javax.swing.JLabel();
        txtDiasPlazo = new javax.swing.JTextField();
        chkSisteCredito = new javax.swing.JCheckBox();
        lbFechaVencimiento = new javax.swing.JLabel();
        txtVencimiento = new javax.swing.JTextField();
        cmbVendedor = new javax.swing.JComboBox();
        rdMediaCarta = new javax.swing.JRadioButton();
        rdPos = new javax.swing.JRadioButton();
        rdCarta = new javax.swing.JRadioButton();
        lbTotalDescuento1 = new javax.swing.JLabel();
        lbTotalDescuento2 = new javax.swing.JLabel();
        txtCantUnidades = new javax.swing.JLabel();
        txtCantProductos = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JLabel();
        lbSubtotal = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JLabel();
        txtIva = new javax.swing.JLabel();
        txtTotalIva = new javax.swing.JLabel();
        lbTotalDescuento = new javax.swing.JLabel();
        txtTotalDescuentos = new javax.swing.JLabel();
        lbImpoconsumo = new javax.swing.JLabel();
        txtTotalImpoconsumo = new javax.swing.JLabel();
        cmbRtf = new javax.swing.JComboBox();
        txtRtf = new javax.swing.JLabel();
        chkReteIva = new javax.swing.JCheckBox();
        txtRiva = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnReImprimir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lbNit = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        btnBuscTerceros = new javax.swing.JButton();
        lbNit1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbCupo = new javax.swing.JLabel();
        btnInformacionCliente = new javax.swing.JButton();
        lbFacturaNo = new javax.swing.JTextField();
        lbNoFactura = new javax.swing.JTextField();
        lbOtroConsecutivo = new javax.swing.JTextField();
        txtTurno = new javax.swing.JTextField();
        pnlCambiarMesa = new javax.swing.JPanel();
        lbCambiarMesa = new javax.swing.JLabel();
        btnCambiarMesa = new javax.swing.JButton();

        popBorrar.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        popBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar-cancelar-icono-4935-16.png"))); // NOI18N
        popBorrar.setText("Borrar");
        popBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popBorrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(popBorrar);

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBorder(null);

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        tblProductos.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Subtotal", "Desc %", "Desc", "Iva %", "Impo", "Total", "Ubicación", "Referencia", "plu", "cant2", "ponderado", "Utilidad", "Estado", "Copago", "datoGrupo", "Pago Tercero", "Utilidad1", "Orden/Aviso", "Borrar", "Impo %", "Orden", "Aviso", "F. Entrega", "Detalle", "Lote", "IdProd", "paraComanda", "permisoDesc", "idSistema", "Iva", "Grupo", "Medida", "ControlInv", "Lista", "Actual", "Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true, true, false, false, false, false, false, false, false, false, false, false, true, false, true, true, true, false, true, true, true, true, false, false, false, true, true, true, true, true, false, true, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblProductos.setComponentPopupMenu(jPopupMenu1);
        tblProductos.setMinimumSize(new java.awt.Dimension(105, 203));
        tblProductos.setRowHeight(31);
        tblProductos.getTableHeader().setReorderingAllowed(false);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        tblProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblProductosKeyReleased(evt);
            }
        });
        scrProductos1.setViewportView(tblProductos);
        if (tblProductos.getColumnModel().getColumnCount() > 0) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(200);
            tblProductos.getColumnModel().getColumn(1).setMinWidth(200);
            tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(3).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(3).setMaxWidth(70);
            tblProductos.getColumnModel().getColumn(4).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(4).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(5).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(50);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(100);
            tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(9).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(9).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(9).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(10).setMinWidth(50);
            tblProductos.getColumnModel().getColumn(10).setMaxWidth(125);
            tblProductos.getColumnModel().getColumn(11).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(11).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(11).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(12).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(12).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(13).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(13).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(14).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(14).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(15).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(15).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(15).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(16).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(16).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(16).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(17).setMinWidth(150);
            tblProductos.getColumnModel().getColumn(17).setPreferredWidth(150);
            tblProductos.getColumnModel().getColumn(17).setMaxWidth(150);
            tblProductos.getColumnModel().getColumn(18).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(18).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(18).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(19).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(19).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(19).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(20).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(20).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(20).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(21).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(21).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(21).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(22).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(22).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(22).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(23).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(23).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(23).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(24).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(24).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(24).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(25).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(25).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(25).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(26).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(26).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(26).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(27).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(27).setPreferredWidth(250);
            tblProductos.getColumnModel().getColumn(27).setMaxWidth(300);
            tblProductos.getColumnModel().getColumn(28).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(28).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(28).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(29).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(29).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(29).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(30).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(30).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(30).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(31).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(31).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(31).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(32).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(32).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(32).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(33).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(33).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(33).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(34).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(34).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(34).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(35).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(35).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(35).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(36).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(36).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(36).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(37).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(37).setPreferredWidth(35);
            tblProductos.getColumnModel().getColumn(37).setMaxWidth(35);
            tblProductos.getColumnModel().getColumn(38).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(38).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(38).setMaxWidth(80);
            tblProductos.getColumnModel().getColumn(39).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(39).setPreferredWidth(50);
            tblProductos.getColumnModel().getColumn(39).setMaxWidth(80);
        }

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbProducto.setText("Producto:");
        lbProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbProductoMouseClicked(evt);
            }
        });
        lbProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProductoKeyReleased(evt);
            }
        });

        txtCodigoProducto.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        txtCodigoProducto.setName("combo"); // NOI18N
        txtCodigoProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCodigoProductoFocusGained(evt);
            }
        });
        txtCodigoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoProductoActionPerformed(evt);
            }
        });
        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyReleased(evt);
            }
        });

        btnBusProd.setBackground(new java.awt.Color(204, 204, 204));
        btnBusProd.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBusProd.setForeground(new java.awt.Color(255, 255, 255));
        btnBusProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBusProd.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBusProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBusProd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBusProd.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBusProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBusProdActionPerformed(evt);
            }
        });

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbProducto1.setText("Cantidad:");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        txtCantidad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCantidad.setEnabled(false);
        txtCantidad.setName("combo"); // NOI18N
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantidadFocusGained(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        txtPorcentaje.setEditable(false);
        txtPorcentaje.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPorcentajeMouseClicked(evt);
            }
        });
        txtPorcentaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPorcentajeActionPerformed(evt);
            }
        });
        txtPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajeKeyTyped(evt);
            }
        });

        lbVendedor1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbVendedor1.setText("Descuento General:");

        txtCargar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCargar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCargar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargarKeyReleased(evt);
            }
        });

        lbCargarDocumento.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbCargarDocumento.setText("Cargar Documento:");

        btnPasarACongelada.setBackground(new java.awt.Color(247, 220, 111));
        btnPasarACongelada.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPasarACongelada.setText("Pasar a congelada");
        btnPasarACongelada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasarACongeladaActionPerformed(evt);
            }
        });

        pnlGrupos.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout pnlGruposLayout = new javax.swing.GroupLayout(pnlGrupos);
        pnlGrupos.setLayout(pnlGruposLayout);
        pnlGruposLayout.setHorizontalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
        );
        pnlGruposLayout.setVerticalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbProducto1)
                        .addGap(2, 2, 2)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lbProducto)
                        .addGap(2, 2, 2)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPasarACongelada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbVendedor1)
                        .addGap(2, 2, 2)
                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbCargarDocumento)
                        .addGap(2, 2, 2)
                        .addComponent(txtCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrProductos1, javax.swing.GroupLayout.DEFAULT_SIZE, 1015, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addComponent(pnlGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(lbProducto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPorcentaje)
                                    .addComponent(lbCargarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCargar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbVendedor1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnPasarACongelada, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addComponent(scrProductos1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE))
                    .addComponent(pnlGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        tapControl.addTab("Facturación", jPanel2);

        pnlCredito.setBackground(new java.awt.Color(255, 255, 255));
        pnlCredito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Información del crédito", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 14))); // NOI18N

        lbVendedor8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbVendedor8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbVendedor8.setText("Tipo de plazo:");

        cmbTipoPlazo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbTipoPlazo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Días", "Semanal", "Quincenal", "Mensual" }));
        cmbTipoPlazo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoPlazoItemStateChanged(evt);
            }
        });
        cmbTipoPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbTipoPlazoKeyReleased(evt);
            }
        });

        lbTelefono3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbTelefono3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTelefono3.setText("Num. Cuotas: *");

        txtCuotas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCuotas.setText("0");
        txtCuotas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuotasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuotasKeyTyped(evt);
            }
        });

        tblCuotas.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tblCuotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No.Cuota", "Fecha", "Abono a capital", "Abono a intereses", "Total", "Saldo", "Saldo Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCuotas.setRowHeight(24);
        jScrollPane3.setViewportView(tblCuotas);

        txtInteres.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtInteres.setText("0");
        txtInteres.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtInteresKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInteresKeyTyped(evt);
            }
        });

        lbDireccion3.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbDireccion3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDireccion3.setText("Porcentaje interés: *");

        txtTotalIntereses.setEditable(false);
        txtTotalIntereses.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTotalIntereses.setName("Dirección"); // NOI18N
        txtTotalIntereses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalInteresesKeyReleased(evt);
            }
        });

        lbDireccion4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion4.setText("Total intereses:");

        txtValorCredito.setEditable(false);
        txtValorCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtValorCredito.setName("Dirección"); // NOI18N
        txtValorCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorCreditoKeyReleased(evt);
            }
        });

        lbDireccion5.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion5.setText("Valor Credito:");

        dtFechaDesenvolso.setFieldFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 12));
        dtFechaDesenvolso.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtFechaDesenvolsoOnCommit(evt);
            }
        });

        lbCelular1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbCelular1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbCelular1.setText("Primer Pago: *");

        lbDireccion6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDireccion6.setText("Cuota Inicial:");

        txtCuotaInicial.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCuotaInicial.setText("0");
        txtCuotaInicial.setName("Dirección"); // NOI18N
        txtCuotaInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuotaInicialKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuotaInicialKeyTyped(evt);
            }
        });

        txtTotalCredito.setEditable(false);
        txtTotalCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTotalCredito.setName("Dirección"); // NOI18N
        txtTotalCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTotalCreditoKeyReleased(evt);
            }
        });

        lbDireccion7.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion7.setText("Total Credito:");

        lbDireccion8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbDireccion8.setText("Valor Venta:");
        lbDireccion8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDireccion8MouseClicked(evt);
            }
        });

        txtValorVenta.setEditable(false);
        txtValorVenta.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtValorVenta.setText("0");
        txtValorVenta.setName("Dirección"); // NOI18N
        txtValorVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtValorVentaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCreditoLayout = new javax.swing.GroupLayout(pnlCredito);
        pnlCredito.setLayout(pnlCreditoLayout);
        pnlCreditoLayout.setHorizontalGroup(
            pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCreditoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlCreditoLayout.createSequentialGroup()
                        .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCreditoLayout.createSequentialGroup()
                                .addComponent(lbDireccion8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCuotaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(253, 253, 253)
                                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlCreditoLayout.createSequentialGroup()
                                        .addComponent(lbTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbDireccion7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbDireccion3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(pnlCreditoLayout.createSequentialGroup()
                                        .addComponent(lbDireccion4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotalIntereses, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pnlCreditoLayout.createSequentialGroup()
                                .addComponent(lbDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116)
                                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnlCreditoLayout.createSequentialGroup()
                                        .addComponent(lbDireccion5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlCreditoLayout.createSequentialGroup()
                                        .addComponent(lbVendedor8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbTipoPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtFechaDesenvolso, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lbCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlCreditoLayout.setVerticalGroup(
            pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCreditoLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDireccion6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuotaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbVendedor8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTelefono3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDireccion3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbCelular1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDireccion8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalIntereses, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbDireccion7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTotalCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dtFechaDesenvolso, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        tapControl.addTab("Datos Créditos", pnlCredito);

        pnlOrdenServicio.setBackground(new java.awt.Color(255, 255, 255));
        pnlOrdenServicio.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        lbNombre9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre9.setText("Placa:");

        txtPlaca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPlaca.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPlaca.setName("Nombre"); // NOI18N
        txtPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlacaKeyReleased(evt);
            }
        });

        lbNombre10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre10.setText("Tipo:");

        lbNombre5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre5.setText("Marca:");

        txtMarca.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMarca.setName("Nombre"); // NOI18N

        lbNombre6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre6.setText("Km:");

        lbNombre7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre7.setText("Modelo:");

        txtModelo.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtModelo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtModelo.setName("Nombre"); // NOI18N

        txtKm.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtKm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtKm.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtKm.setName("Nombre"); // NOI18N

        lbNombre8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre8.setText("N° Motor:");

        txtMotor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtMotor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMotor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMotor.setName("Nombre"); // NOI18N

        txtNumChasis.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNumChasis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNumChasis.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNumChasis.setName("Nombre"); // NOI18N

        lbNombre11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre11.setText("N° Chasis:");

        lbNombre13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbNombre13.setText("Color:");

        txtColor.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColor.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtColor.setName("Nombre"); // NOI18N

        lbNombre14.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        lbNombre14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNombre14.setText("PROBLEMA GENERAL");

        jScrollPane6.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N

        txtProblema.setColumns(20);
        txtProblema.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtProblema.setLineWrap(true);
        txtProblema.setRows(2);
        jScrollPane6.setViewportView(txtProblema);

        btnNuevaParte.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnNuevaParte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar pequeño.png"))); // NOI18N
        btnNuevaParte.setToolTipText("Ctrl+M");
        btnNuevaParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaParteActionPerformed(evt);
            }
        });

        tblArticulos.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Parte del vehiculo", "Inv.", "Problemas Derecha", "Problemas Izquierda", "Observaciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArticulos.setRowHeight(24);
        tblArticulos.getTableHeader().setReorderingAllowed(false);
        tblArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArticulosMouseClicked(evt);
            }
        });
        tblArticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblArticulosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblArticulosKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblArticulos);
        if (tblArticulos.getColumnModel().getColumnCount() > 0) {
            tblArticulos.getColumnModel().getColumn(0).setMinWidth(0);
            tblArticulos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblArticulos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblArticulos.getColumnModel().getColumn(2).setMinWidth(40);
            tblArticulos.getColumnModel().getColumn(2).setPreferredWidth(40);
            tblArticulos.getColumnModel().getColumn(2).setMaxWidth(40);
        }

        btnNuevaParte1.setFont(new java.awt.Font("Century Gothic", 1, 11)); // NOI18N
        btnNuevaParte1.setText("LISTADO DE PARTES");
        btnNuevaParte1.setToolTipText("Ctrl+M");
        btnNuevaParte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaParte1ActionPerformed(evt);
            }
        });

        txtTipoVehiculo.setEditable(false);
        txtTipoVehiculo.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtTipoVehiculo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTipoVehiculo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTipoVehiculo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoVehiculoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoVehiculoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlOrdenServicioLayout = new javax.swing.GroupLayout(pnlOrdenServicio);
        pnlOrdenServicio.setLayout(pnlOrdenServicioLayout);
        pnlOrdenServicioLayout.setHorizontalGroup(
            pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOrdenServicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(lbNombre14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOrdenServicioLayout.createSequentialGroup()
                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOrdenServicioLayout.createSequentialGroup()
                                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbNombre10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlOrdenServicioLayout.createSequentialGroup()
                                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtPlaca))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbNombre7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtKm)
                                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbNombre11)
                                            .addComponent(lbNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnlOrdenServicioLayout.createSequentialGroup()
                                                .addComponent(txtNumChasis, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbNombre13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOrdenServicioLayout.createSequentialGroup()
                                .addComponent(btnNuevaParte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevaParte1)))
                        .addGap(0, 359, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlOrdenServicioLayout.setVerticalGroup(
            pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdenServicioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumChasis, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNombre7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2)
                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKm, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(lbNombre14)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnlOrdenServicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevaParte, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnNuevaParte1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapControl.addTab("Registro Orden", pnlOrdenServicio);

        pnlFacturacionAutomatica.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lbVendedor10.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor10.setText("Hasta:");

        dtHasta.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        dtHasta.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtHastaOnCommit(evt);
            }
        });

        chkSinEstablecer.setText("Sin establecer");

        dtDesde.setFieldFont(new java.awt.Font("Dialog", java.awt.Font.PLAIN, 12));
        dtDesde.addCommitListener(new datechooser.events.CommitListener() {
            public void onCommit(datechooser.events.CommitEvent evt) {
                dtDesdeOnCommit(evt);
            }
        });

        lbVendedor9.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor9.setText("Desde:");

        lbVendedor7.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor7.setText("Periodicidad:");

        cmbPeriodicidad.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbPeriodicidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mensual", "Anual" }));

        lbVendedor11.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor11.setText("Contador de facturas realizadas:");

        lbVendedor12.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor12.setText("Cant facturas para realizar incremento:");

        txtCantIncremento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantIncremento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantIncremento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantIncrementoActionPerformed(evt);
            }
        });

        txtCantFacturados.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCantFacturados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantFacturados.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCantFacturados.setEnabled(false);
        txtCantFacturados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantFacturadosActionPerformed(evt);
            }
        });

        lbVendedor13.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbVendedor13.setText("Fecha ultima factura:");

        txtUltimaFacturaFecha.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtUltimaFacturaFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUltimaFacturaFecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtUltimaFacturaFecha.setEnabled(false);
        txtUltimaFacturaFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUltimaFacturaFechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVendedor10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbVendedor7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbVendedor9, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbPeriodicidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(dtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSinEstablecer)
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbVendedor13, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUltimaFacturaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbVendedor11, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbVendedor12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantFacturados, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCantIncremento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(362, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbVendedor7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPeriodicidad))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbVendedor9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbVendedor10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkSinEstablecer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbVendedor13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUltimaFacturaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbVendedor11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantFacturados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbVendedor12)
                                    .addComponent(txtCantIncremento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pnlFacturacionAutomaticaLayout = new javax.swing.GroupLayout(pnlFacturacionAutomatica);
        pnlFacturacionAutomatica.setLayout(pnlFacturacionAutomaticaLayout);
        pnlFacturacionAutomaticaLayout.setHorizontalGroup(
            pnlFacturacionAutomaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacturacionAutomaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlFacturacionAutomaticaLayout.setVerticalGroup(
            pnlFacturacionAutomaticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacturacionAutomaticaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );

        tapControl.addTab("Facturación Automatica", pnlFacturacionAutomatica);

        cmbListas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8" }));
        cmbListas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbListasItemStateChanged(evt);
            }
        });
        cmbListas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbListasActionPerformed(evt);
            }
        });

        txtPlaca1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        txtPlaca1.setForeground(new java.awt.Color(255, 255, 255));

        txtCopago.setEditable(false);
        txtCopago.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtCopago.setText("0");

        lbTitulo.setFont(new java.awt.Font("Century Gothic", 1, 10)); // NOI18N
        lbTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitulo.setText("FACTURACIÓN");

        txtFechaFactura.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtFechaFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaFactura.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtFechaFactura.setEnabled(false);
        txtFechaFactura.setName("Plazo"); // NOI18N
        txtFechaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaFacturaActionPerformed(evt);
            }
        });
        txtFechaFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFechaFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFechaFacturaKeyTyped(evt);
            }
        });

        pnlCuentaCobro.setBackground(new java.awt.Color(255, 255, 255));

        cmbMes.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        cmbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        javax.swing.GroupLayout pnlCuentaCobroLayout = new javax.swing.GroupLayout(pnlCuentaCobro);
        pnlCuentaCobro.setLayout(pnlCuentaCobroLayout);
        pnlCuentaCobroLayout.setHorizontalGroup(
            pnlCuentaCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuentaCobroLayout.createSequentialGroup()
                .addContainerGap(123, Short.MAX_VALUE)
                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        pnlCuentaCobroLayout.setVerticalGroup(
            pnlCuentaCobroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCuentaCobroLayout.createSequentialGroup()
                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        txtCupo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtCupo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCupo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCupo.setEnabled(false);
        txtCupo.setName("Plazo"); // NOI18N
        txtCupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCupoActionPerformed(evt);
            }
        });
        txtCupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCupoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCupoKeyTyped(evt);
            }
        });

        txtCartera.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtCartera.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCartera.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCartera.setEnabled(false);
        txtCartera.setName("Plazo"); // NOI18N
        txtCartera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCarteraActionPerformed(evt);
            }
        });
        txtCartera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCarteraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCarteraKeyTyped(evt);
            }
        });

        cmbListaPrecio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "L1", "L2", "L3", "L4", "L5", "L6", "L7", "L8" }));

        rdTipoNormal.setBackground(new java.awt.Color(255, 255, 255));
        grpCopago.add(rdTipoNormal);
        rdTipoNormal.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rdTipoNormal.setSelected(true);
        rdTipoNormal.setText("Normal");

        rdTipoCopago.setBackground(new java.awt.Color(255, 255, 255));
        grpCopago.add(rdTipoCopago);
        rdTipoCopago.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        rdTipoCopago.setText("Copago");

        txtDescGeneral.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtDescGeneral.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescGeneral.setText("0");
        txtDescGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescGeneralActionPerformed(evt);
            }
        });
        txtDescGeneral.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescGeneralKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescGeneralKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout pnlOcultarLayout = new javax.swing.GroupLayout(pnlOcultar);
        pnlOcultar.setLayout(pnlOcultarLayout);
        pnlOcultarLayout.setHorizontalGroup(
            pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOcultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOcultarLayout.createSequentialGroup()
                        .addComponent(rdTipoNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdTipoCopago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCopago, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPlaca1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlCuentaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlOcultarLayout.createSequentialGroup()
                        .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCartera, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217)
                        .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbListaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlOcultarLayout.setVerticalGroup(
            pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOcultarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdTipoNormal)
                        .addComponent(rdTipoCopago)
                        .addComponent(txtCopago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlaca1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbListas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDescGeneral, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(pnlCuentaCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOcultarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCartera, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbListaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        lbObservaciones.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbObservaciones.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbObservaciones.setText("Observaciones");

        txtObservaciones.setColumns(20);
        txtObservaciones.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setRows(2);
        txtObservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtObservacionesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtObservaciones);

        lbCar.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbCar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCar.setText("300 ");

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

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        lbDiasPlazo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbDiasPlazo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbDiasPlazo.setText("Plazo:");
        lbDiasPlazo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbDiasPlazoMouseClicked(evt);
            }
        });

        txtDiasPlazo.setBackground(new java.awt.Color(255, 204, 204));
        txtDiasPlazo.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtDiasPlazo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDiasPlazo.setText("0");
        txtDiasPlazo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDiasPlazo.setName("Plazo"); // NOI18N
        txtDiasPlazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiasPlazoActionPerformed(evt);
            }
        });
        txtDiasPlazo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasPlazoKeyTyped(evt);
            }
        });

        chkSisteCredito.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        chkSisteCredito.setText("Siste Credito");

        lbFechaVencimiento.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFechaVencimiento.setText("Vence en:");

        txtVencimiento.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtVencimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVencimiento.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtVencimiento.setEnabled(false);
        txtVencimiento.setName("Plazo"); // NOI18N
        txtVencimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtVencimientoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVencimientoKeyTyped(evt);
            }
        });

        cmbVendedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbVendedorMouseClicked(evt);
            }
        });
        cmbVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVendedorActionPerformed(evt);
            }
        });

        rdMediaCarta.setBackground(new java.awt.Color(215, 217, 216));
        grupoTipoImpresion.add(rdMediaCarta);
        rdMediaCarta.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rdMediaCarta.setText("1/2 CARTA");
        rdMediaCarta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdMediaCartaItemStateChanged(evt);
            }
        });
        rdMediaCarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMediaCartaActionPerformed(evt);
            }
        });

        rdPos.setBackground(new java.awt.Color(215, 217, 216));
        grupoTipoImpresion.add(rdPos);
        rdPos.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rdPos.setSelected(true);
        rdPos.setText("POS");
        rdPos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdPosItemStateChanged(evt);
            }
        });

        rdCarta.setBackground(new java.awt.Color(215, 217, 216));
        grupoTipoImpresion.add(rdCarta);
        rdCarta.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        rdCarta.setText("CARTA");
        rdCarta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdCartaItemStateChanged(evt);
            }
        });

        lbTotalDescuento1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento1.setText("N° Unidades:");
        lbTotalDescuento1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuento1MouseClicked(evt);
            }
        });

        lbTotalDescuento2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento2.setText("N° Productos:");
        lbTotalDescuento2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuento2MouseClicked(evt);
            }
        });

        txtCantUnidades.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCantUnidades.setText("0");
        txtCantUnidades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantUnidadesMouseClicked(evt);
            }
        });

        txtCantProductos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtCantProductos.setText("0");
        txtCantProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCantProductosMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkSisteCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lbFechaVencimiento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVencimiento))
                    .addComponent(cmbVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(rdPos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdCarta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdMediaCarta, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbTotalDescuento2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(lbTotalDescuento1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantUnidades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbDiasPlazo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDiasPlazo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(chkSisteCredito)))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdPos, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(rdCarta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdMediaCarta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalDescuento1)
                    .addComponent(txtCantUnidades))
                .addGap(1, 1, 1)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalDescuento2)
                    .addComponent(txtCantProductos))
                .addGap(3, 3, 3))
        );

        cmbVendedor.getAccessibleContext().setAccessibleName("");
        cmbVendedor.getAccessibleContext().setAccessibleDescription("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtTotal.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        txtTotal.setText("Total: $ 0");

        lbSubtotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal.setText("Subtotal:");

        txtSubTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtSubTotal.setText("$ 0");

        txtIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIva.setText("IVA:");

        txtTotalIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalIva.setText("$ 0");

        lbTotalDescuento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento.setText("Descuentos:");
        lbTotalDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuentoMouseClicked(evt);
            }
        });

        txtTotalDescuentos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalDescuentos.setText("$ 0");

        lbImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbImpoconsumo.setText("Impoconsumo:");

        txtTotalImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalImpoconsumo.setText("$ 0");

        cmbRtf.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cmbRtf.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "RtF", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4", "6", "7", "10", "20" }));
        cmbRtf.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRtfItemStateChanged(evt);
            }
        });
        cmbRtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRtfActionPerformed(evt);
            }
        });

        txtRtf.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtRtf.setText("$ 0");

        chkReteIva.setBackground(new java.awt.Color(255, 255, 255));
        chkReteIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        chkReteIva.setText("Rete Iva");
        chkReteIva.setAlignmentY(0.0F);
        chkReteIva.setBorder(null);
        chkReteIva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReteIva.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkReteIva.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkReteIvaItemStateChanged(evt);
            }
        });
        chkReteIva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkReteIvaActionPerformed(evt);
            }
        });

        txtRiva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtRiva.setText("$ 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbImpoconsumo)
                            .addComponent(chkReteIva, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTotalDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                            .addComponent(txtTotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalDescuentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRiva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbRtf, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtRtf, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(txtTotal)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(lbSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTotalDescuento)
                    .addComponent(txtTotalDescuentos))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbImpoconsumo)
                    .addComponent(txtTotalImpoconsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRiva, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkReteIva, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.setToolTipText("Ctrl+L");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnLimpiar.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("FACTURAR");
        btnGuardar.setToolTipText("Ctrl+G");
        btnGuardar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        btnImprimir.setBackground(new java.awt.Color(46, 204, 113));
        btnImprimir.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnImprimir.setToolTipText("Ctrl+I");
        btnImprimir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnImprimir.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnReImprimir.setBackground(new java.awt.Color(247, 220, 111));
        btnReImprimir.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnReImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnReImprimir.setText("REIMPRIMIR");
        btnReImprimir.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnReImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReImprimir.setEnabled(false);
        btnReImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnReImprimir.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnReImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReImprimirActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(93, 173, 226));
        btnModificar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnModificar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnModificar.setEnabled(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnModificar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnReImprimir)
                .addGap(1, 1, 1)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lbNit.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit.setText("CC ó Nit:");
        lbNit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbNitMouseClicked(evt);
            }
        });
        lbNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNitKeyReleased(evt);
            }
        });

        txtNit.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNit.setName("CC/NIT"); // NOI18N
        txtNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNitActionPerformed(evt);
            }
        });
        txtNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNitKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNitKeyTyped(evt);
            }
        });

        btnBuscTerceros.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscTerceros.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        btnBuscTerceros.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscTerceros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar1.png"))); // NOI18N
        btnBuscTerceros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnBuscTerceros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnBuscTerceros.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscTerceros.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnBuscTerceros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscTercerosActionPerformed(evt);
            }
        });

        lbNit1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        lbNit1.setText("Razón social:");
        lbNit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNit1KeyReleased(evt);
            }
        });

        txtNombre.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNombre.setEnabled(false);
        txtNombre.setName("Cliente"); // NOI18N
        txtNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNombreMouseClicked(evt);
            }
        });

        lbCupo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbCupo.setText("EL CLIENTE SUPERA EL CUPO");

        btnInformacionCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnInformacionCliente.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        btnInformacionCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscarInfo.png"))); // NOI18N
        btnInformacionCliente.setText("Ver");
        btnInformacionCliente.setBorder(null);
        btnInformacionCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnInformacionCliente.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnInformacionCliente.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnInformacionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformacionClienteActionPerformed(evt);
            }
        });

        lbFacturaNo.setEditable(false);
        lbFacturaNo.setBackground(new java.awt.Color(255, 255, 255));
        lbFacturaNo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbFacturaNo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbFacturaNo.setText("Factura de venta No.");
        lbFacturaNo.setBorder(null);
        lbFacturaNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbFacturaNoActionPerformed(evt);
            }
        });
        lbFacturaNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbFacturaNoKeyTyped(evt);
            }
        });

        lbNoFactura.setEditable(false);
        lbNoFactura.setBackground(new java.awt.Color(255, 255, 255));
        lbNoFactura.setFont(new java.awt.Font("Century Gothic", 1, 20)); // NOI18N
        lbNoFactura.setForeground(new java.awt.Color(255, 0, 0));
        lbNoFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lbNoFactura.setText("1");
        lbNoFactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbNoFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbNoFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbNoFacturaKeyTyped(evt);
            }
        });

        lbOtroConsecutivo.setEditable(false);
        lbOtroConsecutivo.setBackground(new java.awt.Color(255, 255, 255));
        lbOtroConsecutivo.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbOtroConsecutivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbOtroConsecutivo.setText("Consecutivo adicional:");
        lbOtroConsecutivo.setBorder(null);
        lbOtroConsecutivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbOtroConsecutivoActionPerformed(evt);
            }
        });
        lbOtroConsecutivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbOtroConsecutivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbOtroConsecutivoKeyTyped(evt);
            }
        });

        txtTurno.setFont(new java.awt.Font("Century Gothic", 1, 22)); // NOI18N
        txtTurno.setForeground(new java.awt.Color(255, 0, 0));
        txtTurno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTurno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTurno.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtTurno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTurnoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTurnoKeyTyped(evt);
            }
        });

        pnlCambiarMesa.setBackground(new java.awt.Color(255, 255, 255));

        lbCambiarMesa.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbCambiarMesa.setText("CAMBIAR MESA");

        btnCambiarMesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cambiarMesa.png"))); // NOI18N
        btnCambiarMesa.setToolTipText("Ctrl+M");
        btnCambiarMesa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCambiarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarMesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCambiarMesaLayout = new javax.swing.GroupLayout(pnlCambiarMesa);
        pnlCambiarMesa.setLayout(pnlCambiarMesaLayout);
        pnlCambiarMesaLayout.setHorizontalGroup(
            pnlCambiarMesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCambiarMesaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(btnCambiarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCambiarMesa))
        );
        pnlCambiarMesaLayout.setVerticalGroup(
            pnlCambiarMesaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCambiarMesaLayout.createSequentialGroup()
                .addComponent(btnCambiarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lbCambiarMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbCupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbNit1)
                                        .addComponent(lbNit))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(txtNit)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnBuscTerceros, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnInformacionCliente)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCambiarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(jtblComprobantes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbNit)
                                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnBuscTerceros))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbNit1)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnInformacionCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1)
                        .addComponent(lbCupo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbFacturaNo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTurno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbOtroConsecutivo)))
                            .addComponent(pnlCambiarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jtblComprobantes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbObservaciones)
                                .addComponent(lbCar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, 0)
                            .addComponent(jScrollPane1))
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlOcultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tapControl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(tapControl)
                .addGap(5, 5, 5)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        jScrollPane2.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private Object[] calcularDescuento(int fila, BigDecimal subtotal, boolean mostrarAlerta) {
        BigDecimal descuento = big.getBigDecimal("0");
        BigDecimal porcentaje = big.getBigDecimal("0");
        BigDecimal porcentaje2 = big.getBigDecimal("0");

        switch (instancias.getDescuento()) {
            case "porcentaje":
                if (!tblProductos.getValueAt(fila, 5).toString().equals("0.0") && !tblProductos.getValueAt(fila, 5).toString().equals("0")) {
                    if ((boolean) tblProductos.getValueAt(fila, 18)) {

                        if (tblProductos.getValueAt(fila, 5).toString().equals("")) {
                            porcentaje2 = big.getBigDecimal("0");
                        } else {
                            porcentaje2 = big.getBigDecimal(tblProductos.getValueAt(fila, 5).toString().replace(",", ".")).divide(big.getBigDecimal("100"));
                        }
                        porcentaje = big.getBigDecimal(porcentaje2);
                        descuento = big.getMoneda(big.setNumero(subtotal.multiply(porcentaje)));
                        porcentaje2 = porcentaje2.multiply(big.getBigDecimal("100"));
                    } else {
                        if (mostrarAlerta) {
                            metodos.msgAdvertencia(null, "Este producto no puede generar descuento.");
                        }
                        porcentaje2 = big.getBigDecimal("0");
                        descuento = big.getMoneda("0");
                    }
                } else {
                    porcentaje2 = big.getBigDecimal("0");
                    descuento = big.getMoneda("0");
                }
                break;
            case "peso":
                if (!tblProductos.getValueAt(fila, 6).toString().equals(this.simbolo + " 0")) {
                    if ((boolean) tblProductos.getValueAt(fila, 18)) {
                        descuento = big.getMoneda(tblProductos.getValueAt(fila, 6).toString());
                        porcentaje2 = big.getBigDecimal(descuento.multiply(big.getBigDecimal("100")).divide(subtotal, 2, RoundingMode.HALF_DOWN));
                    } else {
                        if (mostrarAlerta) {
                            metodos.msgAdvertencia(null, "Este producto no puede generar descuento.");
                        }
                        porcentaje2 = big.getBigDecimal("0");
                        descuento = big.getMoneda("0");
                    }
                } else {
                    porcentaje2 = big.getBigDecimal("0");
                    descuento = big.getMoneda("0");
                }
                break;
        }

        return new Object[]{descuento, porcentaje2};
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

        return tipoComprobante.isEmpty() ? Constantes.FACTURACION_NORMAL : tipoComprobante;
    }

    private boolean validacionesGeneralesDeFacturacion(ModeloContacto datosCliente) {
        String tipoComprobante = obtenerTipoComprobante();
        String vendedor = cmbVendedor.getItemCount() > 0 ? cmbVendedor.getSelectedItem().toString() : "";

        if (!squemaFacturacion.validaciones_facturacion(new ModeloValidacionFactura(datosCliente))) {
            return false;
        }

        if (Constantes.esFacturacionElectronica(tipoComprobante)
                && (tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                || tipoProceso.equals(TipoDocumento.MESA.getValor()))) {
            if (!squemaFacturacionElectronica.validaciones_facturacionElectronica(datosCliente, vendedor, null, false, false)) {
                return false;
            }
        }

        if (!squemaFacturacion.validaciones_detalle_facturacion(tblProductos, tipoComprobante, tipoProceso, null, false, this.saltarPasosFactura)) {
            return false;
        }

        return true;
    }

    private boolean validacionesGeneralesDeFacturacion(ModeloContacto datosCliente,
            ResultadoValidacionInventario inventario, boolean facturarSinInventario) {
        String tipoComprobante = obtenerTipoComprobante();
        String vendedor = cmbVendedor.getItemCount() > 0 ? cmbVendedor.getSelectedItem().toString() : "";

        int diasPlazoInt = 0;
        try {
            diasPlazoInt = Integer.parseInt(txtDiasPlazo.getText());
        } catch (Exception e) {
        }

        ModeloValidacionFactura datosValidacion = new ModeloValidacionFactura(datosCliente);
        datosValidacion.setTipoProceso(tipoProceso);
        datosValidacion.setTitulo(lbTitulo.getText());
        datosValidacion.setNit(txtNit.getText());
        datosValidacion.setCantIncremento(txtCantIncremento.getText());
        datosValidacion.setSaltarPasos(this.saltarPasosFactura);
        datosValidacion.setCheckCupo(DatosMaestra.isOcultarInformacionTercero());
        datosValidacion.setDiasPlazo(txtDiasPlazo.getText());
        datosValidacion.setCupo(txtCupo.getText());
        datosValidacion.setServicioAutomotor(instancias.getConfiguraciones().isServicioAutomotor());
        datosValidacion.setTblArticulos(tblArticulos);
        datosValidacion.setFacturaCredito(esFacturaCredito);
        datosValidacion.setInteres(txtInteres.getText());
        datosValidacion.setSisteCredito(chkSisteCredito.isSelected());
        datosValidacion.setDiasPlazoInt(diasPlazoInt);
        datosValidacion.setCuotas(txtCuotas.getText());
        datosValidacion.setTipoPlazoIndex(cmbTipoPlazo.getSelectedIndex());
        datosValidacion.setFechaDesenvolso(metodos.fecha(metodos.desdeDate(dtFechaDesenvolso.getCurrent())));
        datosValidacion.setFilasTablaCuotas(tblCuotas.getRowCount());

        if (!squemaFacturacion.validaciones_facturacion(datosValidacion)) {
            return false;
        }

        if (Constantes.esFacturacionElectronica(tipoComprobante)
                && (tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                || tipoProceso.equals(TipoDocumento.MESA.getValor()))) {
            if (!squemaFacturacionElectronica.validaciones_facturacionElectronica(datosCliente, vendedor, null, false, false)) {
                return false;
            }
        }

        if (!squemaFacturacion.validaciones_detalle_facturacion(tblProductos, tipoComprobante, this.tipoProceso,
                inventario, facturarSinInventario, this.saltarPasosFactura)) {
            return false;
        }

        return true;
    }

    private String validacionInicialFactura(boolean imprimir) {

        String baseUtilizada = "bdProductos";
        if (instancias.getConfiguraciones().isRestaurante()) {
            agregarAdicionesATabla(baseUtilizada);
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            calcularTabla(i, true);
        }

        tblProductos.removeEditor();

        ResultadoValidacionInventario resultadoValidacion = this.saltarPasosFactura ? null : validarInventarioProductos(baseUtilizada);

        ModeloContacto datosCliente = !ID_CLIENTE_CARGADO.equals("") ? instancias.getSql().getDatosTercero(ID_CLIENTE_CARGADO) : new ModeloContacto();
        if (!validacionesGeneralesDeFacturacion(datosCliente, resultadoValidacion, DatosMaestra.isFacturarSinInventario())) {
            borrarAdiciones();
            return "";
        }

        if (tipoProceso.equals(TipoDocumento.MESA.getValor()) && lbTitulo.getText().equals("DOMICILIO")) {
            txtDiasPlazo.setText("1");
            calcularDiasPlazo(null);
        }

        tblProductos.removeEditor();

        if (!saltarPasosFactura) {
            if (instancias.getRegimen().equals("")) {
                if (tipoProceso.equals(TipoDocumento.FACTURACION.getValor()) || (tipoProceso.equals(TipoDocumento.MESA.getValor()) && lbTitulo.getText().equals("DOMICILIO"))) {
                    if (resultadoValidacion != null && !resultadoValidacion.tieneBolsa()) {
                        if (!instancias.getConfiguraciones().isParqueadero()) {
                            if (DatosMaestra.isImpBolsa()) {
                                BigDecimal num = BigDecimal.ZERO;
                                try {
                                    num = Utilidades.convertirBigDecimal(metodos.msgIngresarEnter(null, "Ingrese # de bolsas"));
                                } catch (NumberFormatException e) {
                                    borrarAdiciones();
                                    ControladorAlertas.alert("Número no válido!");
                                    return "";
                                }
                                if (num.compareTo(BigDecimal.ZERO) > 0) {
                                    cargarProducto("IMP01", num, 1, "", "", "", true, "", "", "", "", "");
                                }
                            }
                        }
                    }
                }
            }
        }

        if (this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            saltarPasosFactura = true;
        }

        DATOS_CLIENTE_CARGADO = datosCliente;
        return facturar(null, imprimir, "");
    }

    public void limpiar(boolean actualizar) {
        instancias.setCancelarFactura(false);
        reiniciarEstadoInterno();

        btnLimpiar.setVisible(true);
        btnPasarACongelada.setVisible(false);
        chkSinEstablecer.setSelected(false);
        txtCantIncremento.setText("");
        txtCantFacturados.setText("");
        dtDesde.setSelectedDate(metodos.haciaDate(metodos.fecha3(metodosGenerales.fecha())));
        dtHasta.setSelectedDate(metodos.haciaDate(metodos.fecha3(metodosGenerales.fecha())));
        chkSisteCredito.setEnabled(false);
        chkSisteCredito.setSelected(false);

        if (tipoProceso.equals("cuentaCobro")) {
            btnModificar.setVisible(false);
        }

        lbCupo.setVisible(false);
        txtCantidad.setText(DatosMaestra.getCantidadEstablecidaAlCargar());
        tblProductos.setEnabled(true);
        setTipo();
        txtNombre.setEnabled(false);
        txtNombre.setEditable(false);

        if (this.tipoProceso.equals("mesa")) {
            instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
        }

        btnGuardar.setEnabled(true);
        btnGuardar.setVisible(true);
        btnImprimir.setVisible(true);
        btnImprimir.setEnabled(true);

        sincronizarTurno();
        habilitarYLimpiarCamposVehiculo();

        txtRtf.setText(this.simbolo + " 0");
        txtDescGeneral.setText(this.simbolo + " 0");
        txtRiva.setText(this.simbolo + " 0");
        cmbRtf.setSelectedIndex(0);
        chkReteIva.setSelected(false);
        txtObservaciones.setText("");
        txtPorcentaje.setText("");
        txtCargar.setText("");
        txtProblema.setText("");
        tblArticulos.setEnabled(true);
        txtCantProductos.setText("0");
        txtCantUnidades.setText("0");

        if (esFacturaCredito) {
            limpiarPanelCredito();
        }

        btnModificar.setEnabled(false);

        limpiarTabla(modeloPro);

        limpiarTotalesDocumento();

        if (actualizar) {
            actualizarClienteYConsecutivo();
        }

        activarCampos(true);
        reiniciarInstanciasPagos();
        limpiarTabla((DefaultTableModel) tblArticulos.getModel());

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor()) || (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO"))) {
            instancias.setTitulo("");
        }

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())) {
            btnReImprimir.setVisible(false);
            btnModificar.setVisible(false);
        }

        tblProductos.removeEditor();
    }

    private void reiniciarEstadoInterno() {
        this.lineasOriginales = new ArrayList<>();
        this.saltarPasosFactura = false;
        this.fechaFacturaAutomatica = "";
        this.loteCuentasCobro = "";
        this.focusDiasPlazo = false;
        this.ndPeluqueria = "";
        this.ndGuarderia = "";
        this.ndHospitalizacion = "";
        this.diasHospitalizacion = "";
        this.horasHospitalizacion = "";
    }

    private void sincronizarTurno() {
        String turno = "";
        if (txtTurno.isVisible() && instancias.getConfiguraciones().isRestaurante()) {
            turno = instancias.getSql().getTurno();
            txtTurno.setText(turno);
        } else {
            txtTurno.setText("");
        }
        instancias.getMaestra().setTurno(turno);
        instancias.getMaestra().actualizarTurno();
    }

    private void habilitarYLimpiarCamposVehiculo() {
        txtPlaca.setEnabled(true);
        txtPlaca.setText("");
        txtModelo.setEnabled(true);
        txtModelo.setText("");
        txtTipoVehiculo.setEnabled(true);
        txtTipoVehiculo.setText("");
        txtNumChasis.setEnabled(true);
        txtNumChasis.setText("");
        txtMarca.setEnabled(true);
        txtMarca.setText("");
        txtKm.setEnabled(true);
        txtKm.setText("");
        txtMotor.setEnabled(true);
        txtMotor.setText("");
        txtColor.setEnabled(true);
        txtColor.setText("");
    }

    private void limpiarTabla(DefaultTableModel modelo) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }

    private void limpiarPanelCredito() {
        txtCuotas.setText("");
        txtInteres.setText("");
        txtCuotaInicial.setText(this.simbolo + " 0");
        txtValorVenta.setText(this.simbolo + " 0");
        txtTotalCredito.setText(this.simbolo + " 0");
        txtValorCredito.setText(this.simbolo + " 0");
        txtTotalIntereses.setText(this.simbolo + " 0");
        cmbTipoPlazo.setSelectedIndex(0);
        dtFechaDesenvolso.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        limpiarTabla((DefaultTableModel) tblCuotas.getModel());
    }

    private void limpiarTotalesDocumento() {
        txtSubTotal.setText(this.simbolo + " 0");
        txtTotal.setText("Total: " + this.simbolo + " 0");
        txtTotalIva.setText(this.simbolo + " 0");
        txtTotalImpoconsumo.setText(this.simbolo + " 0");
        txtTotalDescuentos.setText(this.simbolo + " 0");
    }

    private void actualizarClienteYConsecutivo() {
        txtNit.setText("");
        txtNombre.setText("");
        txtDiasPlazo.setText("0");
        txtVencimiento.setText(txtFechaFactura.getText());

        int fila = 0;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                fila = i;
            }
        }
        actualizarConsecutivo(fila);

        if (instancias.isVentasPredeterminado()
                && !(this.tipoProceso.equals(TipoDocumento.PEDIDO.getValor()) && this.tipoProceso.equals(TipoDocumento.PLAN_SEPARE.getValor()))) {
            cargar1010();
        }
    }

    private void reiniciarInstanciasPagos() {
        instancias.setDevuelta(BigDecimal.ZERO);
        instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
        instancias.setFranquisia("");
        instancias.setComision("");
        instancias.setValorComision(big.getBigDecimal("0"));
        instancias.setTotalFacturaComision(big.getBigDecimal("0"));
        instancias.setNcDevuelta(big.getBigDecimal("0"));
        instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
        instancias.setChequeDevuelta(big.getBigDecimal("0"));
        instancias.setTarjetaCredito(big.getBigDecimal("0"));
        instancias.setPropina(big.getBigDecimal("0"));
        instancias.setPorcPropina("0");
    }

    private void redireccionarRestauranteAlLimpiar() {
        if (this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getMesas().cargarRegistrosMesas();
                instancias.getMesas().cargarRegistros();
                instancias.getMesas().setSelected(true);
                instancias.getMenu().cambiarTitulo("MESAS");
            } else {
                instancias.getMesas().setSelected(true);
            }

            if (!instancias.getMenu().getSeVeElMenu()) {
                instancias.getMenu().expandirMenu();
            }
        }
    }

    public void cargar1010() {
        txtNit.setText("1010");
        cargarCliente("1010");
    }

    public void cambiarListaCliente() {
        if (tblProductos.getSelectedRow() != -1) {
            ndProducto codigo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), "bdProductos");
            String valor = "";

            switch ((String) tblProductos.getValueAt(tblProductos.getSelectedRow(), 37)) {
                case "L1":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL1()));
                    break;
                case "L2":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL2()));
                    break;
                case "L3":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL3()));
                    break;
                case "L4":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL4()));
                    break;
                case "L5":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL5()));
                    break;
                case "L6":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL6()));
                    break;
                case "L7":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL7()));
                    break;
                case "L8":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL8()));
                    break;
            }

            tblProductos.setColumnSelectionInterval(3, 3);
            tblProductos.setRowSelectionInterval(tblProductos.getSelectedRow(), tblProductos.getSelectedRow());
            tblProductos.setValueAt(valor, tblProductos.getSelectedRow(), 2);
            tblProductos.transferFocus();

            //simulando enter sobre el producto
            KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
            evento.setKeyCode(KeyEvent.VK_ENTER);
            tblProductosKeyReleased(evento);
        }
    }

    private void calcularDiasPlazo(java.awt.event.KeyEvent evt) {

        if (txtNombre.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(null, "Debe ingresar un cliente");
            txtDiasPlazo.setText("0");
            chkSisteCredito.setEnabled(false);
            chkSisteCredito.setSelected(false);
            txtNit.requestFocus();
        }

        if (txtNit.getText().equals("1010")) {
            txtNit.requestFocus();
            focusDiasPlazo = true;
            txtDiasPlazo.setText("0");
            return;
        }

//        ndTercero nodo = sql.getDatosTercero(txtNit.getText());
        try {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), Integer.parseInt(txtDiasPlazo.getText())));
        } catch (NumberFormatException exep) {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), 0));
        }

        chkSisteCredito.setEnabled(true);

        if (!txtDiasPlazo.getText().equals("") && !txtDiasPlazo.getText().equals("0")) {

        } else {
            calcularEfectivo();
        }

        if (evt != null) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                txtCodigoProducto.requestFocus();
            }
        }
    }

    private void popBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popBorrarActionPerformed
        if (tblProductos.getSelectedRow() > -1) {

            if (!DatosMaestra.isBorrarCongelada()) {
                if (tipoProceso.equals("mesa") && !instancias.getUsuario().equals("ADMIN")) {
                    if (metodos.msgPregunta(null, "No se puede borrar ¿Pedir permiso?") == 0) {
                        dlgPedirPermiso permiso = new dlgPedirPermiso(null, true, "mesa");
                        permiso.setLocationRelativeTo(null);
                        permiso.setVisible(true);
                        return;
                    } else {
                        return;
                    }
                }
            }

            eliminarFila();

        } else {
            metodos.msgAdvertencia(null, "Seleccione un producto");
        }
    }//GEN-LAST:event_popBorrarActionPerformed

    private void cmbListasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbListasItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbListasItemStateChanged

    private void cmbListasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbListasActionPerformed
        if (tblProductos.getSelectedRow() != -1) {

            String baseUtilizada = "bdProductos";

            ndProducto codigo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), baseUtilizada);
            String valor = "";

            switch ((String) tblProductos.getValueAt(tblProductos.getSelectedRow(), 37)) {
                case "L1":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL1()));
                    break;
                case "L2":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL2()));
                    break;
                case "L3":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL3()));
                    break;
                case "L4":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL4()));
                    break;
                case "L5":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL5()));
                    break;
                case "L6":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL6()));
                    break;
                case "L7":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL7()));
                    break;
                case "L8":
                    valor = big.setMonedaExacta(big.getBigDecimal(codigo.getL8()));
                    break;
            }

            tblProductos.setColumnSelectionInterval(3, 3);
            tblProductos.setColumnSelectionInterval(6, 6);
            tblProductos.setRowSelectionInterval(tblProductos.getSelectedRow(), tblProductos.getSelectedRow());
            tblProductos.setValueAt(valor, tblProductos.getSelectedRow(), 2);
            tblProductos.transferFocus();

            //simulando enter sobre el producto
            KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
            evento.setKeyCode(KeyEvent.VK_ENTER);
            tblProductosKeyReleased(evento);
        }
    }//GEN-LAST:event_cmbListasActionPerformed

    private void txtPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (topeDescuento) {
                if (Integer.parseInt(txtPorcentaje.getText()) > 20) {
                    txtPorcentaje.setText("20");
                }
            }

            tblProductos.removeEditor();

            int xyz = tblProductos.getRowCount();

            if (xyz > 0) {
                for (int y = 0; y < xyz; y++) {
                    tblProductos.setValueAt("0", y, 5);
                    tblProductos.setValueAt(this.simbolo + " 0", y, 6);
                    calcularTabla(y, false);
                }
            }

            if (xyz > 0) {
                BigDecimal porcentaje = big.getBigDecimal(txtPorcentaje.getText());
                for (int y = 0; y < xyz; y++) {
                    tblProductos.setValueAt(porcentaje, y, 5);
                    tblProductos.setValueAt(big.setMoneda(porcentaje.multiply(big.getMoneda(tblProductos.getValueAt(y, 2).toString()).divide(big.getBigDecimal("100")))), y, 6);
                    calcularTabla(y, false);
                }
            }

            txtPorcentaje.setBackground(Color.WHITE);
            txtPorcentaje.setForeground(Color.BLACK);

            cargarTotales();
            txtPorcentaje.requestFocus();
        } else {
            txtPorcentaje.setBackground(new Color(251, 238, 152));
            txtPorcentaje.setForeground(new Color(146, 137, 77));
        }

    }//GEN-LAST:event_txtPorcentajeKeyReleased

    private void txtPorcentajeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajeKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtPorcentajeKeyTyped

    private void txtCargarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarMovimiento();
        } else {
            String cargar = txtCargar.getText();
            limpiar(true);
            txtCargar.setText(cargar);
            txtNit.setText("");
            txtNombre.setText("");
            txtObservaciones.setText("");
//            this.setNit("", "0", "0");
            NC = null;
            txtCargar.requestFocus();
        }
    }//GEN-LAST:event_txtCargarKeyReleased

    private void txtFechaFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFacturaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFacturaKeyReleased

    private void txtFechaFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFechaFacturaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFacturaKeyTyped

    private void txtVencimientoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVencimientoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimientoKeyReleased

    private void txtVencimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVencimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimientoKeyTyped

    private void txtDiasPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyReleased
        calcularDiasPlazo(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyReleased

    private void txtDiasPlazoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasPlazoKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDiasPlazoKeyTyped

    private void cmbRtfItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRtfItemStateChanged
        cargarTotales();
    }//GEN-LAST:event_cmbRtfItemStateChanged

    private void cmbRtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRtfActionPerformed

    private void chkReteIvaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkReteIvaItemStateChanged
        cargarTotales();
    }//GEN-LAST:event_chkReteIvaItemStateChanged

    private void chkReteIvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkReteIvaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkReteIvaActionPerformed

    private void txtNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitActionPerformed

    private void txtNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarCliente(txtNit.getText());
        } else if (!txtNombre.getText().equals("")) {
            ID_CLIENTE_CARGADO = "";
            txtDiasPlazo.setText("0");
            chkSisteCredito.setEnabled(false);
            chkSisteCredito.setSelected(false);
            txtNombre.setText("");
            txtObservaciones.setText("");
            txtCargar.setText("");
            lbCupo.setVisible(false);
            NC = null;
        }
    }//GEN-LAST:event_txtNitKeyReleased

    private void txtNitKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNitKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitKeyTyped

    private void lbNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNitKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNitKeyReleased

    private void txtCodigoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoProductoActionPerformed

    }//GEN-LAST:event_txtCodigoProductoActionPerformed

    private void txtCodigoProductoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoProductoFocusGained
        cargarTotales();
    }//GEN-LAST:event_txtCodigoProductoFocusGained

    private void txtCodigoProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String codigo = txtCodigoProducto.getText().replace("'", "//");
            plu = true;

            String cantidad = txtCantidad.getText();
            cargarProducto(codigo, Utilidades.convertirBigDecimal(cantidad), 1, "", "", "", true, "", "", "", "", "");

            if (pnlCredito.isVisible()) {
                calcularCuotasCredito();
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnGuardar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_MULTIPLY) {
            double cantidad = 1;
            try {
                cantidad = Double.parseDouble(txtCodigoProducto.getText().replace("*", ""));
            } catch (Exception e) {
            }
            txtCantidad.setText(String.valueOf(cantidad));
            if (txtCantidad.getText().substring(txtCantidad.getText().length() - 1, txtCantidad.getText().length()).equals("0")) {
                txtCantidad.setText(txtCantidad.getText().substring(0, txtCantidad.getText().length() - 2));
            }
            txtCodigoProducto.setText("");
        }
    }//GEN-LAST:event_txtCodigoProductoKeyReleased

    private void lbProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProductoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtCodigoProducto.requestFocus();
        }
    }//GEN-LAST:event_lbProductoKeyReleased

    private void tblArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArticulosMouseClicked
        if (tblArticulos.getSelectedColumn() == 3) {
            ventanaProblemas();
        } else if (tblArticulos.getSelectedColumn() == 4) {
            ventanaProblemas1();
        }
    }//GEN-LAST:event_tblArticulosMouseClicked

    private void tblArticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblArticulosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblArticulosKeyPressed

    private void tblArticulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblArticulosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tblArticulos.getSelectedColumn() == 3) {
                ventanaProblemas();
            } else if (tblArticulos.getSelectedColumn() == 4) {
                ventanaProblemas1();
            }
        }
    }//GEN-LAST:event_tblArticulosKeyReleased

    private void btnNuevaParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaParteActionPerformed
        try {
            String tipo = txtTipoVehiculo.getText();
            cargarArticulos(tipo);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnNuevaParteActionPerformed

    private void txtPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlacaKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Object[][] name = instancias.getSql().getUsuarioPlaca(txtPlaca.getText());

            if (name.length > 0) {
                if (name[0][2] != null) {
                    txtTipoVehiculo.setText(name[0][2].toString());
                }

                if (name[0][4] != null) {
                    txtModelo.setText(name[0][4].toString());
                }

                if (name[0][6] != null) {
                    txtNumChasis.setText(name[0][6].toString());
                }

                if (name[0][3] != null) {
                    txtMarca.setText(name[0][3].toString());
                }

                if (name[0][7] != null) {
                    txtMotor.setText(name[0][7].toString());
                }

                if (name[0][5] != null) {
                    txtColor.setText(name[0][5].toString());
                }

                cargarArticulos(txtTipoVehiculo.getText());

                txtNit.setText(name[0][0].toString());
                cargarCliente(name[0][0].toString());
                btnBuscTerceros.requestFocus();
            } else {
                ventanaPlacas1(txtPlaca.getText(), ID_CLIENTE_CARGADO);
            }
        } else {
            txtModelo.setText("");
            txtNumChasis.setText("");
            txtMarca.setText("");
            txtMotor.setText("");
            txtColor.setText("");
            txtTipoVehiculo.setText("");
        }
    }//GEN-LAST:event_txtPlacaKeyReleased

    private void cmbTipoPlazoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoPlazoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoPlazoItemStateChanged

    private void cmbTipoPlazoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTipoPlazoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validarClienteParaCredito();
            calcularCuotasCredito();
        }
    }//GEN-LAST:event_cmbTipoPlazoKeyReleased

    private void txtCuotasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotasKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());

//            this.setNit(txtNit.getText(), nodo.getCupo(), nodo.getPlazo());
            validarClienteParaCredito();
            calcularCuotasCredito();
        }
    }//GEN-LAST:event_txtCuotasKeyReleased

    private void txtCuotasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotasKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCuotasKeyTyped

    private void txtInteresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInteresKeyReleased
        txtInteres.setText(txtInteres.getText().replace(".", ","));
        //txtInteres.setText(big.setNumero2(big.getMoneda(txtInteres.getText())));
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcularCuotasCredito();
        }
    }//GEN-LAST:event_txtInteresKeyReleased

    private void txtInteresKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInteresKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtInteresKeyTyped

    private void txtTotalInteresesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalInteresesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalInteresesKeyReleased

    private void txtValorCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorCreditoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorCreditoKeyReleased

    private void dtFechaDesenvolsoOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtFechaDesenvolsoOnCommit
        calcularCuotasCredito();
    }//GEN-LAST:event_dtFechaDesenvolsoOnCommit

    private void txtCuotaInicialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotaInicialKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            ModeloContacto nodo = instancias.getSql().getDatosTercero(txtNit.getText());
//            this.setNit(txtNit.getText(), nodo.getCupo(), nodo.getPlazo());

            validarClienteParaCredito();
            calcularCuotasCredito();
        } else {
            txtCuotaInicial.setText(big.setMoneda(big.getMoneda(txtCuotaInicial.getText())));
        }
    }//GEN-LAST:event_txtCuotaInicialKeyReleased

    private void txtCuotaInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuotaInicialKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtCuotaInicialKeyTyped

    private void txtTotalCreditoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalCreditoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalCreditoKeyReleased

    private void txtValorVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorVentaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValorVentaKeyReleased

    private void lbNoFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNoFacturaKeyReleased

    }//GEN-LAST:event_lbNoFacturaKeyReleased

    private void lbNoFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNoFacturaKeyTyped

    }//GEN-LAST:event_lbNoFacturaKeyTyped

    private void btnReImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReImprimirActionPerformed
        String legal = "";

        try {
            legal = instancias.getLegal();
        } catch (Exception e) {
        }

        if (tipoProceso.equals("mesa")) {

            Boolean comanda1 = false;
            Boolean prefactura1 = false;

            if (instancias.getConfiguraciones().isRestaurante()) {
                if (metodos.msgPregunta(null, "¿Quiere imprimir COMANDA?") == 0) {
                    comanda1 = true;
                }
            }

            if (metodos.msgPregunta(null, "¿Quiere imprimir PREFACTURA?") == 0) {
                prefactura1 = true;
            }

            if (comanda1) {
                VistaImpresionComanda comanda = new VistaImpresionComanda(null, true, txtObservaciones.getText(), cmbVendedor.getSelectedItem().toString());
                comanda.setInstancias(instancias, lbNoFactura.getText(), true, null);
                comanda.setLocationRelativeTo(null);
                comanda.setVisible(true);
            }

            if (prefactura1) {
                BigDecimal totalNeto = big.getMoneda(txtSubTotal.getText());
                BigDecimal porcPropina = BigDecimal.ZERO, totalPropina = BigDecimal.ZERO;

                try {
                    porcPropina = big.getBigDecimal(DatosMaestra.getPorcPropina());
                } catch (Exception e) {
                }

                totalPropina = totalNeto.multiply(porcPropina).divide(big.getBigDecimal("100"));

                if (metodos.msgPregunta(null, "¿Desea incluir propina?") != 0) {
                    totalPropina = BigDecimal.ZERO;
                }

                String impresoraPrefactura = "";
                try {
                    impresoraPrefactura = DatosMaestra.getImpresoraPrefactura();
                } catch (Exception e) {
                }

                instancias.getReporte().ver_PrefacturaVenta("where idFactura = '" + "CONGELADA-" + lbNoFactura.getText() + "';", lbNoFactura.getText(),
                        txtObservaciones.getText(), lbNoFactura.getText(), "", instancias.getInformacionEmpresa(), totalPropina, DatosMaestra.isPrevisualizarPrefactura(), impresoraPrefactura);

                String copias = "";
                try {
                    copias = DatosMaestra.getCopiasPrefactura();
                } catch (Exception e) {
                }

                try {
                    if (copias != null || !copias.equals("")) {
                        for (int i = 0; i < Integer.parseInt(copias); i++) {
                            instancias.getReporte().ver_PrefacturaVenta("where idFactura = '" + "CONGELADA-" + lbNoFactura.getText() + "';", lbNoFactura.getText(),
                                    txtObservaciones.getText(), lbNoFactura.getText(), "", instancias.getInformacionEmpresa(), totalPropina, DatosMaestra.isPrevisualizarPrefactura(),
                                    impresoraPrefactura);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_btnReImprimirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        switch (this.tipoProceso) {
            case "facturacion":
                if (tblProductos.getRowCount() > 0 && !DatosMaestra.isBorrarMesas() && !instancias.getUsuario().equals("ADMIN")) {
                    solicitarPermisoParaLimpiar();
                    return;
                }

                if (tblProductos.getRowCount() == 0 || metodos.msgPregunta(null, "¿Desea limpiar la factura?") == 0) {
                    limpiar(true);
                }
                break;
            case "mesa":
                if (tblProductos.getRowCount() > 0 && !DatosMaestra.isBorrarMesas() && !instancias.getUsuario().equals("ADMIN")) {
                    solicitarPermisoParaLimpiar();
                    return;
                }

                boolean esRestaurante = instancias.getConfiguraciones().isRestaurante();
                String mensaje = esRestaurante ? "¿Desea limpiar la mesa?" : "¿Desea limpiar la congelada?";
                if (tblProductos.getRowCount() == 0 || ControladorAlertas.option(mensaje)) {
                    limpiar();
                }
                break;
            default:
                limpiar(true);
                break;
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        instancias.setCancelarFactura(false);
        if (btnGuardar.getText().equals("FACTURAR") && this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            convertirDocumentoAFactura(false, "");
        } else {
            validacionInicialFactura(false);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        instancias.setCancelarFactura(false);
        if (btnGuardar.getText().equals("FACTURAR") && this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            convertirDocumentoAFactura(true, "");
        } else {
            validacionInicialFactura(true);
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        instancias.setCancelarFactura(false);

        String baseUtilizada = "bdProductos";
        if (instancias.getConfiguraciones().isRestaurante()) {
            agregarAdicionesATabla(baseUtilizada);
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            calcularTabla(i, true);
        }

        tblProductos.removeEditor();

        ResultadoValidacionInventario resultadoValidacion = saltarPasosFactura ? null : validarInventarioProductos(baseUtilizada, lineasOriginales);

        ModeloContacto datosCliente = !ID_CLIENTE_CARGADO.equals("")
                ? instancias.getSql().getDatosTercero(ID_CLIENTE_CARGADO)
                : new ModeloContacto();

        if (!validacionesGeneralesDeFacturacion(datosCliente, resultadoValidacion, DatosMaestra.isFacturarSinInventario())) {
            borrarAdiciones();
            return;
        }

        if (!saltarPasosFactura && metodos.msgPregunta(null, "¿Desea continuar?") != 0) {
            borrarAdiciones();
            return;
        }

        String prefijoGeneral = TipoDocumento.obtenerPrefijoGeneralPorValor(this.tipoProceso);
        String factura = prefijoGeneral + "-" + lbNoFactura.getText();
        String por = cmbRtf.getSelectedIndex() == 0 ? "0" : cmbRtf.getSelectedItem().toString();
        String vendedor = cmbVendedor.getSelectedItem().toString().equals("Seleccione un vendedor") ? "" : cmbVendedor.getSelectedItem().toString();

        String tipoComprobante = obtenerTipoComprobante();
        String congelada = funcionalidadVentas.obtenerNumeroCongelada(instancias, this.tipoProceso);
        int turno = obtenerTurno();
        String fechaFactura = metodos.fechaConsulta(metodosGenerales.fechaHora());
        BigDecimal copago = BigDecimal.ZERO;
        try {
            copago = big.getMoneda(txtCopago.getText());
        } catch (Exception e) {
        }

        MovimientoDocumento movimiento = new MovimientoDocumento(
                tipoComprobante, factura, factura, prefijoGeneral, vendedor,
                congelada, turno, fechaFactura, copago, por);

        if (this.tipoProceso.equals(TipoDocumento.PEDIDO.getValor())) {
            try {
                revertirInventarioOriginal();
            } catch (SQLException ex) {
                Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                metodos.msgError(null, "Error al revertir el inventario original del pedido");
                return;
            }

            instancias.getSql().eliminarComanda(factura, "pedido");
            instancias.getSql().eliminarPedido(factura);

            if (!agregarMovimientoPedido(movimiento)) {
                return;
            }

            actualizarInventarioDocumento(TipoDocumento.PEDIDO);
            if (!saltarPasosFactura) {
                metodos.msgExito(null, "Pedido modificado con éxito");
            }

            limpiar(true);

        } else if (this.tipoProceso.equals(TipoDocumento.ORDER_SERVICIO.getValor())) {
            try {
                revertirInventarioOriginal();
            } catch (SQLException ex) {
                Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                metodos.msgError(null, "Error al revertir el inventario original de la orden");
                return;
            }

            daoOrdenServicio.eliminarVehiculo(factura);
            daoOrdenServicio.eliminarDetalle(factura);
            instancias.getSql().eliminarOServicio(factura);

            if (!agregarMovimientoOrdenServicio(movimiento)) {
                return;
            }

            actualizarInventarioDocumento(TipoDocumento.ORDER_SERVICIO);
            metodos.msgExito(null, "Orden modificada con éxito");
            limpiar(true);

        } else if (this.tipoProceso.equals(TipoDocumento.CUENTA_COBRO.getValor())) {
            instancias.getSql().eliminarCuentaCobro(factura);

            if (!agregarMovimientoCuentaCobro(movimiento)) {
                return;
            }

            if (txtUltimaFacturaFecha.getText().equals("")) {
                if (!instancias.getSql().modificarFechaUltimoPago(factura, txtCantFacturados.getText())) {
                    metodos.msgError(null, "Error al agregar la fecha de pago");
                    return;
                }
            } else {
                if (!instancias.getSql().modificarFechaUltimoPago(factura, txtUltimaFacturaFecha.getText(), txtCantFacturados.getText())) {
                    metodos.msgError(null, "Error al agregar la fecha de pago");
                    return;
                }
            }

            metodos.msgExito(null, "Plantilla modificada con éxito");
            limpiar(true);

        } else if (this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            try {
                revertirInventarioOriginal();
            } catch (SQLException ex) {
                Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                ControladorAlertas.alertFail("Error al revertir el inventario original de la mesa");
                return;
            }

            instancias.getSql().eliminarMesa(factura);
            instancias.getSql().eliminarComanda(factura, "congelada");

            if (!agregarMovimientoMesa(movimiento)) {
                return;
            }

            actualizarInventarioDocumento(TipoDocumento.MESA);

            if (!saltarPasosFactura) {
                if (instancias.getConfiguraciones().isRestaurante()) {

                    Boolean existeProductoNuevo = false;
                    List<Object> productosIniciales = new ArrayList<>();
                    for (int i = 0; i < tblProductos.getRowCount(); i++) {
                        Object valor = tblProductos.getValueAt(i, 30);

                        if (!"Nuevo".equals(valor)) {
                            productosIniciales.add(valor);
                        } else {
                            existeProductoNuevo = true;
                        }
                    }

                    if (existeProductoNuevo) {
                        VistaImpresionComanda comanda = new VistaImpresionComanda(null, true, txtObservaciones.getText(), cmbVendedor.getSelectedItem().toString());
                        comanda.setInstancias(instancias, lbNoFactura.getText(), false, productosIniciales);
                        comanda.setLocationRelativeTo(null);
                        comanda.setVisible(true);
                    }

                    //metodos.msgExito(null, "Mesa modificada con éxito");
                } else {
                    ControladorAlertas.alertSuccess("Congelada modificada con éxito");
                }
            }

            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getMenu().cambiarTitulo("MESAS");
                instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
                instancias.getMesas().cargarRegistrosMesas();
                instancias.getMesas().cargarRegistros();
                instancias.getMesas().setSelected(true);
            } else {
                instancias.getMesas().setSelected(true);
            }

            if (!instancias.getMenu().getSeVeElMenu()) {
                instancias.getMenu().expandirMenu();
            }
        }

        saltarPasosFactura = false;
    }//GEN-LAST:event_btnModificarActionPerformed

    private void lbFacturaNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyReleased

    private void lbFacturaNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyTyped

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        String baseUtilizada = "bdProductos";

        if (evt.getClickCount() > 1 && (tblProductos.getSelectedColumn() == 5 || tblProductos.getSelectedColumn() == 6)) {
            if (instancias.getConfiguraciones().isFacturaElectronica()) {
                abrirModalDescuentosProducto(tblProductos.getSelectedRow());
            }
        }

        if (!this.tipoProceso.equals(TipoDocumento.COTIZACION.getValor())) {
            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), baseUtilizada);

            if (nodo.getUsuario().equalsIgnoreCase(TipoProducto.PRODUCTO_DISENADO.getValue()) && evt.getClickCount() >= 1 && tblProductos.getSelectedColumn() == 1) {
                if (!this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
                    instancias.getMenu().ocultarMenu("preparacion");
                }

                instancias.getPreparacion().cargarDatos(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(),
                        tblProductos.getValueAt(tblProductos.getSelectedRow(), 21).toString(), mesaCongelada,
                        String.valueOf(tblProductos.getSelectedRow()), this.tipoProceso);

                try {
                    instancias.getPreparacion().setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String tipoProductoSeleccionado = null == nodo.getTipoProducto() ? "" : DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());
            if (instancias.getConfiguraciones().isProductosDetallados()
                    && !tipoProductoSeleccionado.isEmpty()
                    && evt.getClickCount() >= 1
                    && tblProductos.getSelectedColumn() == 3) {
                if (evt.getClickCount() >= 1 && tblProductos.getSelectedColumn() == 3) {
                    metodos.msgAdvertenciaAjustado(null, "La cantidad no se puede modificar");
                    return;
                }
            }
        }

        if (tblProductos.getSelectedColumn() == 22) {
            int fila = tblProductos.getSelectedRow();
            if (tblProductos.getValueAt(fila, 16).equals("REALIZADO")) {
                metodos.msgAdvertencia(null, "No puede Borrar este producto");
                return;
            }

            if (!DatosMaestra.isBorrarCongelada()) {
                if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && !instancias.getUsuario().equals("ADMIN")) {
                    if (metodos.msgPregunta(null, "No se puede borrar ¿Pedir permiso?") == 0) {
                        dlgPedirPermiso permiso = new dlgPedirPermiso(null, true, "mesa");
                        permiso.setLocationRelativeTo(null);
                        permiso.setVisible(true);
                        return;
                    } else {
                        return;
                    }
                }
            }
            eliminarFila();
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    private void tblProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblProductosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            popBorrarActionPerformed(null);
            return;
        }

        String baseUtilizada = "bdProductos";

        int fila = tblProductos.getSelectedRow(), i = 2, j = 0;

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (instancias.isLector()) {

                switch (tblProductos.getSelectedColumn()) {
                    case 1:
                        if (!DatosMaestra.isModificarNombre()) {
                            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                            tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                        }
                        break;
                    case 2:
                        if (!DatosMaestra.isModificarPrecio()) {
                            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                        }

                        if (tblProductos.getValueAt(fila, 2).toString().equalsIgnoreCase("")) {
                            tblProductos.setValueAt("0", fila, 3);
                        }
                        break;
                    case 3:
                        if (tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
//                        tblProductos.setValueAt("1", fila, 3);
                        }
                        i = 3;
                        break;
                    default:
                        break;
                }

                if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                        || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {

                } else {
                    txtCodigoProducto.requestFocus();
                }

            } else {

                if (tblProductos.getSelectedColumn() == 0) {

                    try {
                        int r = tblProductos.getSelectedRow();
                        tblProductos.changeSelection(r, 0, false, false);
                        tblProductos.removeEditor();

                        tblProductos.editCellAt(r, 3);
                        tblProductos.setColumnSelectionInterval(3, 3);
                        tblProductos.transferFocus();
                    } catch (Exception e) {
                    }

                } else if (tblProductos.getSelectedColumn() == 1) {

                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                    if (!DatosMaestra.isModificarNombre()) {
                        tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                    }

                    tblProductos.editCellAt(fila, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();

                } else if (tblProductos.getSelectedColumn() == 2) {
                    if (tblProductos.getValueAt(fila, 32).equals("IMP01")) {
                        tblProductos.setValueAt(this.simbolo + " 0", fila, 2);
                    }

                    if (tblProductos.getValueAt(fila, 2).toString().equalsIgnoreCase("")) {
                        tblProductos.setValueAt("0", fila, 3);
                    }

                    if (!DatosMaestra.isModificarNombre()) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                        tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                    }

                    if (!DatosMaestra.isModificarPrecio()) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                        String lista = tblProductos.getValueAt(tblProductos.getSelectedRow(), 37).toString();
                        if (lista.equals("L1")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L2")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L3")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), tblProductos.getSelectedRow(), 2);
                        } else if (lista.equals("L4")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), tblProductos.getSelectedRow(), 2);
                        }
                    }

                    tblProductos.editCellAt(fila, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();
                } else if (tblProductos.getSelectedColumn() == 3) {

                    if (!DatosMaestra.isModificarPrecio()) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                        String lista = tblProductos.getValueAt(fila, 37).toString();
                        if (lista.equals("L1")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                        } else if (lista.equals("L2")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), fila, 2);
                        } else if (lista.equals("L3")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), fila, 2);
                        } else if (lista.equals("L4")) {
                            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), fila, 2);
                        }
                    }

                    String cant = tblProductos.getValueAt(tblProductos.getSelectedRow(), 3).toString();
                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                    String tipo = "";
                    if (nodo.getTipoProducto() != null) {
                        if (nodo.getTipoProducto().equals("IMEI")) {
                            tipo = "Imei";
                        } else if (nodo.getTipoProducto().equals("Fecha/Lote")) {
                            tipo = "Fecha/Lote";
                        } else if (nodo.getTipoProducto().equals("Color")) {
                            tipo = "Color";
                        } else if (nodo.getTipoProducto().equals("Serial")) {
                            tipo = "Serial";
                        } else if (nodo.getTipoProducto().equals("Talla")) {
                            tipo = "Talla";
                        } else if (nodo.getTipoProducto().equals("ColorTalla")) {
                            tipo = "ColorTalla";
                        } else if (nodo.getTipoProducto().equals("SerialColor")) {
                            tipo = "SerialColor";
                        } else {
                            tipo = "";
                        }
                    }

                    if (!tipo.equals("")) {
                        tblProductos.setValueAt(cant, fila, 3);
                    }

                    if (tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("") || tblProductos.getValueAt(fila, 3).toString().equalsIgnoreCase("0")) {
//                        tblProductos.setValueAt("1", fila, 3);
                    }

                    if (instancias.getConfiguraciones().isFacturaElectronica()) {
                        txtCodigoProducto.requestFocus();
                    } else {
                        tblProductos.editCellAt(fila, 5);
                        tblProductos.setColumnSelectionInterval(5, 5);
                        tblProductos.transferFocus();
                    }

                    i = 3;
                } else if (tblProductos.getSelectedColumn() == 5) {
                    // Si se esta cambiando el campo de descuento

                    if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                            || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {

                    } else {
                        txtCodigoProducto.requestFocus();
                    }

                    i = 5;
                    j = 0;
                }
            }

            if (tblProductos.getSelectedColumn() == 17) {
                tblProductos.editCellAt(tblProductos.getSelectedRow(), 17);
                tblProductos.setColumnSelectionInterval(17, 17);
                try {
                    tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 17).toString())), fila, 17);
                } catch (Exception e) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 17);
                }
                tblProductos.transferFocus();

            }

            if (tblProductos.getSelectedColumn() == 19) {
                try {
                    tblProductos.setValueAt(big.setMoneda(big.getMoneda(tblProductos.getValueAt(fila, 19).toString())), fila, 19);
                    BigDecimal resta = big.getMoneda(tblProductos.getValueAt(fila, 9).toString()).subtract(big.getMoneda(tblProductos.getValueAt(fila, 19).toString()));
                    tblProductos.setValueAt(resta, fila, 20);

                    if (resta.compareTo(BigDecimal.ZERO) < 0) {
                        ControladorAlertas.alert("No tiene ninguna utilidad!");
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 19);
                    }

                } catch (Exception e) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal("0")), fila, 19);
                }
                tblProductos.transferFocus();
            }

            BigDecimal porcentajeDescuento = big.getBigDecimal(tblProductos.getValueAt(fila, 5).toString().replace(",", "."));

            int res = porcentajeDescuento.compareTo(big.getBigDecimal("0"));
            if (res == -1 || String.valueOf(tblProductos.getValueAt(fila, i)).equals("")) {
                tblProductos.setValueAt(j, fila, 5);
            }

            if (tblProductos.getSelectedColumn() == 24) {
                tblProductos.editCellAt(fila, 25);
                tblProductos.setColumnSelectionInterval(25, 25);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 25) {
                tblProductos.editCellAt(fila, 26);
                tblProductos.setColumnSelectionInterval(26, 26);
                tblProductos.transferFocus();
            } else if (tblProductos.getSelectedColumn() == 26) {
                txtCodigoProducto.transferFocus();
            }

            calcularTabla(fila, true);
        } else if (evt.getKeyCode() == KeyEvent.VK_TAB || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN
                || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {

            if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                fila = fila - 1;
            } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
                fila = fila + 1;
            }

            if (fila < 0) {
                fila = 0;
            }
            if (tblProductos.getRowCount() == fila) {
                fila = fila - 1;

            }

            calcularTabla(fila, true);
        }

        if ((Boolean) tblProductos.getValueAt(fila, 36) == true) {
            BigDecimal num1 = big.getMoneda(tblProductos.getValueAt(fila, 38).toString());
            BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
            if (!lineasOriginales.isEmpty()) {
                String idProducto = obtenerValorTabla(fila, 32);
                num2 = num2.subtract(getCantidadOriginalProducto(idProducto));
            }
            BigDecimal total = num1.subtract(num2);
            tblProductos.setValueAt(big.setNumero(total), fila, 39);
        } else {
            tblProductos.setValueAt("N/A", fila, 38);
            tblProductos.setValueAt("N/A", fila, 39);
        }
    }//GEN-LAST:event_tblProductosKeyReleased

    private void lbNit1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbNit1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbNit1KeyReleased

    private void lbDireccion8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDireccion8MouseClicked

    }//GEN-LAST:event_lbDireccion8MouseClicked

    private void tblInventarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarioMouseClicked

    }//GEN-LAST:event_tblInventarioMouseClicked

    private void txtFechaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaFacturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaFacturaActionPerformed

    private void btnCambiarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarMesaActionPerformed
        String num = instancias.getSql().getNumeroMesas();

        Object[][] congeladas = instancias.getSql().getDatosCongelada1();
        if (congeladas.length >= Integer.parseInt(num)) {
            metodos.msgAdvertenciaAjustado(null, "No hay mesas disponibles");
            return;
        }

        String prefijoGeneral = TipoDocumento.obtenerPrefijoGeneralPorValor(this.tipoProceso);
        String factura = prefijoGeneral + "-" + lbNoFactura.getText();
        dlgEscojerMesa mesa = new dlgEscojerMesa(null, true, factura);
        mesa.setVisible(true);

        if (cambioMesa) {
            //btnVolverMouseClicked(null);
//            instancias.getMesas().cargarRegistrosMesas();
//            instancias.getMesas().cargarRegistros();
//            instancias.getMesas().setSelected(true);
//
//            if (!instancias.getMenu().getSeVeElMenu()) {
//                instancias.getMenu().expandirMenu();
//            }
        }
    }//GEN-LAST:event_btnCambiarMesaActionPerformed

    private void lbTotalDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuentoMouseClicked

    }//GEN-LAST:event_lbTotalDescuentoMouseClicked

    private void lbNitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbNitMouseClicked

    }//GEN-LAST:event_lbNitMouseClicked

    private void rdPosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdPosItemStateChanged

    }//GEN-LAST:event_rdPosItemStateChanged

    private void rdCartaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdCartaItemStateChanged

    }//GEN-LAST:event_rdCartaItemStateChanged

    private void rdMediaCartaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdMediaCartaItemStateChanged

    }//GEN-LAST:event_rdMediaCartaItemStateChanged

    private void btnBuscTercerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscTercerosActionPerformed
        ventanaTerceros("");
    }//GEN-LAST:event_btnBuscTercerosActionPerformed

    private void txtTurnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurnoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurnoKeyReleased

    private void txtTurnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTurnoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurnoKeyTyped

    private void rdMediaCartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMediaCartaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdMediaCartaActionPerformed

    private void btnBusProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBusProdActionPerformed
        try {
            int num = Integer.parseInt(DatosMaestra.getLimite());
            if (!rdPos.isSelected()) {
                if (num > 0) {
                    if (tblProductos.getRowCount() >= num) {
                        if (!ControladorAlertas.option("Limite de productos, ¿Desea continuar?")) {
                            return;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
        }

        ventanaProductos("");
    }//GEN-LAST:event_btnBusProdActionPerformed

    private void btnInformacionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionClienteActionPerformed
        dlgInformacionCliente notaCliente = new dlgInformacionCliente(null, true, ID_CLIENTE_CARGADO);
        notaCliente.setLocationRelativeTo(null);
        notaCliente.setVisible(true);
    }//GEN-LAST:event_btnInformacionClienteActionPerformed

    private void txtDiasPlazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasPlazoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasPlazoActionPerformed

    private void txtPorcentajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPorcentajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPorcentajeActionPerformed

    private void lbProducto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbProducto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbProducto1KeyReleased

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadFocusGained

    private void txtCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyPressed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void btnNuevaParte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaParte1ActionPerformed
        infNuevaParte buscador = new infNuevaParte();
        String tipo = txtTipoVehiculo.getText();
        buscador.cargarArticulos("Moto");
        buscador.setVisible(true);
    }//GEN-LAST:event_btnNuevaParte1ActionPerformed

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked

    }//GEN-LAST:event_txtNombreMouseClicked

    private void txtObservacionesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionesKeyReleased
        int cantidad = txtObservaciones.getText().length();
        cantidad = 300 - cantidad;

        lbCar.setText(String.valueOf(cantidad));

        if (cantidad <= 0) {
            txtObservaciones.setText(txtObservaciones.getText().substring(0, 300));
            lbCar.setText("0");
        }
    }//GEN-LAST:event_txtObservacionesKeyReleased

    private void txtTipoVehiculoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVehiculoKeyPressed

    }//GEN-LAST:event_txtTipoVehiculoKeyPressed

    private void txtTipoVehiculoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoVehiculoKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!txtTipoVehiculo.getText().equals("")) {
                cargarArticulos(txtTipoVehiculo.getText());
                txtModelo.requestFocus();
            } else {
                ventanaTipoVehiculos(txtTipoVehiculo.getText());
            }
        } else {
            txtTipoVehiculo.setText("");
        }
    }//GEN-LAST:event_txtTipoVehiculoKeyReleased

    private void cmbVendedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbVendedorMouseClicked

    }//GEN-LAST:event_cmbVendedorMouseClicked

    private void txtCupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCupoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCupoActionPerformed

    private void txtCupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCupoKeyReleased

    private void txtCupoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCupoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCupoKeyTyped

    private void txtCarteraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCarteraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarteraActionPerformed

    private void txtCarteraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarteraKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarteraKeyReleased

    private void txtCarteraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCarteraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCarteraKeyTyped

    private void lbTotalDescuento1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuento1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTotalDescuento1MouseClicked

    private void txtCantUnidadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantUnidadesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantUnidadesMouseClicked

    private void lbTotalDescuento2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTotalDescuento2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTotalDescuento2MouseClicked

    private void txtCantProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCantProductosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantProductosMouseClicked

    private void lbDiasPlazoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbDiasPlazoMouseClicked

    }//GEN-LAST:event_lbDiasPlazoMouseClicked

    private void lbProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProductoMouseClicked

    }//GEN-LAST:event_lbProductoMouseClicked

    private void txtDescGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescGeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescGeneralActionPerformed

    private void txtDescGeneralKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescGeneralKeyReleased
        if (txtDescGeneral.getText().equals("") || txtDescGeneral.getText().equals(this.simbolo) || txtDescGeneral.getText().equals(this.simbolo + " ")) {
            txtDescGeneral.setText("0");
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTotales();
        } else {
            txtDescGeneral.setText(big.setMoneda(big.getMoneda(txtDescGeneral.getText())));
            cargarTotales();
        }
    }//GEN-LAST:event_txtDescGeneralKeyReleased

    private void txtDescGeneralKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescGeneralKeyTyped
        metodos.soloNum(evt);
    }//GEN-LAST:event_txtDescGeneralKeyTyped

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

    private void dtDesdeOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtDesdeOnCommit
        try {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), Integer.parseInt(txtDiasPlazo.getText())));
        } catch (NumberFormatException exep) {
            txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), 0));
        }
    }//GEN-LAST:event_dtDesdeOnCommit

    private void dtHastaOnCommit(datechooser.events.CommitEvent evt) {//GEN-FIRST:event_dtHastaOnCommit
        // TODO add your handling code here:
    }//GEN-LAST:event_dtHastaOnCommit

    private void txtCantFacturadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantFacturadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantFacturadosActionPerformed

    private void txtCantIncrementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantIncrementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantIncrementoActionPerformed

    private void txtUltimaFacturaFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUltimaFacturaFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUltimaFacturaFechaActionPerformed

    private void txtPorcentajeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPorcentajeMouseClicked
        if (instancias.getConfiguraciones().isFacturaElectronica() && tblProductos.getRowCount() > 0) {
            if (instancias.getDescuento().equals("peso")) {
                ControladorAlertas.bigAlert("Cuando el tipo de descuento es en pesos ($), esta opción general no está disponible. "
                        + "Debes asignar el descuento directamente al producto específico.");
                txtCodigoProducto.requestFocus();
                return;
            }

            txtCodigoProducto.requestFocus();
            String tipoOpcion = "Opcion-General";
            if (DESCUENTO_GENERAL_CARGADO) {
                tipoOpcion = tblProductos.getValueAt(0, 31).toString();
            }

            dlgTipoDescuento descuentoProd = new dlgTipoDescuento(null, null, null, null, tipoOpcion, BigDecimal.ZERO, this.tipoProceso);
            descuentoProd.setVisible(true);
        }
    }//GEN-LAST:event_txtPorcentajeMouseClicked

    private void lbFacturaNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbFacturaNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoActionPerformed

    private void cmbVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVendedorActionPerformed

    private void lbOtroConsecutivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbOtroConsecutivoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbOtroConsecutivoKeyTyped

    private void lbOtroConsecutivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbOtroConsecutivoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbOtroConsecutivoKeyReleased

    private void lbOtroConsecutivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbOtroConsecutivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbOtroConsecutivoActionPerformed

    public void inicializarPanelGruposEmbebido() {
        Object[][] grupos = instancias.getSql().getGruposVisualizarFactura();

        if (!instancias.getConfiguraciones().isRestaurante() || grupos.length == 0) {
            pnlGrupos.setVisible(false);
            return;
        }

        panelGruposEmbebido = new PanelGruposCompacto();
        panelGruposEmbebido.setListener(new PanelGruposCompacto.ListenerGrupo() {
            @Override
            public void grupoSeleccionado(String codigo, String nombre) {
                VistaGruposProductos dlg = new VistaGruposProductos(null, true, tipoProceso);
                dlg.seleccionarGrupo(codigo, nombre);
                dlg.setVisible(true);
            }
        });

        panelGruposEmbebido.setGrupos(grupos);

        int n = grupos != null ? grupos.length : 0;
        int alturaVisible = Math.min(n * PanelGruposCompacto.CARD_H + 10, 196);

        JScrollPane scroll = new JScrollPane(panelGruposEmbebido);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(PanelGruposCompacto.CARD_H);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new java.awt.Dimension(200, alturaVisible));

        pnlGrupos.setLayout(new BorderLayout());
        pnlGrupos.add(scroll, BorderLayout.CENTER);
        pnlGrupos.revalidate();
        pnlGrupos.repaint();
    }

    private void btnPasarACongeladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasarACongeladaActionPerformed
        String slotDisponible = funcionalidadVentas.buscarCongeladaDisponible(instancias);
        if (slotDisponible == null) {
            ControladorAlertas.alert("No existen congeladas disponibles");
            return;
        }

        instancias.setCancelarFactura(false);
        String tipoProcesoOriginal = this.tipoProceso;
        String tituloOriginal = instancias.getTitulo();

        this.tipoProceso = TipoDocumento.MESA.getValor();
        instancias.setTitulo(slotDisponible);
        pasandoACongelada = true;

        String resultado = validacionInicialFactura(false);
        pasandoACongelada = false;

        if (resultado != null && !resultado.isEmpty()) {
            String numeroCongelada = slotDisponible.replace("CONGELADA-", "");
            this.tipoProceso = tipoProcesoOriginal;
            this.saltarPasosFactura = false;
            instancias.setTitulo(tituloOriginal);
            limpiar(true);

            ControladorAlertas.bigAlert("Congelada #" + numeroCongelada + " guardada con éxito");
        }
    }//GEN-LAST:event_btnPasarACongeladaActionPerformed

    public void actualizarResolucion(int fila) {
        if (tblComprobantes.getRowCount() == 0) {
            return;
        }

        if (Constantes.FACTURACION_ELECTRONICA_POS.equals(tblComprobantes.getValueAt(fila, 7))) {
            rdPos.setSelected(true);
        } else if (Constantes.FACTURACION_ELECTRONICA.equals(tblComprobantes.getValueAt(fila, 7))) {
            if (instancias.getImpresion().equals("facturaCompleta")) {
                rdCarta.setSelected(true);
            } else {
                rdMediaCarta.setSelected(true);
            }
        }

        String resolucion = "", numeracion = "", infoResolucion = "", infoEmpresa = "";

        if (DatosMaestra.getD1() != null) {
            infoEmpresa = infoEmpresa + "" + DatosMaestra.getD1() + "";
        }

        if (DatosMaestra.getD2() != null) {
            infoEmpresa = infoEmpresa + "\n" + DatosMaestra.getD2();
        }

        if (DatosMaestra.getD3() != null) {
            infoEmpresa = infoEmpresa + "\n" + DatosMaestra.getD3();
        }

        if (DatosMaestra.getD6() != null) {
            infoEmpresa = infoEmpresa + "\n" + DatosMaestra.getD6();
        }

        if (DatosMaestra.getD7() != null) {
            infoEmpresa = infoEmpresa + "\n" + DatosMaestra.getD7();
        }

        String dato1 = "", dato2 = "";
        if (DatosMaestra.getLegal() != null) {
            dato1 = DatosMaestra.getLegal();
        }

        if (DatosMaestra.getPie() != null) {
            dato2 = DatosMaestra.getPie();
        }

        if (instancias.getRegimen().equals("")) {
            String resol = "", desde = "", hasta = "", fecha = "";

            try {
                resol = tblComprobantes.getValueAt(fila, 3).toString();
            } catch (Exception e) {
            }

            try {
                fecha = tblComprobantes.getValueAt(fila, 4).toString();
            } catch (Exception e) {
            }

            try {
                desde = tblComprobantes.getValueAt(fila, 5).toString();
            } catch (Exception e) {
            }

            try {
                hasta = tblComprobantes.getValueAt(fila, 6).toString();
            } catch (Exception e) {
            }

            if (!resol.equals("")) {
                resolucion = "RESOLUCIÓN DIAN " + resol + " " + fecha;
                numeracion = "NUMERACION DESDE " + desde + " HASTA " + hasta;
            }

            infoResolucion = infoResolucion + "\n" + resolucion + "\n" + numeracion;

            actualizarConsecutivo(fila);
            String datosEmpresa = metodosGenerales.convertToMultiline(infoEmpresa);
            String datosEmpresaReimpresion = infoEmpresa;
            String datosEmpresaCompleto = metodosGenerales.convertToMultiline(infoEmpresa + "" + infoResolucion);

            instancias.setInformacionEmpresaCompleto(datosEmpresaCompleto);
            instancias.setDatosEmpresa(datosEmpresa, dato1, dato2, datosEmpresaCompleto, datosEmpresaReimpresion);
            instancias.setResolucion(resolucion + "\n" + numeracion);
        } else {
            if (DatosMaestra.getD4() != null) {
                resolucion = DatosMaestra.getD4();
            }

            if (DatosMaestra.getD5() != null) {
                numeracion = DatosMaestra.getD5();
            }

            if (resolucion.equals("") || numeracion.equals("")) {
            } else {
                infoResolucion = infoResolucion + "\n" + resolucion + "\n" + numeracion;
            }

            String datosEmpresa = metodosGenerales.convertToMultiline(infoEmpresa);
            String datosEmpresaReimpresion = infoEmpresa;
            String datosEmpresaCompleto = metodosGenerales.convertToMultiline(infoEmpresa + "" + infoResolucion);
            instancias.setDatosEmpresa(datosEmpresa, dato1, dato2, datosEmpresaCompleto, datosEmpresaReimpresion);
            instancias.setInformacionEmpresaCompleto(datosEmpresaCompleto);
            instancias.setResolucion(resolucion + "\n" + numeracion);
        }

        //        if (null != instancias.getDiasAlertaResolucion()) {
        //            long aux = metodos.restarFecha(metodosGenerales.fecha(), metodos.sumarFecha(metodos.fecha(informacion[1].toString()),
        //                    Integer.parseInt(instancias.getDiasAlertaResolucion())));
        //            instancias.setDiasRestantesResolucion(aux + "");
        //        }
    }

    public void limpiar() {
        instancias.setCancelarFactura(false);
        limpiar(true);
        redireccionarRestauranteAlLimpiar();
    }

    private void cargarMovimiento() {
        if (this.tipoProceso.equals(TipoDocumento.COTIZACION.getValor())) {
            cargarMovimientoCotizacion();
        } else if (this.tipoProceso.equals(TipoDocumento.ORDER_SERVICIO.getValor())) {
            cargarMovimientoOrdenServicio();
        } else if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())) {
            cargarMovimientoFactura();
        } else if (this.tipoProceso.equals(TipoDocumento.PLAN_SEPARE.getValor())) {
            cargarMovimientoPlanSepare();
        } else if (this.tipoProceso.equals(TipoDocumento.PEDIDO.getValor())) {
            cargarMovimientoPedido();
        } else if (this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            cargarMovimientoMesa();
        }

        tblProductos.removeEditor();
    }

    private void cargarMovimientoCotizacion() {
        String idCotizacion = "COTI-" + txtCargar.getText();
        DocumentoMovimiento doc = daoFactura.cargarCotizacion(idCotizacion);
        if (doc.isEmpty()) {
            ControladorAlertas.alert("La cotización no existe");
            return;
        }

        if ("REALIZADO".equals(doc.getCabecera().getEstadoGeneral())) {
            metodos.msgAdvertenciaAjustado(null, "La cotización ya ha sido facturada.");
            txtCargar.setText("");
            return;
        }

        limpiar(true);
        cargarLineasCotizacion(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), true);

        btnGuardar.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnReImprimir.setVisible(true);
        btnReImprimir.setEnabled(true);
        txtCargar.setText(idCotizacion.replace("COTI-", ""));
    }

    private void cargarMovimientoOrdenServicio() {
        String orden = "OSERV-" + txtCargar.getText();
        DocumentoMovimiento doc = daoFactura.cargarOrdenServicio(orden);

        if (doc.isEmpty()) {
            ControladorAlertas.alert("La orden de servicio no existe");
            return;
        }

        limpiar(false);
        cargarLineasDocumento(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), false);
        this.lineasOriginales = doc.getLineas();
        recalcularInventarioTabla();

        ndOServicio nodoOrden = daoFactura.getDatosOServicio(orden);
        cargarDatosVehiculo(nodoOrden);
        aplicarEstadoVehiculo(daoFactura.getEstadoVehiculo(orden));

        txtNombre.requestFocus();

        if ("REALIZADO".equals(doc.getCabecera().getEstadoGeneral())) {
            metodos.msgAdvertencia(null, "Esta orden ya ha sido facturada y no se puede modificar.");
            activarCampos(false);
            btnModificar.setEnabled(false);
            btnReImprimir.setEnabled(true);
        } else {
            activarCampos(true);
            btnModificar.setEnabled(true);
            btnReImprimir.setEnabled(true);
        }

        lbNoFactura.setText(orden.substring(6));

        String placaReal = doc.getCabecera().getPlacaReal();
        if (placaReal != null) {
            txtPlaca1.setText(placaReal);
        }

        txtCargar.setText(orden.replace("OSERV-", ""));
    }

    private void cargarMovimientoFactura() {
        String idFactura = "FACT-" + txtCargar.getText();
        limpiar(false);

        DocumentoMovimiento doc = daoFactura.cargarMovimientoPrefactura(idFactura);
        if (doc.isEmpty()) {
            metodos.msgError(null, "La factura no existe");
            return;
        }

        cargarLineasDocumento(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), false);

        try {
            ndCxc nodoCxc = daoFactura.getDatosCxc(idFactura);
            txtDiasPlazo.setText(Integer.toString(nodoCxc.getPlazo()));
        } catch (Exception e) {
            txtDiasPlazo.setText("0");
        }

        calcularDiasPlazo(null);
    }

    private void cargarMovimientoPlanSepare() {
        String idSepare = "SEPARE-" + txtCargar.getText();
        DocumentoMovimiento doc = daoFactura.cargarPlanSepare(idSepare);

        if (doc.isEmpty()) {
            metodos.msgError(null, "El plan separe no existe");
            return;
        }

        limpiar(false);
        cargarLineasDocumento(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), false);

        lbNoFactura.setText(txtCargar.getText());
        btnReImprimir.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnImprimir.setEnabled(false);
    }

    private void cargarMovimientoPedido() {
        String numPedido = txtCargar.getText();
        String idPedido = "PEDIDO-" + numPedido;
        DocumentoMovimiento doc = daoFactura.cargarPedido(idPedido);

        if (doc.isEmpty()) {
            ControladorAlertas.alert("El pedido no existe");
            return;
        }

        limpiar(false);
        cargarLineasDocumento(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), false);
        this.lineasOriginales = doc.getLineas();
        recalcularInventarioTabla();

        String estadoGeneral = doc.getCabecera().getEstadoGeneral();
        if ("REALIZADO".equals(estadoGeneral)) {
            if (TipoDocumento.PEDIDO.getValor().equals(tipoProceso)) {
                metodos.msgAdvertencia(null, "Este pedido ya ha sido facturada y no se puede modificar.");
            } else {
                metodos.msgAdvertencia(null, "Este pedido ya ha sido facturada.");
                limpiar(true);
                txtCargar.requestFocus();
                return;
            }
            activarCampos(false);
            btnModificar.setEnabled(false);
            btnReImprimir.setEnabled(true);
        } else if ("ANULADA".equals(estadoGeneral)) {
            metodos.msgAdvertencia(null, "El pedido esta anulado.");
            limpiar(true);
            txtCargar.requestFocus();
            return;
        } else {
            activarCampos(true);
            btnModificar.setEnabled(true);
            btnReImprimir.setEnabled(true);
        }

        btnGuardar.setEnabled(false);
        btnImprimir.setEnabled(false);
        lbNoFactura.setText(numPedido);
    }

    private void configurarContextoCongelada(String tipo, String titulo) {
        tapControl.setSelectedIndex(0);
        btnReImprimir.setVisible(false);
        pnlCambiarMesa.setVisible(false);
        switch (tipo) {
            case "MESA":
                lbFacturaNo.setText("MESA No.");
                txtDiasPlazo.setEnabled(true);
                lbTitulo.setText(titulo);
                instancias.setTitulo(titulo);
                break;
            case "CONG":
                lbFacturaNo.setText("CONGELADA No.");
                txtDiasPlazo.setEnabled(true);
                lbTitulo.setText(titulo);
                instancias.setTitulo(titulo);
                break;
            default:
                lbFacturaNo.setText("DOMICILIO No.");
                txtDiasPlazo.setText("1");
                txtDiasPlazo.setEnabled(false);
                lbTitulo.setText("DOMICILIO");
                instancias.setTitulo("DOMICILIO");
                break;
        }
        if (instancias.getTipoImpresion().equals("Pos")) {
            rdPos.setSelected(true);
        }
        mesaCongelada = true;
        instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "OCUPADO");
    }

    public void abrirNuevaCongelada(String tipo, String titulo) {
        configurarContextoCongelada(tipo, titulo);
        limpiar(true);
        lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("CONGELADA")[0]);
        btnGuardar.setEnabled(true);
        btnGuardar.setVisible(true);
        if (instancias.getConfiguraciones().isRestaurante()) {
            btnImprimir.setVisible(tipo.equals("DOMICILIO"));
        }
        btnReImprimir.setVisible(false);
        btnModificar.setVisible(false);
        btnGuardar.setText("GUARDAR");
    }

    private void cargarMovimientoMesa() {
        String idMesa = "CONGELADA-" + txtCargar.getText();
        DocumentoMovimiento doc = daoFactura.cargarMesa(idMesa);
        if (doc.isEmpty()) {
            metodos.msgError(null, "La mesa no existe");
            return;
        }
        limpiar(false);
        cargarLineasDocumento(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), false);
        lbNoFactura.setText(txtCargar.getText());
        btnReImprimir.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnImprimir.setEnabled(false);
    }

    public void cargarMovimientoMesa(String nombreMesa) {
        String tipo = instancias.getConfiguraciones().isRestaurante() ? "MESA" : "CONG";
        configurarContextoCongelada(tipo, nombreMesa);
        ndCongelada congelada = instancias.getSql().getDatosCongelada1(nombreMesa);
        if (congelada.getIdFactura() == null) {
            metodos.msgError(null, "La mesa no existe");
            return;
        }
        if (congelada.getTurno() != null) {
            txtTurno.setText(congelada.getTurno());
        }
        ejecutarCargaMesa(congelada.getIdFactura(), nombreMesa);
    }

    private void ejecutarCargaMesa(String idCongelada, String titulo) {
        DocumentoMovimiento doc = daoFactura.cargarMesa(idCongelada);
        if (doc.isEmpty()) {
            metodos.msgError(null, "La mesa no existe");
            return;
        }

        limpiar(false);
        cargarLineasDocumento(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), false);

        this.lineasOriginales = doc.getLineas();
        recalcularInventarioTabla();
        for (int j = 0; j < tblProductos.getRowCount(); j++) {
            tblProductos.setValueAt("PLATO-" + j, j, 30);
        }

        borrarAdiciones();
        lbNoFactura.setText(idCongelada.replace("CONGELADA-", ""));
        if (titulo != null && titulo.contains("Mesa.")) {
            pnlCambiarMesa.setVisible(true);
        }

        btnGuardar.setText("FACTURAR");
        btnGuardar.setEnabled(true);
        btnGuardar.setVisible(true);
        btnModificar.setVisible(true);
        btnModificar.setEnabled(true);
        btnReImprimir.setEnabled(true);
        btnReImprimir.setVisible(true);
        btnLimpiar.setVisible(false);

        if (!instancias.getUsuario().equals("ADMIN") && !DatosMaestra.isFacturarMesas()) {
            Object[][] vendedores = instancias.getSql().getVendedores1();
            for (int ix = 0; ix < vendedores.length; ix++) {
                String asociado = "";
                try {
                    asociado = vendedores[ix][1].toString();
                } catch (Exception e) {
                }
                if (instancias.getUsuario().equals(asociado)) {
                    btnGuardar.setVisible(false);
                    btnImprimir.setVisible(false);
                }
            }
        }
    }

    private void cargarLineasCotizacion(List<LineaProducto> lineas) {
        for (int i = 0; i < lineas.size(); i++) {
            LineaProducto linea = lineas.get(i);
            cargarProducto(linea.getCodigo(), Utilidades.convertirBigDecimal(linea.getCantidad()), linea.getPlu(), "", "", "", false, "", "", "", "", "");
            tblProductos.setValueAt(linea.getRango(), i, 31);
            tblProductos.setValueAt(linea.getDescripcion(), i, 1);
            tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(linea.getPrecio())), i, 2);
            tblProductos.setValueAt(big.getBigDecimal(linea.getPorcDescuento()), i, 5);
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.setRowSelectionInterval(i, i);
            tblProductosKeyReleased(new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER));
        }
    }

    private void cargarLineasDocumento(List<LineaProducto> lineas) {
        for (int i = 0; i < lineas.size(); i++) {
            LineaProducto linea = lineas.get(i);
            String imeiSerial = linea.getImei() != null ? linea.getImei() : "";
            String idProductoDetallado = linea.getIdProd() != null ? linea.getIdProd() : "";

            cargarProducto(linea.getCodigo(), Utilidades.convertirBigDecimal(linea.getCantidad()), linea.getPlu(),
                    imeiSerial, "", idProductoDetallado, false, "", "", "", "", "");
            String rango = linea.getRango();
            if (rango != null && !rango.isEmpty()) {
                tblProductos.setValueAt(rango, i, 31);
            }
            tblProductos.setValueAt(linea.getDescripcion(), i, 1);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(linea.getPrecio())), i, 2);
            tblProductos.setValueAt(linea.getPorcDescuento().replace(",", "."), i, 5);
            tblProductos.setValueAt(linea.getDescuento().replace(".", ","), i, 6);
            tblProductos.setValueAt(linea.getPreparacion(), i, 21);
            calcularTabla(i, false);
        }
    }

    private void mostrarResumenDocumento(CabeceraDocumento cabecera, boolean monedaExacta) {
        if (cabecera.getClienteId() != null) {
            txtNit.setText(cabecera.getClienteId());
            cargarCliente(cabecera.getClienteId());
        }
        if (cabecera.getVendedor() != null) {
            cmbVendedor.setSelectedItem(cabecera.getVendedor());
        }
        txtSubTotal.setText(formatearMonto(cabecera.getSubtotal(), monedaExacta));
        txtTotalDescuentos.setText(formatearMonto(cabecera.getTotalDescuentos(), monedaExacta));
        txtTotalIva.setText(formatearMonto(cabecera.getTotalIva(), monedaExacta));
        txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(cabecera.getTotal())));
        if (cabecera.getObservacion() != null) {
            txtObservaciones.setText(cabecera.getObservacion());
        }
    }

    private String formatearMonto(String valor, boolean monedaExacta) {
        BigDecimal monto = big.getBigDecimal(valor);
        return monedaExacta ? big.setMonedaExacta(monto) : big.setMoneda(monto);
    }

    private void cargarDatosVehiculo(ndOServicio nodoOrden) {
        cargarArticulos(nodoOrden.getTipo());
        txtPlaca.setText(nodoOrden.getPlaca());
        txtTipoVehiculo.setText(nodoOrden.getTipo());
        txtModelo.setText(nodoOrden.getModelo());
        txtNumChasis.setText(nodoOrden.getNumeroChasis());
        txtMarca.setText(nodoOrden.getMarca());
        txtKm.setText(nodoOrden.getKm());
        txtMotor.setText(nodoOrden.getNumeroMotor());
        txtColor.setText(nodoOrden.getColor());
        txtProblema.setText(nodoOrden.getProblema());
        tblArticulos.setEnabled(false);
        btnModificar.setEnabled(true);
        btnReImprimir.setEnabled(true);
        txtPlaca.setEnabled(false);
        txtModelo.setEnabled(false);
        txtTipoVehiculo.setEnabled(false);
        txtNumChasis.setEnabled(false);
        txtMarca.setEnabled(false);
        txtKm.setEnabled(false);
        txtMotor.setEnabled(false);
        txtColor.setEnabled(false);
        if (!TipoDocumento.FACTURACION.getValor().equals(this.tipoProceso)) {
            btnGuardar.setVisible(false);
            btnImprimir.setVisible(false);
        }
    }

    private void aplicarEstadoVehiculo(Object[][] estadoVehiculo) {
        for (int i = 0; i < estadoVehiculo.length; i++) {
            for (int j = 0; j < tblArticulos.getRowCount(); j++) {
                if (estadoVehiculo[i][0].equals(tblArticulos.getValueAt(j, 0))) {
                    tblArticulos.setValueAt(true, j, 2);
                    tblArticulos.setValueAt(estadoVehiculo[i][2], j, 3);
                    tblArticulos.setValueAt(estadoVehiculo[i][3], j, 4);
                    tblArticulos.setValueAt(estadoVehiculo[i][4], j, 5);
                }
            }
        }
    }

    private void activarCampos(boolean x) {
        txtNit.setEditable(x);
        txtCodigoProducto.setEditable(x);
        btnBuscTerceros.setEnabled(x);
        btnBusProd.setEnabled(x);
    }

    public void cargarOServicio(String num, String tipo) {
        String orden = tipo + "" + num;
        ndOServicio1 nodo = instancias.getSql().getDatosOServicio1(orden);

        Object[][] estadoVehiculo = instancias.getSql().getEstadoVehiculo(orden);

        if (nodo.getIdFactura() != null) {

            DocumentoMovimiento docOrden = daoFactura.cargarOrdenServicio(orden);
            if (!docOrden.isEmpty()) {
                limpiar(false);
                cargarLineasDocumento(docOrden.getLineas());
                mostrarResumenDocumento(docOrden.getCabecera(), false);
            }
            txtNombre.requestFocus();
            if (tipo.equals("OSERV-")) {
                ndOServicio nodoOrden = instancias.getSql().getDatosOServicio(orden);
                txtPlaca.setText(nodoOrden.getPlaca());
                txtTipoVehiculo.setText(nodoOrden.getTipo());
                txtModelo.setText(nodoOrden.getModelo());
                txtNumChasis.setText(nodoOrden.getNumeroChasis());
                txtMarca.setText(nodoOrden.getMarca());
                txtKm.setText(nodoOrden.getKm());
                txtMotor.setText(nodoOrden.getNumeroMotor());
                txtColor.setText(nodoOrden.getColor());
                txtProblema.setText(nodoOrden.getProblema());
                cargarArticulos(nodoOrden.getTipo());
            }

            for (int i = 0; i < estadoVehiculo.length; i++) {
                for (int j = 0; j < tblArticulos.getRowCount(); j++) {
                    if (estadoVehiculo[i][0].equals(tblArticulos.getValueAt(j, 0))) {
                        tblArticulos.setValueAt(true, j, 2);
                        tblArticulos.setValueAt(estadoVehiculo[i][2], j, 3);
                        tblArticulos.setValueAt(estadoVehiculo[i][3], j, 4);
                        tblArticulos.setValueAt(estadoVehiculo[i][4], j, 5);
                    }
                }
            }

            tblArticulos.setEnabled(false);
            txtObservaciones.setText(nodo.getObservacion());

            if (tipo.equals("OSERV-")) {
                btnModificar.setEnabled(true);
                btnReImprimir.setEnabled(true);

                txtPlaca.setEnabled(false);
                txtModelo.setEnabled(false);
                txtTipoVehiculo.setEnabled(false);
                txtNumChasis.setEnabled(false);
                txtMarca.setEnabled(false);
                txtKm.setEnabled(false);
                txtMotor.setEnabled(false);
                txtColor.setEnabled(false);

                if (pnlOrdenServicio.isVisible()) {
                    btnGuardar.setEnabled(false);
                    btnImprimir.setEnabled(false);
                } else {
                    btnGuardar.setEnabled(true);
                    btnImprimir.setEnabled(true);
                }
            }

//            for (int i = 0; i < tblProductos.getRowCount(); i++) {
//                calcularTabla(i);
//            }
        } else {
            metodos.msgError(null, "orden invalida");
            return;
        }
    }

    public void calcularCuotasCredito() {

        DefaultTableModel modeloCredito;
        BigDecimal valorCredito = big.getMoneda(txtValorVenta.getText()).subtract(big.getMoneda(txtCuotaInicial.getText()));
        if (valorCredito.compareTo(big.getBigDecimal("0")) == -1) {
            valorCredito = big.getBigDecimal("0");
        }
        txtValorCredito.setText(big.setMoneda(valorCredito));

//        txtTotalCredito.setText(big.setMonedaExacta(valorCredito));
//        txtTotal.setText("Total: " + big.setMonedaExacta(big.getMoneda(txtTotalCredito.getText())));
        //Para hacer la validación de que no se pase la cartera permitda sino sacar mensaje.
        BigDecimal tot = BigDecimal.ZERO;
        try {
            tot = big.getMoneda(txtTotal.getText().replace("Total: ", ""));
        } catch (Exception e) {
        }

//        this.setPlazo(txtDiasPlazo.getText(), tot);
        if (txtNombre.getText().equals("") || txtNit.getText().equals("1010")) {
//            metodos.msgError(null, "Debe ingresar un cliente");
            txtNombre.setText("");
            txtNit.setText("");
            return;
        }
        int j = tblCuotas.getRowCount();
        int tipoPlazo = 0;

        if (cmbTipoPlazo.getSelectedItem().equals("Días")) {
            tipoPlazo = 1;
        } else if (cmbTipoPlazo.getSelectedItem().equals("Semanal")) {
            tipoPlazo = 7;
        } else if (cmbTipoPlazo.getSelectedItem().equals("Quincenal")) {
            tipoPlazo = 14;
        } else if (cmbTipoPlazo.getSelectedItem().equals("Mensual")) {
            tipoPlazo = 1;
        } else {

            modeloCredito = (DefaultTableModel) tblCuotas.getModel();

            for (int i = 0; i < j; i++) {
                modeloCredito.removeRow(0);
            }
            txtDiasPlazo.setText("0");
            return;
        }

        modeloCredito = (DefaultTableModel) tblCuotas.getModel();

        for (int i = 0; i < j; i++) {
            modeloCredito.removeRow(0);
        }

        if (txtCuotaInicial.getText().equals("")) {
            txtCuotaInicial.setText(this.simbolo + " 0");
        }

        if (txtCuotas.getText().equals("0") || txtCuotas.getText().equals("")) {
            return;
        }

        if (txtInteres.getText().equals("")) {
            txtInteres.setText("0");
        }

        BigDecimal valor = big.getMoneda("0"), cuotas, intereses, total, cuotaCapital, cuotaInteres,
                cuotaTotal, porcentaje, saldo, tem, factor;
        String fecha = metodos.fecha(metodos.desdeDate(dtFechaDesenvolso.getCurrent()));

        valor = big.getMoneda(txtValorCredito.getText());

        porcentaje = big.getMoneda(txtInteres.getText());
        cuotas = big.getMoneda(txtCuotas.getText());
        if (porcentaje.compareTo(BigDecimal.ZERO) == 0) {
//            tem = big.getBigDecimal("0");
//            factor = big.getBigDecimal("0");
            total = valor;
            intereses = big.getBigDecimal("0");
            cuotaTotal = valor.divide(cuotas, 2, RoundingMode.HALF_UP);
        } else {
            tem = (BigDecimal.ONE.add((porcentaje.divide(new BigDecimal(100), 10, RoundingMode.HALF_UP)))).subtract(new BigDecimal(1));
            factor = (tem.multiply((BigDecimal.ONE.add(tem)).pow(cuotas.intValueExact()))).divide(((BigDecimal.ONE.add(tem)).pow(cuotas.intValueExact())).subtract(BigDecimal.ONE), 10, RoundingMode.HALF_UP);
            total = valor.multiply(factor).multiply(cuotas);
            intereses = total.subtract(valor);
            cuotaTotal = valor.multiply(factor);
        }
        saldo = valor;
        total = BigDecimal.ZERO;
        j = cuotas.intValueExact();

        txtTotalIntereses.setText(big.setMonedaExacta(intereses));
        String ultimaFecha = "";
        for (int i = 1; i <= j; i++) {
            cuotaInteres = saldo.multiply(porcentaje.divide(new BigDecimal(100)));
            cuotaCapital = cuotaTotal.subtract(cuotaInteres);
            if (cmbTipoPlazo.getSelectedItem().equals("Mensual")) {
                if (i == j) {
                    modeloCredito.addRow(new Object[]{i, metodos.sumarMeses(fecha, tipoPlazo), big.setMoneda(cuotaCapital), big.setMoneda(cuotaInteres), big.setMoneda(cuotaTotal),
                        this.simbolo + " 0", this.simbolo + " 0"});
                } else {
                    if (i == 1) {
                        modeloCredito.addRow(new Object[]{i, fecha, big.setMoneda(cuotaCapital), big.setMoneda(cuotaInteres), big.setMoneda(cuotaTotal), big.setMoneda(saldo.subtract(cuotaTotal)), big.setMoneda(total.subtract(cuotaTotal))});
                    } else {
                        modeloCredito.addRow(new Object[]{i, metodos.sumarMeses(fecha, tipoPlazo), big.setMoneda(cuotaCapital), big.setMoneda(cuotaInteres), big.setMoneda(cuotaTotal), big.setMoneda(saldo.subtract(cuotaTotal)), big.setMoneda(big.getBigDecimal(big.getMoneda(modeloCredito.getValueAt(i - 2, 6).toString())).subtract(cuotaTotal))});
                    }
                }
                total = total.add(big.getMoneda(big.setMoneda(cuotaTotal)));
                if (i > 1) {
                    fecha = metodos.sumarMeses(fecha, tipoPlazo);
                }
            } else {
                if (i == j) {
                    modeloCredito.addRow(new Object[]{i, metodos.sumarFecha(fecha, tipoPlazo), big.setMoneda(cuotaCapital), big.setMoneda(cuotaInteres), big.setMoneda(cuotaTotal),
                        this.simbolo + " 0", this.simbolo + " 0"});
                } else {
                    if (i == 1) {
                        modeloCredito.addRow(new Object[]{i, fecha, big.setMoneda(cuotaCapital), big.setMoneda(cuotaInteres), big.setMoneda(cuotaTotal), big.setMoneda(saldo.subtract(cuotaTotal)), big.setMoneda(total.subtract(cuotaTotal))});
                    } else {
                        modeloCredito.addRow(new Object[]{i, metodos.sumarFecha(fecha, tipoPlazo), big.setMoneda(cuotaCapital), big.setMoneda(cuotaInteres), big.setMoneda(cuotaTotal), big.setMoneda(saldo.subtract(cuotaTotal)), big.setMoneda(big.getBigDecimal(big.getMoneda(modeloCredito.getValueAt(i - 2, 6).toString())).subtract(cuotaTotal))});
                    }
                }
                total = total.add(big.getMoneda(big.setMoneda(cuotaTotal)));
                if (i > 1) {
                    fecha = metodos.sumarFecha(fecha, tipoPlazo);
                }
            }

            ultimaFecha = fecha;
            saldo = saldo.subtract(cuotaCapital);
        }

        long dias = metodos.restarFecha(metodosGenerales.fecha(), ultimaFecha);
        txtDiasPlazo.setText(dias + "");
        txtVencimiento.setText(metodos.sumarFecha(txtFechaFactura.getText(), Integer.parseInt(txtDiasPlazo.getText())));

        txtTotalCredito.setText(big.setMonedaExacta(total));
        txtTotal.setText("Total: " + big.setMoneda(big.getMoneda(txtTotalCredito.getText())));
        //Para hacer la validación de que no se pase la cartera permitda sino sacar mensaje.
//        this.setPlazo(txtDiasPlazo.getText(), big.getMoneda(txtTotal.getText().replace("Total: ", "")));
    }

    public void validarClienteParaCredito() {
        if (txtNombre.getText().equals("") || txtNit.getText().equals("1010")) {
            metodos.msgError(null, "Debe ingresar un cliente");
            return;
        }
    }

    public Object[] getOcultarIvaPanama() {
        return new Object[]{tblProductos, chkReteIva, txtRiva, cmbRtf, txtRtf, txtTotalImpoconsumo,
            lbImpoconsumo};
    }

    public Object[] getOcultarIva() {
        return new Object[]{tblProductos, chkReteIva, txtRiva, txtIva, txtTotalIva, cmbRtf, txtRtf, txtTotalImpoconsumo,
            lbImpoconsumo};
    }

    public void eliminarFila() {
        int fila = tblProductos.getSelectedRow();
        if (tblProductos.getValueAt(fila, 16).equals("REALIZADO")) {
            metodos.msgAdvertencia(null, "No puede Borrar este producto");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.removeRow(fila);

        tblProductos.removeEditor();

        btnPasarACongelada.setVisible(esValidoParaPasarACongeladas());
        cargarTotales();
    }

    private boolean esValidoParaPasarACongeladas() {
        return !instancias.getConfiguraciones().isRestaurante() && modeloPro.getRowCount() > 0 && this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor());
    }

    private String[] obtenerConsecutivoDocumento() {
        String factura = "", factura2;

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                || (tipoProceso.equals(TipoDocumento.MESA.getValor()) && lbTitulo.getText().equals("DOMICILIO"))) {

            int fila = 0;
            // OBTENEMOS LA FILA DEL COMPROBANTE SELECCIONADO
            for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
                if (Boolean.TRUE.equals(tblComprobantes.getValueAt(i, 2))) {
                    fila = i;
                    break;
                }
            }

            // OBTENEMOS EL PREFIJO DEL COMPROBANTE
            String prefijo = tblComprobantes.getValueAt(fila, 8) == null ? "" : tblComprobantes.getValueAt(fila, 8).toString();

            // VALIDAMOS QUE EL CONSECUTIVO DE FACTURACIÓN NO EXISTA.
            int idResolucion = Integer.parseInt(tblComprobantes.getValueAt(fila, 0).toString());
            factura2 = funcionalidadVentas.validarYObtenerFactura(idResolucion);
            factura = factura2.replace(prefijo, "");
        } else {
            switch (this.tipoProceso) {
                case "orden":
                    factura = "OSERV-" + daoFactura.getNextConsecutivo("OSERV");
                    break;
                case "cotizacion":
                    factura = "COTI-" + daoFactura.getNextConsecutivo("COTI");
                    break;
                case "pedido":
                    factura = "PEDIDO-" + daoFactura.getNextConsecutivo("PEDIDO");
                    break;
                case "separe":
                    factura = "SEPARE-" + daoFactura.getNextConsecutivo("SEPARE");
                    break;
                case "mesa":
                    factura = "CONGELADA-" + daoFactura.getNextConsecutivo("CONGELADA");
                    break;
                case "cuentaCobro":
                    factura = "CCOBRO-" + daoFactura.getNextConsecutivo("CCOBRO");
                    break;
                default:
                    break;
            }

            factura2 = factura;
        }

        return new String[]{factura, factura2};
    }

    private String facturar(VistaMetodoPagos devuelta, boolean imprimir, String desde) {

        registrarMetodosDePago(devuelta);

        if (validarCancelacionFactura()) {
            return "";
        }

        String prefijoGeneral = TipoDocumento.obtenerPrefijoGeneralPorValor(this.tipoProceso);
        String[] consecutivos = obtenerConsecutivoDocumento();
        String factura = consecutivos[0];
        String factura2 = consecutivos[1];

        String tipoComprobante = obtenerTipoComprobante();
        String vendedor = cmbVendedor.getSelectedItem().toString().equals("Seleccione un vendedor") ? "" : cmbVendedor.getSelectedItem().toString();
        String congelada = funcionalidadVentas.obtenerNumeroCongelada(instancias, this.tipoProceso);
        int turno = obtenerTurno();
        String fechaFactura = this.fechaFacturaAutomatica.isEmpty() ? metodos.fechaConsulta(metodosGenerales.fechaHora()) : this.fechaFacturaAutomatica;

        BigDecimal copago = BigDecimal.ZERO;
        try {
            copago = big.getMoneda(txtCopago.getText());
        } catch (Exception e) {
        }

        String porcentajeReteFuente = "";
        if (cmbRtf.getSelectedIndex() == 0) {
            porcentajeReteFuente = "0";
        } else {
            porcentajeReteFuente = cmbRtf.getSelectedItem().toString();
        }

        // Actualizar consecutivo si el documento ya existe (escenario de recarga)
        if (this.tipoProceso.equals(TipoDocumento.PLAN_SEPARE.getValor())) {
            ndPlanSepare nodoSepare = instancias.getSql().getDatosPlanSepare(factura);
            if (nodoSepare.getIdFactura() != null) {
                if (!instancias.getSql().aumentarConsecutivo("SEPARE", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("SEPARE")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al aumentar el consecutivo del plan separe");
                }
                factura = "SEPARE-" + daoFactura.getNextConsecutivo("SEPARE");
            }
        } else if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && !lbTitulo.getText().equals("DOMICILIO")) {
            ndCongelada nodoMesa = instancias.getSql().getDatosCongelada(factura);
            if (nodoMesa.getIdFactura() != null) {
                if (!instancias.getSql().aumentarConsecutivo("CONGELADA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONGELADA")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al aumentar el consecutivo de la congelada");
                }
                factura = "CONGELADA-" + daoFactura.getNextConsecutivo("CONGELADA");
            }
        }

        MovimientoDocumento movimiento = new MovimientoDocumento(tipoComprobante, factura, factura2, prefijoGeneral,
                vendedor, congelada, turno, fechaFactura, copago, porcentajeReteFuente);

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                || (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && lbTitulo.getText().equals("DOMICILIO"))) {

            if (agregarMovimientoFactura(movimiento)) {
                actualizarInventarioDocumento(TipoDocumento.FACTURACION);

                if (DatosMaestra.isTurnoActivo() && instancias.getConfiguraciones().isRestaurante()) {
                    aumentarTurno();
                }
            }
        } else if (this.tipoProceso.equals(TipoDocumento.COTIZACION.getValor())) {
            agregarMovimientoCotizacion(movimiento);
        } else if (this.tipoProceso.equals(TipoDocumento.ORDER_SERVICIO.getValor())) {
            if (agregarMovimientoOrdenServicio(movimiento)) {
                actualizarInventarioDocumento(TipoDocumento.ORDER_SERVICIO);
            }
        } else if (this.tipoProceso.equals(TipoDocumento.PEDIDO.getValor())) {
            if (agregarMovimientoPedido(movimiento)) {
                actualizarInventarioDocumento(TipoDocumento.PEDIDO);
                if (DatosMaestra.isTurnoActivo() && instancias.getConfiguraciones().isRestaurante()) {
                    aumentarTurno();
                }
            }
        } else if (this.tipoProceso.equals(TipoDocumento.PLAN_SEPARE.getValor())) {
            if (agregarMovimientoPlanSepare(movimiento)) {
                actualizarInventarioDocumento(TipoDocumento.PLAN_SEPARE);
            }
        } else if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && !lbTitulo.getText().equals("DOMICILIO")) {
            if (agregarMovimientoMesa(movimiento)) {
                actualizarInventarioDocumento(TipoDocumento.MESA);

                if (DatosMaestra.isTurnoActivo() && instancias.getConfiguraciones().isRestaurante()) {
                    aumentarTurno();
                }
            }
        } else if (this.tipoProceso.equals(TipoDocumento.CUENTA_COBRO.getValor())) {
            agregarMovimientoCuentaCobro(movimiento);
        }

        int fila = 0;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                fila = i;
            }
        }

        aumentarConsecutivo(fila);

        if (!ndGuarderia.isEmpty()) {
            actualizarEstadoGuarderia(factura2);
        }

        if (!ndHospitalizacion.isEmpty()) {
            actualizarEstadoHospitalizacion(factura2);
        }

        if (!ndPeluqueria.isEmpty()) {
            actualizarEstadoPeluqueria(factura);
        }

        if (instancias.getConfiguraciones().isAgenda()) {
            actualizarEstadoAgenda();
        }

        actualizarConsecutivo(fila);
        mostrarMensajeFinalizacionDocumento(desde);

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())) {
            mostrarDevueltaFactura();
        }

        String impresoraComanda = "";
        try {
            impresoraComanda = DatosMaestra.getImpresoraComanda();
        } catch (Exception e) {
        }

        //IMPRIMIR LA COMANDA
        if (instancias.getConfiguraciones().isRestaurante() && !this.saltarPasosFactura) {
            if (tipoProceso.equals("facturacion")) {
                if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {
                    imprimirComanda("where factura = '" + factura + "'", factura, "", impresoraComanda);
                }
            }

            if (tipoProceso.equals("mesa")) {
                if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {
                    boolean esDomicilio = lbTitulo.getText().equals("DOMICILIO");
                    String condicion = esDomicilio ? "where factura = '" + factura + "'" : "where congelada = '" + factura + "'";
                    String titulo = esDomicilio ? "" : lbTitulo.getText();
                    imprimirComanda(condicion, factura, titulo, impresoraComanda);
                }
            }

            if (tipoProceso.equals("pedido")) {
                if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {
                    imprimirComanda("where pedido = '" + factura + "'", factura, lbTitulo.getText(), impresoraComanda);
                }
            }
        }
        // FIN DE LA IMPRESION DE LA COMANDA

        if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && !lbTitulo.getText().equals("DOMICILIO")) {
            instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
        }

        generarImpresionDocumento(desde, factura, factura2, imprimir);

        if (esFacturaCredito) {
            instancias.getReporte().verPrestamo(credito1, instancias.getInformacionEmpresa());
        }

        if (this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getMesas().cargarRegistrosMesas();
                instancias.getMesas().cargarRegistros();
                instancias.getMesas().setSelected(true);
            } else {
                if (!pasandoACongelada) {
                    instancias.getMesas().setSelected(true);
                }
            }

            if (!pasandoACongelada && !instancias.getMenu().getSeVeElMenu()) {
                instancias.getMenu().expandirMenu();
            }
        } else {
            limpiar(true);
        }

        lbNit.requestFocus();
        tblProductos.removeEditor();

        actualizarTablaResoluciones();

        return factura2;
    }

    private void guardarCuentaPorCobrar(String factura, String factura2, String tipoCxc) {
        if (txtDiasPlazo.getText().equals("")) {
            txtDiasPlazo.setText("0");
        }

        Object[] vectCxc = {factura, tipoCxc, "PEND", "", big.getMoneda(txtTotal.getText().replace("Total: ", "")),
            txtDiasPlazo.getText(), metodos.fechaConsulta(txtVencimiento.getText()),
            instancias.getUsuario(), instancias.getTerminal(), esFacturaCredito, factura2};

        ndCxc nodoCxc = metodos.llenarCxc(vectCxc);
        if (!instancias.getSql().agregarCxc(nodoCxc)) {
            metodos.msgError(null, "Hubo un problema al guardar la factura en cartera");
        }
    }

    private void imprimirComanda(String condicion, String factura, String titulo, String impresoraComanda) {
        instancias.getReporte().ver_Comanda(condicion, factura, txtObservaciones.getText(),
                factura, titulo, DatosMaestra.isPrevisualizarComanda(), impresoraComanda, cmbVendedor.getSelectedItem().toString());
        String copias = "";
        try {
            copias = DatosMaestra.getCopiasComanda();
        } catch (Exception e) {
        }
        try {
            if (copias != null || !copias.equals("")) {
                for (int i = 0; i < Integer.parseInt(copias); i++) {
                    instancias.getReporte().ver_Comanda(condicion, factura, txtObservaciones.getText(),
                            factura, titulo, DatosMaestra.isPrevisualizarComanda(), impresoraComanda, cmbVendedor.getSelectedItem().toString());
                }
            }
        } catch (Exception e) {
        }
    }

    public void actualizarTablaResoluciones() {
        while (tblComprobantes.getRowCount() > 0) {
            modeloComprobantes.removeRow(0);
        }

        List<ModeloResolucion> resoluciones = daoResoluciones.obtenerResoluciones(TipoDocumento.FACTURACION.getValor());
        for (ModeloResolucion resolucion : resoluciones) {
            modeloComprobantes.addRow(new Object[]{resolucion.getIdResolucion(), resolucion.getDescripcionResolucion(), false, resolucion.getNumeroResolucion(), resolucion.getFechaInicio(),
                resolucion.getNumeracionDel(), resolucion.getNumeracionHasta(), resolucion.getTipoResolucion(), resolucion.getPrefijo(), resolucion.getConsecutivo(), resolucion.getDisenho()});
        }

        tblComprobantes.setValueAt(true, 0, 2);
        actualizarResolucion(0);
    }

    public String getTipoProceso() {
        return this.tipoProceso;
    }

    public void setSaltarPasosFactura(boolean saltar) {
        this.saltarPasosFactura = saltar;
    }

    public boolean cargarDocumentoParaConversionAFactura(String tipoDocumento, String idDocumento) {
        if (tblComprobantes.getRowCount() == 0) {
            actualizarTablaResoluciones();
        }

        DocumentoMovimiento doc;
        boolean esCotizacion = TipoDocumento.COTIZACION.getValor().equals(tipoDocumento);

        switch (tipoDocumento) {
            case "pedido":
                doc = daoFactura.cargarPedido(idDocumento);
                break;
            case "cotizacion":
                doc = daoFactura.cargarCotizacion(idDocumento);
                break;
            case "orden":
                doc = daoFactura.cargarOrdenServicio(idDocumento);
                break;
            default:
                return false;
        }

        if (doc == null || doc.isEmpty()) {
            return false;
        }

        limpiar(false);

        if (esCotizacion) {
            cargarLineasCotizacion(doc.getLineas());
        } else {
            cargarLineasDocumento(doc.getLineas());
            this.lineasOriginales = doc.getLineas();
            recalcularInventarioTabla();
        }

        mostrarResumenDocumento(doc.getCabecera(), false);

        String numDocumento = idDocumento.contains("-")
                ? idDocumento.substring(idDocumento.lastIndexOf("-") + 1)
                : idDocumento;
        lbNoFactura.setText(numDocumento);

        return true;
    }

    public boolean agregarDocumentoParaUnificacion(String tipoDocumento, String idDocumento) {
        DocumentoMovimiento doc;
        boolean esCotizacion = TipoDocumento.COTIZACION.getValor().equals(tipoDocumento);

        switch (tipoDocumento) {
            case "pedido":
                doc = daoFactura.cargarPedido(idDocumento);
                break;
            case "cotizacion":
                doc = daoFactura.cargarCotizacion(idDocumento);
                break;
            case "orden":
                doc = daoFactura.cargarOrdenServicio(idDocumento);
                break;
            default:
                return false;
        }

        if (doc == null || doc.isEmpty()) {
            return false;
        }

        List<LineaProducto> lineas = doc.getLineas();
        int offset = tblProductos.getRowCount();

        for (int i = 0; i < lineas.size(); i++) {
            LineaProducto linea = lineas.get(i);
            String imeiSerial = linea.getImei() != null ? linea.getImei() : "";
            String idProductoDetallado = linea.getIdProd() != null ? linea.getIdProd() : "";

            cargarProducto(linea.getCodigo(), Utilidades.convertirBigDecimal(linea.getCantidad()),
                    linea.getPlu(), imeiSerial, "", idProductoDetallado, false, "", "", "", "", "");
            int rowIndex = offset + i;
            String permisoDescuento = linea.getRango();
            if (permisoDescuento != null && !permisoDescuento.isEmpty()) {
                tblProductos.setValueAt(permisoDescuento, rowIndex, 31);
            }
            tblProductos.setValueAt(linea.getDescripcion(), rowIndex, 1);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(linea.getPrecio())), rowIndex, 2);
            String porcDesc = linea.getPorcDescuento();
            if (porcDesc != null) {
                tblProductos.setValueAt(porcDesc.replace(",", "."), rowIndex, 5);
            }
            String desc = linea.getDescuento();
            if (desc != null) {
                tblProductos.setValueAt(desc.replace(".", ","), rowIndex, 6);
            }
            tblProductos.setValueAt(linea.getPreparacion(), rowIndex, 21);
            calcularTabla(rowIndex, false);
        }

        if (!esCotizacion) {
            this.lineasOriginales.addAll(lineas);
        }

        return true;
    }

    public void seleccionarComprobanteParaConversion(int filaIndex) {
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            tblComprobantes.setValueAt(false, i, 2);
        }
        if (filaIndex >= 0 && filaIndex < tblComprobantes.getRowCount()) {
            tblComprobantes.setValueAt(true, filaIndex, 2);
            actualizarResolucion(filaIndex);
        }
    }

    public void ejecutarConversionAFactura(boolean imprimir, String idDocumentoOrigen) {
        convertirDocumentoAFactura(imprimir, idDocumentoOrigen);
    }

    public void marcarDocumentoOrigenComoConvertido(String tipoProcesoOriginal, String idDocumento) {
        if (conversorDocumentoAFactura != null) {
            conversorDocumentoAFactura.actualizarDocumentoOrigen(tipoProcesoOriginal, idDocumento, instancias.getTitulo());
        }
    }

    private void convertirDocumentoAFactura(boolean imprimir, String idDocumentoOrigen) {
        if (TipoDocumento.MESA.getValor().equals(this.tipoProceso) && lbTitulo.getText().equals("DOMICILIO")) {
            validacionInicialFactura(imprimir);
            return;
        }

        String tipoProcesoOrigen = this.tipoProceso;
        String prefijoGeneral = TipoDocumento.obtenerPrefijoGeneralPorValor(tipoProcesoOrigen);
        String tituloOrigen = instancias.getTitulo() != null ? instancias.getTitulo() : "";

        if (TipoDocumento.MESA.getValor().equals(this.tipoProceso)) {
            idDocumentoOrigen = prefijoGeneral + "-" + lbNoFactura.getText();
        }

        try {
            revertirInventarioOriginal();
        } catch (SQLException ex) {
            Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
            ControladorAlertas.alertFail("Error al revertir el inventario del documento origen");
            return;
        }

        if (conversorDocumentoAFactura != null) {
            conversorDocumentoAFactura.actualizarDocumentoOrigen(tipoProcesoOrigen, idDocumentoOrigen, tituloOrigen);
        }

        this.tipoProceso = TipoDocumento.FACTURACION.getValor();
        validacionInicialFactura(imprimir);

        if (TipoDocumento.MESA.getValor().equals(tipoProcesoOrigen) && tblProductos.getRowCount() == 0) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getMesas().cargarRegistrosMesas();
                instancias.getMesas().cargarRegistros();
            }
            if (!instancias.getMenu().getSeVeElMenu()) {
                instancias.getMenu().expandirMenu();
            }
            instancias.getMesas().setSelected(true);
        }
    }

    public void guardarCredito(String factura, String factura2) {
//        String credito = "CREDITO-" + sql.getNumConsecutivo("CREDITO")[0];
        String credito = "CREDITO-" + factura.replace("FACT-", "");
        Object[] vector = {credito, factura, instancias.getUsuario(),
            metodos.fechaConsulta(metodosGenerales.fecha()), ID_CLIENTE_CARGADO, "", "",
            metodos.fechaConsulta(metodosGenerales.fecha()), metodos.desdeDate(dtFechaDesenvolso.getCurrent()),
            txtObservaciones.getText(), big.getMoneda(txtValorVenta.getText()),
            big.getMoneda(txtValorCredito.getText()), big.getMoneda(txtCuotas.getText()),
            big.getMoneda(txtInteres.getText()), big.getMoneda(txtTotalIntereses.getText()),
            big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtCuotaInicial.getText()), factura2};

        ndPrestamo nodo = metodos.llenarPrestamo(vector);
        if (!instancias.getSql().agregarPrestamo(nodo)) {
            metodos.msgError(null, "Hubo un "
                    + "problema al guardar el Credito");
            return;
        }

        for (int i = 0; i < tblCuotas.getRowCount(); i++) {
            Object[] vectorDos = {credito + "-" + modeloCredito.getValueAt(i, 0).toString(), credito, modeloCredito.getValueAt(i, 0).toString(),
                metodos.fechaConsulta(modeloCredito.getValueAt(i, 1).toString()), instancias.getUsuario(), metodos.fechaConsulta(metodosGenerales.fecha()),
                "", "", big.getMoneda(modeloCredito.getValueAt(i, 2).toString()), big.getMoneda(modeloCredito.getValueAt(i, 3).toString()),
                big.getMoneda(modeloCredito.getValueAt(i, 4).toString()), big.getMoneda("0"), big.getMoneda("0"), big.getMoneda("0"),
                big.getMoneda("0"), ""};

            ndCuota nodoDos = metodos.llenarCuota(vectorDos);
            if (!instancias.getSql().agregarCuota(nodoDos)) {
                metodos.msgError(null, "Error al guardar la Cuota: " + i);
                return;
            }
        }

        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }

        credito1 = credito;

//        if (!sql.aumentarConsecutivo("CREDITO", Integer.parseInt((String) sql.getNumConsecutivo("CREDITO")[0]) + 1)) {
        //            metodos.msgError(null, "Hubo un problema al guardar en el consecutivo del credito");
        //        }
    }

    public String getTipo() {

        if (instancias.getConfiguraciones().isMedico()) {
            if (rdCarta.isSelected()) {
                return "facturaMedicaCompleta";
            } else {
                return "facturaMedica";
            }
        }

        if (rdCarta.isSelected()) {
            switch (tipoProceso) {
                case "facturacion":
                    return "facturaCompleta" + instancias.getRegimen();
                case "cotizacion":
                    return "cotiza";
                case "orden":
                    return "";
                case "pedido":
                    return "pedidoCompleta";
                case "separe":
                    return "separeCompleta";
            }
        } else if (rdPos.isSelected()) {
            switch (tipoProceso) {
                case "facturacion":
                    return "pos" + instancias.getRegimen();
                case "cotizacion":
                    return "cotizaPos";
                case "orden":
                    return "";
                case "pedido":
                    return "pedidoPos";
                case "separe":
                    return "separePos";
            }

        }

        /*if (instancias.getFactura().getCantidadProductos() > 6) {
         return "facturaCompleta" + instancias.getRegimen();
         }*/
        switch (tipoProceso) {
            case "facturacion":
                return "factura" + instancias.getRegimen();
            case "cotizacion":
                return "cotiza";
            case "orden":
                return "";
            case "pedido":
                return "pedido";
            case "separe":
                return "separe";
        }
        return "";

    }

    public void ventanaProductos(String codigo) {
        buscProductos buscar = new buscProductos(null, true, false, "facturacion", "productos1");
        buscar.setOpc("factura");
        buscar.setFactura(this);
        buscar.setLocationRelativeTo(null);
        instancias.setBusProductos(buscar);
        instancias.setCampoActual(txtCodigoProducto);
        txtCodigoProducto.requestFocus();
        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaTipoVehiculos(String nit) {
        buscTipoVehiculo buscar = new buscTipoVehiculo(instancias.getMenu(), true);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscTipoVehiculo(buscar);
        instancias.setCampoActual(txtTipoVehiculo);
        txtTipoVehiculo.requestFocus();
        buscar.setInstancia(instancias);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaProblemas() {
        buscProblemas buscar = new buscProblemas(null, true, false);
        buscar.setOpc("orden");
        buscar.setFactura(this);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarProblemas(buscar);
//        buscar.noEncontrado(codigo.replace("'", "//"));
        buscar.show();
    }

    public void ventanaProblemas1() {
        buscProblemas buscar = new buscProblemas(null, true, false);
        buscar.setOpc("orden1");
        buscar.setFactura(this);
        buscar.setLocationRelativeTo(null);
        instancias.setBuscarProblemas(buscar);
        buscar.show();
    }

    public void cargarProblema(String problemas, String tipoOrden) {
        if (tipoOrden.equals("1")) {
            tblArticulos.setValueAt(problemas, tblArticulos.getSelectedRow(), 3);
        } else {
            tblArticulos.setValueAt(problemas, tblArticulos.getSelectedRow(), 4);
        }
    }

    public void cargarPreparacion(String fila, String cadena, String prod) {
        if (fila.equals("")) {
            cargarProducto(prod, BigDecimal.ONE, 1, "", "", cadena, false, "", "", "", "", "");
            tblProductos.setValueAt(cadena, tblProductos.getRowCount() - 1, 21);
        } else {
            tblProductos.setValueAt(cadena, Integer.parseInt(fila), 21);
        }
    }

    public void cargarCliente(String nit) {
        ModeloContacto nodo = instancias.getSql().getDatosTercero(nit);

        if (nodo.getId() != null) {

            if (nodo.isActivo()) {
                metodos.msgError(null, "Este cliente esta inactivado");
                lbNit.requestFocus();
                return;
            }

            if (tipoProceso.equals("pedido") && nit.equals("1010")) {
                txtNombre.setEnabled(true);
                txtNombre.setEditable(true);
            } else {
                txtNombre.setEnabled(false);
                txtNombre.setEditable(false);
            }

            ID_CLIENTE_CARGADO = nodo.getIdSistema();
            txtNit.setText(nodo.getId());
            txtNombre.setText(nodo.getNombre());
            txtObservaciones.setText(nodo.getPlacas());

            try {
                String nombreVendedor = "";
                if (!nodo.getVendedor().equals("")) {
                    nombreVendedor = instancias.getSql().getNombreEmpleado(nodo.getVendedor());
                    cmbVendedor.setSelectedItem(nombreVendedor);
                }
            } catch (Exception e) {
            }

            try {
                cmbListaPrecio.setSelectedItem(nodo.getLista());
            } catch (Exception e) {
                cmbListaPrecio.setSelectedIndex(0);
            }

            if (focusDiasPlazo) {
                txtDiasPlazo.requestFocus();
                focusDiasPlazo = false;
            } else {
                txtCodigoProducto.requestFocus();
            }

            if (tipoProceso.equals("separe")) {
                txtDiasPlazo.requestFocus();
            }

            if (DatosMaestra.isCargarDiasPlazoAutomaticamente()) {
                txtDiasPlazo.setText(nodo.getPlazo());
                calcularDiasPlazo(null);
            }

            if (DatosMaestra.isOcultarInformacionTercero()) {
                String cupo = nodo.getCupo(), plazo = nodo.getPlazo();

                if (!nodo.getId().equals("1010")) {
                    Object[] resul = instancias.getSql().getCarteraPendiente1(nodo.getIdSistema());
                    BigDecimal cartera = big.getBigDecimal(resul[0]);
                    if (cartera == null) {
                        cartera = big.getBigDecimal("0");
                    } else {
                        BigDecimal valorTotal = big.getBigDecimal(resul[1]);
                        cartera = cartera.subtract(valorTotal);
                    }

                    if (cupo.equals("") || cupo == null) {
                        cupo = "0";
                    }

                    try {
                        if (big.getBigDecimal(cupo).subtract(cartera).compareTo(BigDecimal.ZERO) != 1) {
                            lbCupo.setText("CARTERA PENDIENTE: " + big.setMoneda(cartera) + "  Y  TIENE CUPO DE: " + this.simbolo + " 0");
                            txtCupo.setText(this.simbolo + " 0");
                            txtCartera.setText(big.setMoneda(cartera));
                        } else {
                            lbCupo.setText("CARTERA PENDIENTE DE: " + big.setMoneda(cartera) + "  Y  TIENE CUPO DE: " + big.setMoneda(big.getBigDecimal(cupo).subtract(cartera)));
                            txtCupo.setText(big.setMoneda(big.getBigDecimal(cupo).subtract(cartera)));
                            txtCartera.setText(big.setMoneda(cartera));
                        }
                    } catch (Exception e) {
                        lbCupo.setText("CARTERA PENDIENTE DE: " + big.setMoneda(cartera) + "  Y  TIENE CUPO DE: " + this.simbolo + " 0");
                        txtCupo.setText(this.simbolo + " 0");
                        txtCartera.setText(big.setMoneda(cartera));
                    }

                    lbCupo.setVisible(true);
                }
            }

            return;
        }
        NC = null;
        ventanaTerceros(nit);
    }

    public void ventanaTerceros(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setOpc("factura");
        buscar.setFactura(this);
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        instancias.setCampoActual(txtNit);
        txtNit.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void ventanaTerceros1(String nit) {
        buscClientes buscar = new buscClientes(instancias.getMenu(), true, false, null, "");
        buscar.setOpc("factura");
        buscar.setFactura(this);
        buscar.setLocationRelativeTo(null);
        instancias.setBusClientes(buscar);
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarTotales() {

        int i;
        BigDecimal subtotal = BigDecimal.ZERO, iva = BigDecimal.ZERO, impoconsumo = BigDecimal.ZERO, total = BigDecimal.ZERO, descuentos = BigDecimal.ZERO,
                rtf = BigDecimal.ZERO, rti = BigDecimal.ZERO, copago = BigDecimal.ZERO, cantUnidades = BigDecimal.ZERO, impuesto = BigDecimal.ZERO, descGeneral = BigDecimal.ZERO;

        for (i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO") || tipoProceso.equalsIgnoreCase("orden")) {
                subtotal = subtotal.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 4))));
                descuentos = descuentos.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 6))));

                impoconsumo = impoconsumo.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 8))));
                iva = iva.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 33))));

                total = total.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 9))));
                copago = copago.add(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 17))));
            }

            cantUnidades = cantUnidades.add(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", ".")));
        }

        try {
            descGeneral = big.getMoneda(txtDescGeneral.getText());
        } catch (Exception e) {
        }

        descuentos = descuentos.add(descGeneral);

        if (cmbRtf.getSelectedIndex() > 0) {
            rtf = subtotal.multiply(big.getBigDecimal(cmbRtf.getSelectedItem())).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_UP);
        }

        if (descGeneral.compareTo(BigDecimal.ZERO) > 0) {
            subtotal = subtotal.subtract(descGeneral);
            iva = subtotal.multiply(big.getBigDecimal("1.19")).subtract(subtotal);
        }

        if (chkReteIva.isSelected()) {
            rti = iva.multiply(big.getBigDecimal("15")).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_DOWN);
        }

        txtSubTotal.setText(big.setMoneda(subtotal));
        txtTotalDescuentos.setText(big.setMoneda(descuentos));
        txtTotalIva.setText(big.setMoneda(iva));
        txtTotalImpoconsumo.setText(big.setMoneda(impoconsumo));
        txtCopago.setText(big.setMoneda(copago));

        if (this.esFacturaCredito) {
            txtValorVenta.setText(big.setMonedaExacta(total.subtract(descGeneral)));
        } else {
            txtTotal.setText("Total: " + big.setMoneda(total.add(impuesto).subtract(descGeneral)));
        }

        txtRtf.setText(big.setMonedaExacta(rtf));
        txtRiva.setText(big.setMonedaExacta(rti));

//        if (!txtDiasPlazo.getText().equals("0") && !txtDiasPlazo.getText().equals("")) {
//            this.setPlazo(txtDiasPlazo.getText(), big.getMoneda(txtTotal.getText().replace("Total: ", "")));
//        }
        txtCantProductos.setText(Integer.toString(tblProductos.getRowCount()));
        txtCantUnidades.setText(cantUnidades.toString());

        calcularEfectivo();

    }

    public void calcularEfectivo() {
        BigDecimal total = big.getMoneda(txtTotal.getText().replace("Total: ", ""));
    }

    public void desdeHospitalizacion(String cliente, String consecutivo, String horas, String dias) {

        limpiar(true);
        txtNit.setText(cliente);
        cargarCliente(cliente);

        if (!horas.equals("0")) {
            cargarProducto("HSP1", Utilidades.convertirBigDecimal(horas), 1, "", "", "", true, "", "", "", "", "");
        }

        if (!dias.equals("0")) {
            cargarProducto("HSP2", Utilidades.convertirBigDecimal(dias), 1, "", "", "", true, "", "", "", "", "");
        }

        Object[][] productosAdicionales = instancias.getSql().getProductosHospitalizaciones(consecutivo);

        if (productosAdicionales != null) {
            for (int i = 0; i < productosAdicionales.length; i++) {
                cargarProducto(productosAdicionales[i][0].toString(), Utilidades.convertirBigDecimal(productosAdicionales[i][2].toString()), 1, "", "", "", false, "", "", "", "", "");
                tblProductos.setValueAt(productosAdicionales[i][3].toString(), tblProductos.getRowCount() - 1, 2);
                KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
                evento.setKeyCode(KeyEvent.VK_ENTER);
                tblProductosKeyReleased(evento);
            }
        }

        cargarTotales();
        txtObservaciones.setText(consecutivo);
        saltarPasosFactura = false;
        ndHospitalizacion = consecutivo;
        diasHospitalizacion = dias;
        horasHospitalizacion = horas;

//        btnGuardar1ActionPerformed(null);
    }

    public void desdeGuarderia(String cliente, String consecutivo, String horas, String dias) {

        limpiar(true);
        txtNit.setText(cliente);
        cargarCliente(cliente);

        if (!horas.equals("0")) {
            cargarProducto("GD1", Utilidades.convertirBigDecimal(horas), 1, "", "", "", true, "", "", "", "", "");
        }

        if (!dias.equals("0")) {
            cargarProducto("GD2", Utilidades.convertirBigDecimal(dias), 1, "", "", "", true, "", "", "", "", "");
        }

        Object[][] productosAdicionales = instancias.getSql().getProductosProductosServiciosAdicionales(consecutivo);
        if (productosAdicionales != null) {
            for (int i = 0; i < productosAdicionales.length; i++) {
                cargarProducto(productosAdicionales[i][0].toString(), Utilidades.convertirBigDecimal(productosAdicionales[i][1].toString()), 1, productosAdicionales[i][3].toString(),
                        productosAdicionales[i][6].toString(), productosAdicionales[i][7].toString(), false, "", "", "", "", "");
                tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(productosAdicionales[i][2].toString())), tblProductos.getRowCount() - 1, 2);
                KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
                evento.setKeyCode(KeyEvent.VK_ENTER);
                tblProductosKeyReleased(evento);
            }
        }

        cargarTotales();
        txtObservaciones.setText(consecutivo);
        saltarPasosFactura = false;
        ndGuarderia = consecutivo;
        btnImprimirActionPerformed(null);
    }

    public void desdePeluqueria(String cliente, String productos, String consecutivo) {

        this.setVisible(true);

        limpiar(true);
        txtNit.setText(cliente);
        cargarCliente(cliente);

        cargarProducto(productos, BigDecimal.ONE, 1, "", "", "", true, "", "", "", "", "");

        Object[][] productosAdicionales = instancias.getSql().getProductosProductosServiciosAdicionales(consecutivo);
        if (productosAdicionales != null) {
            for (int i = 0; i < productosAdicionales.length; i++) {
                cargarProducto(productosAdicionales[i][0].toString(), Utilidades.convertirBigDecimal(productosAdicionales[i][1].toString()), 1, productosAdicionales[i][3].toString(),
                        productosAdicionales[i][6].toString(), productosAdicionales[i][7].toString(), true, "", "", "", "", "");

                tblProductos.setValueAt(big.setMoneda(big.getMoneda(productosAdicionales[i][2].toString())), tblProductos.getRowCount() - 1, 2);
                KeyEvent evento = new KeyEvent(tblProductos, 0, 0, 0, 0);
                evento.setKeyCode(KeyEvent.VK_ENTER);
                tblProductosKeyReleased(evento);
            }
        }

        cargarTotales();
        txtObservaciones.setText(consecutivo);
        saltarPasosFactura = false;
        ndPeluqueria = consecutivo;
//        btnGuardar1ActionPerformed(null);
    }

    public String desdeOrdenMedica(String cliente, String[][] productos, boolean opc) {
        limpiar(true);
        rdTipoNormal.setSelected(!opc);
        rdTipoCopago.setSelected(opc);

        txtNit.setText(cliente);
        cargarCliente(cliente);

        for (int i = 0; i < productos.length; i++) {
            try {
                if (!productos[i][0].equals("") || productos[i][0] != null) {
                    cargarProducto(productos[i][0], Utilidades.convertirBigDecimal(productos[i][1]), 1, "", "", "", false, "", "", "", "", "");
                    tblProductos.setValueAt(productos[i][2], tblProductos.getRowCount() - 1, 6);
                    tblProductos.setValueAt(big.getMoneda(productos[i][3]), tblProductos.getRowCount() - 1, 2);
                    tblProductos.setValueAt(productos[i][4], tblProductos.getRowCount() - 1, 17);
                    calcularTabla(tblProductos.getRowCount() - 1, false);
                }
            } catch (Exception e) {
            }
        }

//        intDevuelta devuelta = new intDevuelta(null, false, big.getBigDecimal("0"), null, null, cliente);<
        return facturar(null, true, "ordenMedica");
    }

    public String desdeOrdenMedica(String cliente, String[] productos, boolean opc) {

        limpiar(true);

        rdTipoNormal.setSelected(!opc);
        rdTipoCopago.setSelected(opc);

        txtNit.setText(cliente);
        cargarCliente(cliente);

        cargarProducto(productos[0], Utilidades.convertirBigDecimal(productos[1]), 1, "", "", "", false, "", "", "", "", "");

        tblProductos.setValueAt(productos[2], 0, 6);
        tblProductos.setValueAt(productos[3], 0, 2);
        tblProductos.setValueAt(productos[4], 0, 17);

        calcularTabla(0, false);

        saltarPasosFactura = true;

        return facturar(null, true, "ordenMedica");
    }

    public void setVendedores(String[] Vendedores) {
        cmbVendedor.removeAllItems();
        for (String Vendedore : Vendedores) {
            cmbVendedor.addItem(Vendedore);
        }
    }

    public void setDomiciliarios(String[] Domiciliarios) {
        for (String Vendedore : Domiciliarios) {
            cmbVendedor.addItem(Vendedore);
        }
    }

    public void nuevoTercero(String id) {
        txtNit.setText(id);
        ModeloContacto nodo = instancias.getSql().getDatosTercero(id);
        ID_CLIENTE_CARGADO = nodo.getIdSistema();
        txtNombre.setText(nodo.getNombre());
        txtCodigoProducto.requestFocus();
    }

    public void nuevoProducto(String id) {
        cargarProducto(id, BigDecimal.ONE, 1, "", "", "", true, "", "", "", "", "");
    }

    public void desdeMensualidad(Object[][] Productos, String cliente, String diasPlazo, String placa) {

        limpiar(true);
        int i = 0;
        for (Object[] reg : Productos) {
            cargarProducto((String) reg[0], Utilidades.convertirBigDecimal((String) reg[1]), 1, "", "", "", false, "", "", "", "", (String) reg[3] + " hasta " + (String) reg[4]);
            tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(reg[6].toString())), i, 2);
//            tblProductos.setValueAt(big.getBigDecimal(reg[5].toString()), i, 5);
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.setRowSelectionInterval(i, i);
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
            i++;
        }

        txtNit.setText(cliente);
        cargarCliente(cliente);

        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        txtObservaciones.setText("VENTA DE MENSUALIDAD");
        txtPlaca1.setText(placa);

        saltarPasosFactura = true;
        if (metodos.msgPregunta(null, "¿Desea imprimir factura?") == 0) {
            btnImprimirActionPerformed(null);
        } else {
            btnGuardarActionPerformed(null);
        }
    }

    public void cargarProducto(String codigo, BigDecimal cantidad, int plu, String imei, String lote, String idProductoACargar, Boolean agrupar, String talla, String color,
            String temp, String fechaVence, String detalleMensualidad) {

        String baseUtilizada = "bdProductos";
        ndProducto nodo = null;

        String CodigoProd = "";
        if (codigo.equals("")) {
            CodigoProd = "";
        } else {
            Object[][] listado = instancias.getSql().getCodigosRelacionados(codigo, " where codigo");
            if (listado.length > 0) {
                codigo = listado[0][0].toString();
            }

            nodo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
            if (nodo.getIdSistema() != null) {
                CodigoProd = nodo.getIdSistema();
            }
        }

        if (!CodigoProd.equals("")) {
            if (codigo.equals(nodo.getCodigo2())) {
                plu = 2;
            } else if (codigo.equals(nodo.getCodigo3())) {
                plu = 3;
            } else if (codigo.equals(nodo.getCodigo4())) {
                plu = 4;
            } else if (codigo.equals(nodo.getCodigo5())) {
                plu = 5;
            } else if (codigo.equals(nodo.getCodigo6())) {
                plu = 6;
            } else if (codigo.equals(nodo.getCodigo7())) {
                plu = 7;
            } else if (codigo.equals(nodo.getCodigo8())) {
                plu = 8;
            }

            if (instancias.getConfiguraciones().isProductosDetallados()) {
                for (int j = 0; j < tblProductos.getRowCount(); j++) {
                    String idProductoTabla = tblProductos.getValueAt(j, 29).toString();
                    if (!idProductoTabla.isEmpty() && idProductoTabla.equals(idProductoACargar)) {
                        if (!imei.isEmpty()) {
                            ControladorAlertas.bigAlert("El imei '" + imei + "' ya esta cargado.");
                            return;
                        } else if (!lote.isEmpty()) {
                            ControladorAlertas.bigAlert("Este producto con el lote '" + lote + "' ya se cargó.");
                            return;
                        }
                    }
                }
            }

            tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());

            try {
                int num = Integer.parseInt(DatosMaestra.getLimite());
                if (!rdPos.isSelected()) {
                    if (num > 0) {
                        if (tblProductos.getRowCount() >= num) {
                            if (!ControladorAlertas.option("Limite de productos, ¿Desea continuar?")) {
                                return;
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            if (agrupar) {
                if (nodo.getCodigo() != null) {
                    if (nodo.getGrupo() != null) {
                        if (nodo.getCodigo().equals("IMP01") || nodo.getGrupo().equals("GRP-02")) {
                            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                                if (nodo.getIdSistema().equalsIgnoreCase((String) tblProductos.getValueAt(j, 32)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 12)) + "")) {
                                    tblProductos.setValueAt((big.getMoneda(tblProductos.getValueAt(j, 3).toString().replace(".", ",")).add(cantidad)).toString().replace(".", ","), j, 3);
                                    txtCodigoProducto.setText("");
                                    tblProductos.setColumnSelectionInterval(0, 0);
                                    tblProductos.setRowSelectionInterval(j, j);
                                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                                    tblProductosKeyReleased(x);
                                    if (instancias.isLector()) {
                                        txtCodigoProducto.requestFocus();
                                    } else {
                                        tblProductos.editCellAt(0, 3);
                                        tblProductos.setColumnSelectionInterval(3, 3);
                                        tblProductos.transferFocus();
                                    }
                                    return;
                                }
                            }
                        }
                    }
                }
            }

            if (agrupar) {
                try {
                    if (DatosMaestra.isCombinarProductos()) {
                        if (nodo.getUsuario().equals("ADMIN")) {
                            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                                if (nodo.getIdSistema().equalsIgnoreCase((String) tblProductos.getValueAt(j, 32)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 12)) + "")) {
                                    tblProductos.setValueAt((big.getMoneda(tblProductos.getValueAt(j, 3).toString().replace(".", ",")).add(cantidad)).toString().replace(".", ","), j, 3);
                                    txtCodigoProducto.setText("");

                                    tblProductos.setColumnSelectionInterval(0, 0);
                                    tblProductos.setRowSelectionInterval(j, j);

                                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                                    tblProductosKeyReleased(x);

                                    if (instancias.isLector()) {
                                        txtCodigoProducto.requestFocus();
                                    } else {
                                        tblProductos.editCellAt(0, 3);
                                        tblProductos.setColumnSelectionInterval(3, 3);
                                        tblProductos.transferFocus();
                                    }

                                    txtCantidad.setText(DatosMaestra.getCantidadEstablecidaAlCargar());

                                    return;
                                }
                            }
                        } else {

                        }
                    }
                } catch (Exception e) {
                }
            }

            if (instancias.getSql().getProdActivo(nodo.getCodigo())) {
                metodos.msgError(null, "Este producto esta inactivo");
                lbProducto.requestFocus();
                return;
            }

            String tipo = Enums.DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());

            if (!tipo.equals("") && idProductoACargar.equals("") && !this.tipoProceso.equals("cotizacion")) {
                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, "Salida", this.tipoProceso, BigDecimal.ZERO);
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;
            } else {

                if (this.plu) {
                    this.plu = false;
                    int cant = 0;

                    if (nodo.isPlu2()) {
                        cant++;
                    }
                    if (nodo.isPlu3()) {
                        cant++;
                    }
                    if (nodo.isPlu4()) {
                        cant++;
                    }
                    if (nodo.getPlu5()) {
                        cant++;
                    }
                    if (nodo.getPlu6()) {
                        cant++;
                    }
                    if (nodo.getPlu7()) {
                        cant++;
                    }
                    if (nodo.getPlu8()) {
                        cant++;
                    }

                    if (cant > 0) {
                        seleccionarPLU pluu = new seleccionarPLU(null, true, "bdProductos");
                        pluu.setFactura(this);
                        pluu.setInstancias(instancias, nodo.getIdSistema());
                        pluu.setOpc("factura");
                        pluu.setVisible(true);
                        return;
                    }
                }

                BigDecimal aux = new BigDecimal("0.0"), iva, valor;
                valor = big.getBigDecimal(nodo.getL1());
                iva = big.getBigDecimal(nodo.getIva());
                iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
                aux = valor.divide(iva, 2);
                aux = valor.subtract(aux);

                BigDecimal cant = Utilidades.convertirBigDecimal(nodo.getFisicoInventario());

                aux = aux.divide(new BigDecimal("100"));
                //
                String cant2 = "1";
                String desc = nodo.getDescripcion();
                String lista = nodo.getL1(), lista1 = "L1";
                switch (plu) {
                    case 2:
                        cant2 = nodo.getCantidad2();
                        desc = nodo.getDescripcion2();
                        lista = nodo.getL2();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad2()), 4, RoundingMode.HALF_UP);
                        lista1 = "L2";
                        break;
                    case 3:
                        cant2 = nodo.getCantidad3();
                        desc = nodo.getDescripcion3();
                        lista = nodo.getL3();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad3()), 4, RoundingMode.HALF_UP);
                        lista1 = "L3";
                        break;
                    case 4:
                        cant2 = nodo.getCantidad4();
                        desc = nodo.getDescripcion4();
                        lista = nodo.getL4();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad4()), 4, RoundingMode.HALF_UP);
                        lista1 = "L4";
                        break;
                    case 5:
                        cant2 = nodo.getCantidad5();
                        desc = nodo.getDescripcion5();
                        lista = nodo.getL5();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad5()), 4, RoundingMode.HALF_UP);
                        lista1 = "L5";
                        break;
                    case 6:
                        cant2 = nodo.getCantidad6();
                        desc = nodo.getDescripcion6();
                        lista = nodo.getL6();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad6()), 4, RoundingMode.HALF_UP);
                        lista1 = "L6";
                        break;
                    case 7:
                        cant2 = nodo.getCantidad7();
                        desc = nodo.getDescripcion7();
                        lista = nodo.getL7();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad7()), 4, RoundingMode.HALF_UP);
                        lista1 = "L7";
                        break;
                    case 8:
                        cant2 = nodo.getCantidad8();
                        desc = nodo.getDescripcion8();
                        lista = nodo.getL8();
                        cant = cant.divide(big.getBigDecimal(nodo.getCantidad8()), 4, RoundingMode.HALF_UP);
                        lista1 = "L8";
                        break;
                }

                BigDecimal restante = cant.subtract(cantidad);

                String invActual = "N/A";
                String invFinal = "N/A";
                if (nodo.getManejaInventario()) {
                    invActual = Utilidades.formatearCantidadVista(cant);
                    invFinal = Utilidades.formatearCantidadVista(restante);
                }

                boolean datosGrupo = true;
                try {
                    datosGrupo = (boolean) instancias.getSql().getDatosGrupo(nodo.getGrupo())[1];
                } catch (Exception e) {
                    datosGrupo = true;
                }

                Icon icono = null;
                ImageIcon fot = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
                icono = new ImageIcon(fot.getImage().getScaledInstance(30, 25, Image.SCALE_DEFAULT));

                String cadena = "";
                if (instancias.getConfiguraciones().isRestaurante()) {

                } else {
//                    if (!idProd.equals("")) {
//                        cadena = idProd;
//                    }
                }

                String grupo = "";
                if (nodo.getGrupo() != null) {
                    grupo = nodo.getGrupo();
                }

                String detalle = "";
                if (!imei.equals("")) {
                    detalle = imei;
                }

                if (!color.equals("")) {
                    if (detalle.equals("")) {
                        detalle = color;
                    } else {
                        detalle = detalle + "-" + color;
                    }
                }

                if (!talla.equals("")) {
                    if (detalle.equals("")) {
                        detalle = talla;
                    } else {
                        detalle = detalle + "-" + talla;
                    }
                }

                if (!lote.equals("")) {
                    detalle = lote;
                }

                if (!fechaVence.equals("")) {
                    if (detalle.equals("")) {
                        detalle = fechaVence;
                    } else {
                        detalle = detalle + "-" + fechaVence;
                    }
                }

                if (!detalleMensualidad.equals("")) {
                    detalle = detalleMensualidad;
                }

                modeloPro.addRow(new Object[]{nodo.getCodigo(), desc, big.setMoneda(big.getBigDecimal(lista)),
                    Utilidades.formatearCantidadVista(cantidad), big.setMoneda(big.getBigDecimal(lista)), "0", "0",
                    big.setMoneda(big.getBigDecimal(nodo.getIva())).replace(this.simbolo + " ", ""),
                    this.simbolo + " 0", big.setMoneda(big.getBigDecimal(lista)),
                    nodo.getUbicacion1(), nodo.getReferencia(), plu,
                    Utilidades.formatearCantidadVista(big.getBigDecimal(cant2).multiply(cantidad)), this.simbolo + " 0", "", "PENDIENTE", this.simbolo + " 0", datosGrupo, this.simbolo + " 0",
                    this.simbolo + " 0", cadena, new JLabel(icono), big.setMonedaExacta(big.getBigDecimal(nodo.getImpoconsumoVenta())).replace(this.simbolo + " ", ""), "", "", "",
                    detalle, lote, idProductoACargar, "Nuevo", "Sin-Permiso", nodo.getIdSistema(), big.setMoneda(big.getBigDecimal(aux)), grupo, nodo.getUnd(),
                    nodo.getManejaInventario(), lista1, invActual, invFinal});
                txtCodigoProducto.setText("");

                tblProductos.scrollRectToVisible(tblProductos.getCellRect(tblProductos.getRowCount() - 1, 0, true));
                cargarTotales();

                tblProductos.setColumnSelectionInterval(0, 0);
                tblProductos.setRowSelectionInterval(modeloPro.getRowCount() - 1, modeloPro.getRowCount() - 1);

                if (!this.plu) {
                    if (cmbListaPrecio.getSelectedIndex() > 0) {
                        cmbListas.setSelectedItem(cmbListaPrecio.getSelectedItem());
                        tblProductos.setValueAt(cmbListaPrecio.getSelectedItem(), tblProductos.getRowCount() - 1, 37);
                        tblProductos.setColumnSelectionInterval(37, 37);
                        tblProductos.setRowSelectionInterval(tblProductos.getRowCount() - 1, tblProductos.getRowCount() - 1);
                        cambiarListaCliente();
                    }
                }
            }

            btnPasarACongelada.setVisible(esValidoParaPasarACongeladas());

            calcularTabla(modeloPro.getRowCount() - 1, false);
            txtCantidad.setText(DatosMaestra.getCantidadEstablecidaAlCargar());

            if (instancias.isLector()) {
                txtCodigoProducto.requestFocus();
            } else {
                if (DatosMaestra.getFocoDespuesDeCargarProducto().equals("Valor")) {
                    tblProductos.editCellAt(tblProductos.getRowCount() - 1, 2);
                    tblProductos.setColumnSelectionInterval(2, 2);
                    tblProductos.transferFocus();
                } else {
                    tblProductos.editCellAt(tblProductos.getRowCount() - 1, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();
                }
            }

            return;
        }

        if (codigo.equals("")) {
            try {
                int num = Integer.parseInt(DatosMaestra.getLimite());
                if (!rdPos.isSelected()) {
                    if (num > 0) {
                        if (tblProductos.getRowCount() >= num) {
                            if (!ControladorAlertas.option("Limite de productos, ¿Desea continuar?")) {
                                return;
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            ventanaProductos(codigo);
        } else {
            ControladorAlertas.alert("El codigo no existe!");
            txtCodigoProducto.setText("");
            lbProducto.requestFocus();
        }
    }

    public void ventaDiseno(String mov, String cadena, String prod, String precio) {
        if (mov.equals("Guardar")) {

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (tblProductos.getValueAt(i, 32).equals(prod)) {
                    modeloPro.removeRow(i);
                    break;
                }
            }

            String cantidadEstablecida = DatosMaestra.getCantidadEstablecidaAlCargar();
            cargarProducto(prod, Utilidades.convertirBigDecimal(cantidadEstablecida), 1, "", "", cadena, false, "", "", "", "", "");

            tblProductos.setValueAt(precio, tblProductos.getRowCount() - 1, 2);

        } else {
            txtCodigoProducto.setText("");
        }
    }

    public void cargarArticulos(String tipo) {
        DefaultTableModel modelo = (DefaultTableModel) tblArticulos.getModel();

        while (tblArticulos.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        Object[][] articulo = instancias.getSql().getArticulos(tipo);

        if (articulo.length > 0) {
            for (int i = 0; i < articulo.length; i++) {
                modelo.addRow(new Object[]{articulo[i][0], articulo[i][1], false, "", "", ""});
            }
            return;
        }
    }

    public void actualizarVistaConsecutivo() {
        if (instancias.getConfiguraciones().isRestaurante()
                && (this.tipoProceso.equals(TipoDocumento.PEDIDO.getValor()) || this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor()))) {

            if (DatosMaestra.isTurnoActivo()) {
                lbOtroConsecutivo.setText("Turno");
                lbOtroConsecutivo.setVisible(true);
                txtTurno.setVisible(true);
                txtTurno.setEnabled(false);
                txtTurno.setText(DatosMaestra.getNumeroTurno());
            } else {
                lbOtroConsecutivo.setVisible(false);
                txtTurno.setVisible(false);
            }

        } else if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor()) && DatosMaestra.isConsecutivoAdicional()) {
            lbOtroConsecutivo.setText("Otro Consecutivo:");
            txtTurno.setVisible(true);
            lbOtroConsecutivo.setVisible(true);
            txtTurno.setEnabled(true);
        } else {
            txtTurno.setVisible(false);
            lbOtroConsecutivo.setVisible(false);
        }
    }

    public void cargarProductos1(Object[][] productos) {
        String cantEstablecida = txtCantidad.getText();

        for (int i = 0; i < productos.length; i++) {
            ndProducto nodo = instancias.getSql().getDatosProducto(productos[i][0].toString(), "bdProductos");

            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }

            this.plu = true;
            if (nodo.getUsuario().equals("ADMIN")) {
                cargarProducto(codigo, Utilidades.convertirBigDecimal(cantidad), 1, "", "", "", true, "", "", "", "", "");
            } else {
                for (int j = 0; j < Integer.parseInt(cantidad); j++) {
                    cargarProducto(codigo, BigDecimal.ONE, 1, "", "", "", true, "", "", "", "", "");
                }
            }
        }

        tblProductos.changeSelection(tblProductos.getRowCount() - 1, 0, false, false);
        tblProductos.removeEditor();

        if (tblProductos.editCellAt(tblProductos.getRowCount() - 1, 0)) {
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.transferFocus();
        }

        if (DatosMaestra.getFocoDespuesDeCargarProducto().equals("Valor")) {
            tblProductos.editCellAt(tblProductos.getRowCount() - 1, 2);
            tblProductos.setColumnSelectionInterval(2, 2);
            tblProductos.transferFocus();
        } else {
            tblProductos.editCellAt(tblProductos.getRowCount() - 1, 3);
            tblProductos.setColumnSelectionInterval(3, 3);
            tblProductos.transferFocus();
        }
    }

    public void calcularTablaPreVentaValores() {
        int q = tblProductos.getRowCount();
        for (int i = 0; i < q; i++) {
            calcularTabla(i, false);
        }
    }

    public static void main(String[] args) {
        BigDecimal aux;
        aux = big.getBigDecimal("-154.5");
    }

    public String generarFacturaExterior(String cliente, String[][] productos, String diasPlazo, boolean devueltaSino, String lote, String mes) {
        limpiar(true);
        txtNit.setText(cliente);
        cargarCliente(cliente);
        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        loteGeneral = lote;

        for (String[] producto : productos) {
            cargarProducto((String) producto[0], BigDecimal.ONE, 1, "", "", "", true, "", "", "", "", "");
        }

//        tblProductos.editCellAt(tblProductos.getSelectedRow(), 6);
//        tblProductos.setColumnSelectionInterval(6, 6);
//        tblProductos.transferFocus();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(productos[i][1])), i, 2);
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
        }

        cargarTotales();
        VistaMetodoPagos devuelta = new VistaMetodoPagos(null, false, big.getBigDecimal("0"), null, cliente, big.getBigDecimal("0"));

        if (devueltaSino) {
            devuelta = null;
        }

        cmbMes.setSelectedItem(mes);
        saltarPasosFactura = true;

//        btnGuardar1ActionPerformed(null);
        String fact = "CCOBRO-" + (String) instancias.getSql().getNumConsecutivo("CCOBRO")[0];
        btnImprimirActionPerformed(null);
        return fact;
    }

    public void ventanaPlacas1(String nit, String condi) {
        buscPlacas buscar = new buscPlacas(instancias.getMenu(), true, condi);
        buscar.setLocationRelativeTo(null);
        buscar.setInstancia(instancias);
        instancias.setBuscPlacas(buscar);
        instancias.setCampoActual(txtPlaca);
        txtPlaca.requestFocus();
        buscar.noEncontrado(nit);
        buscar.show();
    }

    public void cargarClienteYProducto(String cliente, String[][] producto) {
//        btnLimpiarActionPerformed(null);

        txtRtf.setText(this.simbolo + " 0");
        txtRiva.setText(this.simbolo + " 0");
        cmbRtf.setSelectedIndex(0);
        chkReteIva.setSelected(false);
        txtObservaciones.setText("");
        txtPorcentaje.setText("");
        txtMarca.setText("");
        txtColor.setText("");
        txtModelo.setText("");
        txtMotor.setText("");
        txtPlaca.setText("");
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnImprimir.setEnabled(true);
        int i, j = tblProductos.getRowCount();

        for (i = 0; i < j; i++) {
            modeloPro.removeRow(0);
        }
        txtSubTotal.setText(this.simbolo + " 0");
        txtTotal.setText("Total: " + this.simbolo + " 0");
        txtTotalIva.setText(this.simbolo + " 0");
        txtTotalDescuentos.setText(this.simbolo + " 0");
        txtNit.setText("");
        txtNombre.setText("");
        txtDiasPlazo.setText("");
        txtVencimiento.setText(txtFechaFactura.getText());

        int fila = 0;
        for (int ser = 0; ser < tblComprobantes.getRowCount(); ser++) {
            if ((Boolean) tblComprobantes.getValueAt(ser, 2)) {
                fila = ser;
            }
        }

        actualizarConsecutivo(fila);

        activarCampos(true);
        txtNit.setText(cliente);
        cargarCliente(cliente);
        cargarProducto(producto[0][0], Utilidades.convertirBigDecimal(producto[0][1]), 1, "", "", "", true, "", "", "", "", "");
    }

    public void cargarDescuento(Integer fila, BigDecimal porcentajeDescuento, BigDecimal descuento, String descripcionDescuento) {
        if (null == porcentajeDescuento && null == descuento) {
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                tblProductos.setValueAt(0, i, 5);
                tblProductos.setValueAt("$ 0", i, 6);
                tblProductos.setValueAt("", i, 31);
                calcularTabla(i, false);
            }

            DESCUENTO_GENERAL_CARGADO = false;
        } else {
            if (null != fila) {
                if (null == porcentajeDescuento) {
                    tblProductos.setValueAt(descuento, fila, 6);
                } else {
                    tblProductos.setValueAt(porcentajeDescuento, fila, 5);
                }

                tblProductos.setValueAt(descripcionDescuento, fila, 31);
                calcularTabla(fila, false);
            } else {
                for (int i = 0; i < tblProductos.getRowCount(); i++) {
                    tblProductos.setValueAt(porcentajeDescuento, i, 5);
                    tblProductos.setValueAt(descripcionDescuento, i, 31);
                    calcularTabla(i, false);
                }

                DESCUENTO_GENERAL_CARGADO = true;
            }
        }
    }

    private void agregarAdicionesATabla(String baseUtilizada) {

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
            BigDecimal cantidadProducto = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 13).toString());

            if (nodo.getUsuario().equals("FACTURA")) {
                String opciones = "";

                try {
                    opciones = tblProductos.getValueAt(i, 21).toString().split("; ")[1];
                } catch (Exception e) {
                }

                if (!opciones.equals("")) {
                    for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {
                        if (opcion.esAdicion()) {
                            String codigo = opcion.getCodigo();
                            BigDecimal cantidad = opcion.getCantidad();
                            String estado = opcion.getEstado();
                            BigDecimal cantidadTotal = cantidadProducto.multiply(cantidad);

                            if (estado.equals(" true")) {
                                ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                if (nodo1.getGrupo() != null) {
                                    if (nodo1.getGrupo().equals("GRP-02")) {
                                        cargarProducto(codigo, cantidadTotal, 1, "", "", "", false, "", "", "", "", "");
                                        tblProductos.setValueAt("PRODUCTO-AGREGADO", tblProductos.getRowCount() - 1, 31);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void calcularTabla(int fila, boolean mostrarAlerta) {

        String baseUtilizada = "bdProductos";
        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

        BigDecimal copago = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 17)));
        BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 3));
        BigDecimal valorDescuento = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 6)));
        BigDecimal valorUnitario = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
        BigDecimal subtotal = valorUnitario.multiply(cantidad);

        tblProductos.setValueAt(big.setMonedaExacta(valorDescuento), fila, 6);

        BigDecimal descuento, total, porcentaje2, compra, utilidadMax, utilidadMin;

        String listaPrecio = tblProductos.getValueAt(fila, 37).toString();
        if (!DatosMaestra.isModificarPrecio()) {
            BigDecimal valorReal = funcionalidadVentas.revisarPrecioProducto(listaPrecio, valorUnitario, nodo);
            tblProductos.setValueAt(big.setMoneda(valorReal), fila, 2);
        }

        if (tblProductos.getValueAt(fila, 6).toString().equals("") || tblProductos.getValueAt(fila, 5).toString().equals("")) {
            porcentaje2 = big.getBigDecimal("0");
            descuento = big.getMoneda("0");
        } else {
            Object[] datos = calcularDescuento(fila, subtotal, mostrarAlerta);
            descuento = (BigDecimal) datos[0];
            porcentaje2 = (BigDecimal) datos[1];
        }

        if (!instancias.getUsuario().equals("ADMIN")) {
            if (porcentaje2.compareTo(big.getBigDecimal(instancias.getDescuentoMaximoVentas())) == 1) {

                Boolean solicitudPermisos = Boolean.parseBoolean(String.valueOf(DatosMaestra.isCiudadBuscador()));
                if (solicitudPermisos) {

                    String lineaPermisos = tblProductos.getValueAt(fila, 31).toString();
                    System.out.println("information: " + tblProductos.getValueAt(fila, 31));

                    if (lineaPermisos.contains("PERMISO-")) {
                        int cantidadRegistros = lineaPermisos.split("///").length;
                        String idPermisoAsignado = lineaPermisos.split("///")[cantidadRegistros - 1];
                        Object[] informacion = instancias.getSql().getInformacionPermiso(idPermisoAsignado);

                        if (big.getBigDecimal(informacion[2]).compareTo(porcentaje2) < 0) {
                            vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true, "El descuento máximo es " + informacion[2] + "%.", "DESCUENTO",
                                    big.setNumero(porcentaje2), this.tipoProceso);
                            permisos.setLocationRelativeTo(null);
                            permisos.setVisible(true);

                            if (solicitudPermiso) {
                                String lineaFinal = "";
                                for (int i = 0; i < cantidadRegistros - 1; i++) {
                                    lineaFinal = lineaFinal + lineaPermisos.split("///")[i] + "///";
                                }

                                System.out.println("linea finalll mipp: " + lineaFinal + permisoNumero);
                                tblProductos.setValueAt(lineaFinal + permisoNumero, fila, 31);
                            } else {
                                porcentaje2 = big.getBigDecimal("0");
                                descuento = big.getMoneda("0");
                            }
                        }
                    } else {
                        vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true,
                                "El descuento máximo es " + instancias.getDescuentoMaximoVentas() + "%.", "DESCUENTO",
                                big.setNumero(porcentaje2), this.tipoProceso);
                        permisos.setLocationRelativeTo(null);
                        permisos.setVisible(true);

                        if (solicitudPermiso) {
                            String lineaFinal = tblProductos.getValueAt(fila, 31).toString() + "///" + permisoNumero;
                            tblProductos.setValueAt(lineaFinal, fila, 31);
                        } else {
                            porcentaje2 = big.getBigDecimal("0");
                            descuento = big.getMoneda("0");
                        }
                    }

                    solicitudPermiso = false;
                } else {
                    porcentaje2 = big.getBigDecimal("0");
                    descuento = big.getMoneda("0");
                }
            }
        }

        BigDecimal totalIva = BigDecimal.ZERO, totalImpoconsumo = BigDecimal.ZERO;
        BigDecimal porcentajeIva = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 7))).divide(BigDecimal.valueOf(100));
        BigDecimal porcentajeImpoconsumo = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 23))).divide(BigDecimal.valueOf(100));
        BigDecimal valorBaseProducto = BigDecimal.ONE;
        if (instancias.isPvpConIva()) {
            valorBaseProducto = valorBaseProducto.add(porcentajeIva);
        }

        if (instancias.isPvpConImpoconsumo()) {
            valorBaseProducto = valorBaseProducto.add(porcentajeImpoconsumo);
        }

        valorBaseProducto = subtotal.divide(valorBaseProducto, 2);

        if (instancias.isPvpConIva()) {
            totalIva = valorBaseProducto.multiply(porcentajeIva);
            subtotal = subtotal.subtract(totalIva);
        } else {
            totalIva = subtotal.multiply(porcentajeIva);
        }

        if (instancias.isPvpConImpoconsumo()) {
            totalImpoconsumo = valorBaseProducto.multiply(porcentajeImpoconsumo);
            subtotal = subtotal.subtract(totalImpoconsumo);
        } else {
            totalImpoconsumo = subtotal.multiply(porcentajeImpoconsumo);
        }

        subtotal = subtotal.subtract(descuento);
        total = subtotal.add(totalIva).add(totalImpoconsumo);

        if (rdTipoNormal.isSelected()) {
            if (!copago.equals(big.getBigDecimal("0"))) {
                total = subtotal.subtract(copago);
            }
        } else {
            if (!copago.equals(big.getBigDecimal("0"))) {
                total = copago;
                valorUnitario = big.getBigDecimal("0");
                subtotal = big.getBigDecimal("0");
            }
        }

        tblProductos.setValueAt(big.setMoneda(subtotal), fila, 4);
        tblProductos.setValueAt(big.setMoneda(totalIva), fila, 33);
        tblProductos.setValueAt(big.setMoneda(totalImpoconsumo), fila, 8);
        tblProductos.setValueAt(big.setMoneda(total), fila, 9);
        tblProductos.setValueAt(big.setNumero(porcentaje2), fila, 5);
        tblProductos.setValueAt(big.setMonedaExacta(descuento), fila, 6);
        tblProductos.setValueAt(big.setMoneda(copago), fila, 17);

        switch (tblProductos.getValueAt(fila, 12).toString()) {
            case "1":
                tblProductos.setValueAt(cantidad, fila, 13);
                break;
            case "2":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad2())), fila, 13);
                break;
            case "3":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad3())), fila, 13);
                break;
            case "4":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad4())), fila, 13);
                break;
            case "5":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad5())), fila, 13);
                break;
            case "6":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad6())), fila, 13);
                break;
            case "7":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad7())), fila, 13);
                break;
            case "8":
                tblProductos.setValueAt(cantidad.multiply(big.getBigDecimal(nodo.getCantidad8())), fila, 13);
                break;
        }

        tblProductos.setValueAt(cantidad, fila, 3);

        BigDecimal costo = BigDecimal.ZERO;
        try {
            UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(nodo.getIdSistema());
            costo = ultimoPonderado.getNuevoPonderado();
        } catch (SQLException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            ControladorAlertas.bigAlert("No se pudo consultar el último ponderado del producto");
        }

        BigDecimal utilidad, valorUnidad;
        valorUnidad = big.getMoneda(tblProductos.getValueAt(fila, 4).toString()).divide(big.getBigDecimal(tblProductos.getValueAt(fila, 13)), 2, RoundingMode.HALF_UP);

        utilidad = valorUnidad.subtract(costo);
        utilidad = utilidad.multiply(big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", ".")));
        tblProductos.setValueAt(big.setMoneda(utilidad), fila, 14);

        if (nodo.getIva().equals("0") || nodo.getIva().equals(".00")) {
            compra = costo.multiply(big.getBigDecimal(nodo.getIvaC())).divide(new BigDecimal("100")).add(big.getBigDecimal(costo));
        } else {
            compra = costo;
        }

        utilidadMin = (compra.multiply(big.getBigDecimal(nodo.getMinima())).divide(new BigDecimal("100"))).add(compra).setScale(0, BigDecimal.ROUND_HALF_DOWN);
        utilidadMax = (compra.multiply(big.getBigDecimal(nodo.getMaxima())).divide(new BigDecimal("100"))).add(compra).setScale(0, BigDecimal.ROUND_HALF_DOWN);

        if (valorUnitario.compareTo(utilidadMin) == -1) {
            tblProductos.setValueAt("ERROR1", fila, 15);
        } else if (valorUnitario.compareTo(utilidadMax) == 1) {
            tblProductos.setValueAt("ERROR2", fila, 15);
        } else {
            tblProductos.setValueAt("OK", fila, 15);
        }

        cargarTotales();
    }

    private ModeloFacturacionElectronica crearModeloFacturacionEletronica(String factura, String factura2, ModeloContacto datosCliente) {

        ModeloFacturacionElectronica modeloFacturacionElectronica = new ModeloFacturacionElectronica();
        modeloFacturacionElectronica.setDsPrefijo(obtenerPrefijoFactura());
        modeloFacturacionElectronica.setDsNumeroFactura(factura.replace("FACT-", ""));
        modeloFacturacionElectronica.setDsVendedor(cmbVendedor.getSelectedItem().toString());
        modeloFacturacionElectronica.setFechaEmision(metodos.fecha4(metodosGenerales.fecha()) + " " + metodosGenerales.fechaHora().split(" ")[1]);
        modeloFacturacionElectronica.setFechaVencimiento(metodos.fecha4(txtVencimiento.getText()));
        modeloFacturacionElectronica.setEmailAdquiriente(datosCliente.getEmail());
        modeloFacturacionElectronica.setTipoIdentificacionAdquiriente(enumTipoIdentificacion.obtenerTipoIdentificacion(datosCliente.getTipo()));
        modeloFacturacionElectronica.setIdentificacionAdquiriente(datosCliente.getId());
        modeloFacturacionElectronica.setDigitoVerificacionAdquiriente(datosCliente.getId().split("-")[1]);
        modeloFacturacionElectronica.setCodigoPostalAdquirente(datosCliente.getCodigoPostal());
        modeloFacturacionElectronica.setTipoPersonaAdquiriente(enumTipoPersona.obtenerTipoPersona(datosCliente.getNaturaleza()));

        if (datosCliente.getTipo().equals(enumTipoIdentificacion.TipoIdentificacion.NIT.getValue())) {
            modeloFacturacionElectronica.setNombresAdquiriente(datosCliente.getNombre());
            modeloFacturacionElectronica.setPrimerApellido(datosCliente.getNombre());
        } else {
            modeloFacturacionElectronica.setNombresAdquiriente(datosCliente.getpNombre());
            modeloFacturacionElectronica.setSegundoNombre(datosCliente.getsNombre());
            modeloFacturacionElectronica.setPrimerApellido(datosCliente.getpApellido());
            modeloFacturacionElectronica.setSegundoApellido(datosCliente.getsApellido());
        }

        modeloFacturacionElectronica.setDireccionAdquiriente(datosCliente.getDireccion());
        modeloFacturacionElectronica.setAdquirenteResponsable(datosCliente.isResponsableIva());
        modeloFacturacionElectronica.setRegimenAdquirente(obtenerRegimen(datosCliente.isResponsableIva()));
        modeloFacturacionElectronica.setTelefonoAdquiriente(datosCliente.getTelefono());

        Object[][] datosDepartamentoYCiudad = instancias.getSql().getCodigoLugar(datosCliente.getDepartamento(), datosCliente.getCiudad());
        if (null != datosDepartamentoYCiudad[0][1]) {
            String cdDaneCiudad = datosDepartamentoYCiudad[0][1].toString();
            modeloFacturacionElectronica.setCdDaneCiudad(cdDaneCiudad.substring(2, cdDaneCiudad.length()));
            modeloFacturacionElectronica.setDsNombreCiudad(datosCliente.getCiudad());
            modeloFacturacionElectronica.setCdDaneDepartamento(datosDepartamentoYCiudad[0][0].toString());
            modeloFacturacionElectronica.setDsNombreDepartamento(datosCliente.getDepartamento());
            modeloFacturacionElectronica.setCdIsoPais("CO");
            modeloFacturacionElectronica.setDsNombrePais(datosCliente.getPais());
        } else {
            ControladorAlertas.alertFail("Revisar la ciudad y departamento del cliente");
            return null;
        }

        modeloFacturacionElectronica.setSnDistribucionFisica("N");

        BigDecimal total = big.getMoneda(txtTotal.getText().replace("Total: ", "")).add(instancias.getTotalPropina());
        modeloFacturacionElectronica.setValorNeto(total);
        modeloFacturacionElectronica.setDsObservacion(txtObservaciones.getText());
        modeloFacturacionElectronica.setTipoDocumentoElectronico("VENTA");

        BigDecimal subtotal = big.getMoneda(txtSubTotal.getText()).add(instancias.getTotalPropina());
        BigDecimal porcentajeIva = big.getMoneda(txtTotalIva.getText()).divide(subtotal, 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setPorcentajeIva(porcentajeIva);

        BigDecimal porcentajeConsumo = big.getMoneda(txtTotalImpoconsumo.getText()).divide(subtotal, 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setPorcentajeConsumo(porcentajeConsumo);

        modeloFacturacionElectronica.setDsPorcentajeReteFuente(obtenerPorcentajeRetencionFuente());
        modeloFacturacionElectronica.setDsRetencionFuente(obtenerRetencionFuente(subtotal));
        modeloFacturacionElectronica.setDsPorcentajeReteIva(chkReteIva.isSelected() ? big.getBigDecimal(15) : BigDecimal.ZERO);
        modeloFacturacionElectronica.setDsRetencionIva(big.getMoneda(txtRiva.getText()));

        BigDecimal porcentajeDescuento = big.getMoneda(txtTotalDescuentos.getText()).divide(subtotal, 2, BigDecimal.ROUND_HALF_UP).multiply(big.getBigDecimal(100));
        modeloFacturacionElectronica.setDsPorcentajeDescuento(porcentajeDescuento);
        modeloFacturacionElectronica.setDsDescuento(big.getMoneda(txtTotalDescuentos.getText()));

        BigDecimal valorBaseImponible = BigDecimal.ZERO;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IVA).toString()) > 0
                    || Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString()) > 0) {
                valorBaseImponible = valorBaseImponible.add(big.getMoneda(tblProductos.getValueAt(i, Constantes.COLUMNA_SUBTOTAL).toString()));
            }
        }

        modeloFacturacionElectronica.setValorBaseImponible(valorBaseImponible);
        modeloFacturacionElectronica.setValorBrutoMasTributos(total.add(big.getMoneda(txtTotalDescuentos.getText())));
        modeloFacturacionElectronica.setDescuentoTotal(big.getMoneda(txtTotalDescuentos.getText()));
        modeloFacturacionElectronica.setCargoTotal(BigDecimal.ZERO);
        modeloFacturacionElectronica.setAnticipoTotal(BigDecimal.ZERO);
        modeloFacturacionElectronica.setValorTotalImpuestoConsumo(big.getMoneda(txtTotalImpoconsumo.getText()));
        modeloFacturacionElectronica.setMoneda("COP");
        modeloFacturacionElectronica.setValorBruto(subtotal.add(big.getMoneda(txtTotalDescuentos.getText())));
        modeloFacturacionElectronica.setValorIva(big.getMoneda(txtTotalIva.getText()));

        String tipoOperacion = rdPos.isSelected() ? "POS" : "ESTANDAR";
        int tipoPlantilla = rdPos.isSelected() ? 2 : 1;
        modeloFacturacionElectronica.setTipoOperacion(tipoOperacion);
        modeloFacturacionElectronica.setCdTipoPlantilla(tipoPlantilla);

        modeloFacturacionElectronica.setDsResolucionDian(obtenerResolucionFactura());
        modeloFacturacionElectronica.setVersionDian("2");
        modeloFacturacionElectronica.setResponsabilidadesFiscales(DatosMaestra.getResponsabilidadesFiscales());

        ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosFactura(modeloFacturacionElectronica.getDescuentoTotal());
        modeloFacturacionElectronica.setDescuentosFactura(resultadosDescuentos);

        Object[] informacionDePagos = obtenerInformacionDePagos(factura2);
        modeloFacturacionElectronica.setFormaPago(informacionDePagos[0].toString());
        modeloFacturacionElectronica.setMedioPago(informacionDePagos[1].toString());
        modeloFacturacionElectronica.setFechaVencimientoPago(informacionDePagos[2].toString());
        modeloFacturacionElectronica.setIdPago(informacionDePagos[3].toString());

        if (rdPos.isSelected()) {
            String codigoCaja = Constantes.leerArchivoTerminal();
            Terminal datosTerminal = daoInicioSesion.obtenerTerminal(codigoCaja);
            modeloFacturacionElectronica.setPlacaCaja(codigoCaja);
            modeloFacturacionElectronica.setUbicacionCaja(datosTerminal.getCodigo1());
            modeloFacturacionElectronica.setCajero(instancias.getUsuarioLog().getNombre());
            modeloFacturacionElectronica.setTipoCaja("Sitio");
        }

        ModeloDetalleImpuestos resultadosImpuestos = obtenerImpuestosFacturas(obtenerPorcentajeRetencionFuente());
        modeloFacturacionElectronica.setImpuestosFactura(resultadosImpuestos);

        ModeloDetalleProductos[] detalleProductos = obtenerDetalleProductos(factura);
        modeloFacturacionElectronica.setDetalleProductos(detalleProductos);

        return modeloFacturacionElectronica;
    }

    private ModeloDetalleProductos[] obtenerDetalleProductos(String factura) {

        int cantidadTotal = instancias.getTotalPropina().compareTo(BigDecimal.ZERO) > 0 ? tblProductos.getRowCount() + 1 : tblProductos.getRowCount();
        ModeloDetalleProductos[] detalladoProductos = new ModeloDetalleProductos[cantidadTotal];

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
            modeloIndividual.setNumeroFactura(factura);
            modeloIndividual.setCodigoArticulo(tblProductos.getValueAt(i, Constantes.COLUMNA_CODIGO_PRODUCTO).toString());
            modeloIndividual.setEstandarProducto("UNSPSC");
            modeloIndividual.setDescripcionArticulo(tblProductos.getValueAt(i, Constantes.COLUMNA_DESCRIPCION_PRODUCTO).toString());
            modeloIndividual.setPorcentajeIva(big.getBigDecimal(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IVA).toString()));
            modeloIndividual.setPorcentajeConsumo(big.getBigDecimal(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString()));
            modeloIndividual.setCantidad(tblProductos.getValueAt(i, Constantes.COLUMNA_CANTIDAD).toString());
            modeloIndividual.setPrecioUnitario(big.getMoneda(tblProductos.getValueAt(i, Constantes.COLUMNA_VALOR_PRODUCTO).toString()));
            modeloIndividual.setValorTotalArticulo(big.getMoneda(tblProductos.getValueAt(i, 9).toString()));

            BigDecimal totalIva = big.getMoneda(tblProductos.getValueAt(i, Constantes.COLUMNA_VALOR_IVA).toString());
            BigDecimal totalImpoconsumo = big.getMoneda(tblProductos.getValueAt(i, 8).toString());
            modeloIndividual.setValorIva(totalIva);
            modeloIndividual.setValorConsumo(totalImpoconsumo);
            modeloIndividual.setUnidadMedida(tblProductos.getValueAt(i, Constantes.COLUMNA_UNIDAD_MEDIDA).toString());

            BigDecimal valorTotalBruto = big.getMoneda(tblProductos.getValueAt(i, 2).toString()).multiply(big.getBigDecimal(tblProductos.getValueAt(i, 3).toString()));
            if (instancias.isPvpConIva()) {
                valorTotalBruto = valorTotalBruto.subtract(totalIva).subtract(totalImpoconsumo);
            }

            modeloIndividual.setValorTotalBruto(valorTotalBruto);
            modeloIndividual.setUnidadesEmpaque(big.getBigDecimal(tblProductos.getValueAt(i, 13)).divide(big.getBigDecimal(tblProductos.getValueAt(i, 3)), 2, RoundingMode.HALF_UP));
            modeloIndividual.setValorTotalImpuestosRetenciones(big.getMoneda(tblProductos.getValueAt(i, 8).toString()).add(big.getMoneda(tblProductos.getValueAt(i, 33).toString())));
            modeloIndividual.setCodigoVendedor(instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString()));
            modeloIndividual.setObservacionDetalle(tblProductos.getValueAt(i, 27).toString());

            ModeloDescuentos[] resultadosDescuentos = obtenerDescuentosProducto(modeloIndividual, i);
            modeloIndividual.setDescuentoProducto(resultadosDescuentos);

            Object[][] impuestosProducto = obtenerImpuestosPorProducto(modeloIndividual, i);
            modeloIndividual.setImpuestosProducto(impuestosProducto);

            detalladoProductos[i] = modeloIndividual;
        }

        if (instancias.getTotalPropina().compareTo(BigDecimal.ZERO) > 0) {
            detalladoProductos[tblProductos.getRowCount()] = obtenerProductoPropina(factura);
        }

        return detalladoProductos;
    }

    private ModeloDetalleProductos obtenerProductoPropina(String factura) {
        ModeloDetalleProductos modeloIndividual = new ModeloDetalleProductos();
        modeloIndividual.setNumeroFactura(factura);
        modeloIndividual.setCodigoArticulo("PROPINA");
        modeloIndividual.setEstandarProducto("UNSPSC");
        modeloIndividual.setDescripcionArticulo("PROPINA");
        modeloIndividual.setPorcentajeIva(BigDecimal.ZERO);
        modeloIndividual.setPorcentajeConsumo(BigDecimal.ZERO);
        modeloIndividual.setCantidad("1");
        modeloIndividual.setPrecioUnitario(instancias.getTotalPropina());
        modeloIndividual.setValorTotalArticulo(instancias.getTotalPropina());
        modeloIndividual.setValorIva(BigDecimal.ZERO);
        modeloIndividual.setValorConsumo(BigDecimal.ZERO);
        modeloIndividual.setUnidadMedida("UNIDAD");
        modeloIndividual.setValorTotalBruto(instancias.getTotalPropina());
        modeloIndividual.setValorTotalImpuestosRetenciones(instancias.getTotalPropina());
        modeloIndividual.setCodigoVendedor(instancias.getSql().getIdEmpleado(cmbVendedor.getSelectedItem().toString()));
        modeloIndividual.setObservacionDetalle("");

        ModeloDescuentos[] descuentoProducto = new ModeloDescuentos[0];
        modeloIndividual.setDescuentoProducto(descuentoProducto);

        Object[][] impuestosProducto = new Object[0][4];
        modeloIndividual.setImpuestosProducto(impuestosProducto);

        return modeloIndividual;
    }

    private ModeloDescuentos[] obtenerDescuentosProducto(ModeloDetalleProductos informacionProducto, int filaProducto) {

        String codigoDescuento = "", descripcionDescuento = "";
        if (!"".equals(tblProductos.getValueAt(filaProducto, 31).toString())) {
            codigoDescuento = tblProductos.getValueAt(filaProducto, 31).toString().split("///")[0];
            try {
                descripcionDescuento = tblProductos.getValueAt(filaProducto, 31).toString().split("///")[1];
            } catch (Exception e) {
            }
        }

        BigDecimal valorBase = big.getMoneda(tblProductos.getValueAt(filaProducto, 4).toString());
        BigDecimal descuentoProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 6).toString());
        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (big.getBigDecimal(tblProductos.getValueAt(filaProducto, 5)).compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(formatoDosDecimales.format(descuentoProducto).replace(",", "."));
            modeloDescuento.setValorBase(formatoDosDecimales.format(valorBase).replace(",", "."));

            int porcentajeDescuento = Integer.parseInt(tblProductos.getValueAt(filaProducto, 5).toString());
            modeloDescuento.setPorcentaje(formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private ModeloDescuentos[] obtenerDescuentosFactura(BigDecimal descuentosDocumento) {

        String codigoDescuento = "", descripcionDescuento = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!"Sin-Permiso".equals(tblProductos.getValueAt(i, 31).toString())) {
                codigoDescuento = tblProductos.getValueAt(i, 31).toString().split("///")[0];
                try {
                    descripcionDescuento = tblProductos.getValueAt(i, 31).toString().split("///")[1];
                } catch (Exception e) {
                }
            }
        }

        BigDecimal subtotal = big.getMoneda(txtSubTotal.getText()).add(instancias.getTotalPropina());
        BigDecimal valorBase = big.getMoneda(txtTotalDescuentos.getText()).add(subtotal);
        ModeloDescuentos[] informacionDescuentos = new ModeloDescuentos[1];

        if (descuentosDocumento.compareTo(BigDecimal.ZERO) > 0) {
            ModeloDescuentos modeloDescuento = new ModeloDescuentos();
            modeloDescuento.setTipo(false);
            modeloDescuento.setRazonDescuento(descripcionDescuento);
            modeloDescuento.setValorDescuento(formatoDosDecimales.format(descuentosDocumento).replace(",", "."));
            modeloDescuento.setValorBase(formatoDosDecimales.format(valorBase).replace(",", "."));

            BigDecimal porcentajeDescuento = descuentosDocumento.multiply(big.getBigDecimal(100)).divide(valorBase, 0, RoundingMode.HALF_UP);
            modeloDescuento.setPorcentaje(formatoDosDecimales.format(porcentajeDescuento).replace(",", "."));
            modeloDescuento.setCodigoDescuento(codigoDescuento);
            informacionDescuentos[0] = modeloDescuento;
        }

        return informacionDescuentos;
    }

    private Object[][] obtenerImpuestosPorProducto(ModeloDetalleProductos detalleProducto, int filaProducto) {

        int secuencia = 0;
        Object[][] informacionImpuestosFactura = new Object[2][4];
        BigDecimal baseProducto = big.getMoneda(tblProductos.getValueAt(filaProducto, 4).toString());

        if (detalleProducto.getValorIva().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorIva();
            informacionImpuestosFactura[secuencia][2] = formatoDosDecimales.format(detalleProducto.getPorcentajeIva()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "IVA";
            secuencia++;
        }

        if (detalleProducto.getValorConsumo().compareTo(BigDecimal.ZERO) > 0) {
            informacionImpuestosFactura[secuencia][0] = baseProducto;
            informacionImpuestosFactura[secuencia][1] = detalleProducto.getValorConsumo();
            informacionImpuestosFactura[secuencia][2] = formatoDosDecimales.format(detalleProducto.getPorcentajeConsumo()).replace(",", ".");
            informacionImpuestosFactura[secuencia][3] = "INC";
            secuencia++;
        }

        return informacionImpuestosFactura;
    }

    private ModeloDetalleImpuestos obtenerImpuestosFacturas(BigDecimal dsPorcentajeReteFuente) {

        List<Integer> ivas = new ArrayList<>();
        List<Integer> impoconsumos = new ArrayList<>();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            int porcentajeIva = Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IVA).toString());
            int porcentajeConsumo = Integer.parseInt(tblProductos.getValueAt(i, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString());

            if (porcentajeIva != 0 && !ivas.contains(porcentajeIva)) {
                ivas.add(porcentajeIva);
            }

            if (porcentajeConsumo != 0 && !impoconsumos.contains(porcentajeConsumo)) {
                impoconsumos.add(porcentajeConsumo);
            }
        }

        Object[][] informacionImpuestoIva = new Object[ivas.size()][4];
        Object[][] informacionImpuestoImpoconsumo = new Object[impoconsumos.size()][4];
        Object[][] informacionReteIva = new Object[1][4];
        Object[][] informacionReteFuente = new Object[1][4];

        for (int i = 0; i < ivas.size(); i++) {
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal impuesto = BigDecimal.ZERO;
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                if (Integer.parseInt(tblProductos.getValueAt(j, Constantes.COLUMNA_PORCENTAJE_IVA).toString()) == ivas.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_SUBTOTAL).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_VALOR_IVA).toString()));
                }
            }

            informacionImpuestoIva[i][0] = subtotal;
            informacionImpuestoIva[i][1] = impuesto;
            informacionImpuestoIva[i][2] = formatoDosDecimales.format(ivas.get(i)).replace(",", ".");
            informacionImpuestoIva[i][3] = "IVA";
        }

        for (int i = 0; i < impoconsumos.size(); i++) {
            BigDecimal subtotal = BigDecimal.ZERO;
            BigDecimal impuesto = BigDecimal.ZERO;
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                if (Integer.parseInt(tblProductos.getValueAt(j, Constantes.COLUMNA_PORCENTAJE_IMPOCONSUMO).toString()) == impoconsumos.get(i)) {
                    subtotal = subtotal.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_SUBTOTAL).toString()));
                    impuesto = impuesto.add(big.getMoneda(tblProductos.getValueAt(j, Constantes.COLUMNA_VALOR_IMPOCONSUMO).toString()));
                }
            }

            informacionImpuestoImpoconsumo[i][0] = subtotal;
            informacionImpuestoImpoconsumo[i][1] = impuesto;
            informacionImpuestoImpoconsumo[i][2] = formatoDosDecimales.format(impoconsumos.get(i)).replace(",", ".");
            informacionImpuestoImpoconsumo[i][3] = "INC";
        }

        if (big.getMoneda(txtRiva.getText()).compareTo(BigDecimal.ZERO) > 0) {
            informacionReteIva[0][0] = big.getMoneda(txtTotalIva.getText());
            informacionReteIva[0][1] = big.getMoneda(txtRiva.getText());
            informacionReteIva[0][2] = "15.00";
            informacionReteIva[0][3] = "RETE_IVA";
        }

        if (big.getMoneda(txtRtf.getText()).compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal subtotal = big.getMoneda(txtSubTotal.getText()).add(instancias.getTotalPropina());
            informacionReteFuente[0][0] = subtotal;
            informacionReteFuente[0][1] = obtenerRetencionFuente(subtotal);

            String porcentajeReteFuente = formatoDosDecimales.format(dsPorcentajeReteFuente).replace(",", ".");
            if (dsPorcentajeReteFuente.toString().startsWith("0")) {
                porcentajeReteFuente = "0" + formatoDosDecimales.format(dsPorcentajeReteFuente).replace(",", ".");
            }
            informacionReteFuente[0][2] = porcentajeReteFuente;
            informacionReteFuente[0][3] = "RETE_FUENTE";
        }

        ModeloDetalleImpuestos detalleImpuestos = new ModeloDetalleImpuestos();
        detalleImpuestos.setImpuestosIvas(informacionImpuestoIva);
        detalleImpuestos.setImpuestosImpoconsumo(informacionImpuestoImpoconsumo);
        detalleImpuestos.setImpuestosReteIva(informacionReteIva);
        detalleImpuestos.setImpuestosReteFuente(informacionReteFuente);
        return detalleImpuestos;
    }

    private Object[] obtenerInformacionDePagos(String numeroFactura) {
        String fechaVencimiento = metodos.fecha4(txtVencimiento.getText());
        String minutosFactura = metodosGenerales.hora().split(":")[0];
        String segundosFactura = metodosGenerales.hora().split(":")[1];

        if (minutosFactura.length() == 1) {
            minutosFactura = "0" + minutosFactura;
        }

        if (segundosFactura.length() == 1) {
            segundosFactura = "0" + segundosFactura;
        }

        Object[] informacionMetodosPagos = new Object[4];
        if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
            informacionMetodosPagos[0] = "CREDITO";
            informacionMetodosPagos[1] = "CREDITO_ACH";
            informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
            informacionMetodosPagos[3] = "";
        } else {
            if (instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) > 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "EFECTIVO";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "EFECTIVO");
            } else if (instancias.getChequeDevuelta().compareTo(BigDecimal.ZERO) > 0 && instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) == 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "CHEQUE";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "CHEQUE");
            } else if (instancias.getTarjetaCredito().compareTo(BigDecimal.ZERO) > 0 && instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) == 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "TARJETA_CREDITO";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "TARJETA_CREDITO");
            } else if (instancias.getTarjetaDevuelta().compareTo(BigDecimal.ZERO) > 0 && instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) == 0) {
                informacionMetodosPagos[0] = "CONTADO";
                informacionMetodosPagos[1] = "TARJETA_DEBITO";
                informacionMetodosPagos[2] = fechaVencimiento + " " + minutosFactura + ":" + segundosFactura + ":00";
                informacionMetodosPagos[3] = instancias.getSql().getIDPago(numeroFactura, "TARJETA_DEBITO");
            }
        }

        return informacionMetodosPagos;
    }

    private BigDecimal obtenerPorcentajeRetencionFuente() {
        BigDecimal porcentajeRetencion = BigDecimal.ZERO;
        if (cmbRtf.getSelectedIndex() > 0) {
            porcentajeRetencion = big.getBigDecimal(cmbRtf.getSelectedItem());
        }

        return porcentajeRetencion;
    }

    private BigDecimal obtenerRetencionFuente(BigDecimal subtotal) {
        BigDecimal retencionFuente = BigDecimal.ZERO;
        if (cmbRtf.getSelectedIndex() > 0) {
            retencionFuente = subtotal.multiply(big.getBigDecimal(cmbRtf.getSelectedItem())).divide(big.getBigDecimal("100"), 2, RoundingMode.HALF_UP);
        }

        return retencionFuente;
    }

    private String obtenerRegimen(boolean esResponsableIva) {
        String regimenAdquirente = "SIMPLE";
        if (esResponsableIva) {
            regimenAdquirente = "ORDINARIO";
        }

        return regimenAdquirente;
    }

    private String obtenerPrefijoFactura() {
        int filaSeleccionada = 0;
        String prefijoFactura = "";

        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                filaSeleccionada = i;
                break;
            }
        }

        if (null != tblComprobantes.getValueAt(filaSeleccionada, 8)) {
            prefijoFactura = tblComprobantes.getValueAt(filaSeleccionada, 8).toString();
        }

        return prefijoFactura;
    }

    private String obtenerResolucionFactura() {
        int filaSeleccionada = 0;
        String resolucionFactura = "";

        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                filaSeleccionada = i;
                break;
            }
        }

        if (null != tblComprobantes.getValueAt(filaSeleccionada, 3)) {
            resolucionFactura = tblComprobantes.getValueAt(filaSeleccionada, 3).toString();
        }

        return resolucionFactura;
    }

    private void abrirModalDescuentosProducto(int filaSelecciona) {
        txtCodigoProducto.requestFocus();

        String descuento = "";
        String porcentajeDescuento = "";
        String tipoOpcion = tblProductos.getValueAt(filaSelecciona, 31).toString();

        BigDecimal valorProducto = big.getMoneda(String.valueOf(tblProductos.getValueAt(filaSelecciona, 2)));
        BigDecimal cantidad = big.getBigDecimal(tblProductos.getValueAt(filaSelecciona, 3).toString().replace(".", "").replace(",", "."));
        BigDecimal subtotal = valorProducto.multiply(cantidad);

        if (instancias.getDescuento().equals("peso")) {
            descuento = tblProductos.getValueAt(filaSelecciona, 6).toString();
        } else {
            porcentajeDescuento = tblProductos.getValueAt(filaSelecciona, 5).toString();
        }

        dlgTipoDescuento tipoDescuento = new dlgTipoDescuento(null, porcentajeDescuento, descuento, filaSelecciona, tipoOpcion, subtotal, this.tipoProceso);
        tipoDescuento.setVisible(true);
    }

    private List<MovimientoInventario> generarListadoProductos(String tablaUtilizada) throws SQLException {

        List<MovimientoInventario> movimientos = new ArrayList<>();

        ServicioDiscosteo servicioDiscosteo = new ServicioDiscosteo(new CargadorProducto() {
            @Override
            public ndProducto cargar(String codigo, String tabla) {
                return instancias.getSql().getDatosProducto(codigo, tabla);
            }
        });

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 13).toString());
            String idDetalleProducto = obtenerValorTabla(i, 29);

            MovimientoInventario inventario = new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, idDetalleProducto);
            movimientos.add(inventario);

            String preparacion = obtenerValorTabla(i, 21);
            movimientos.addAll(servicioDiscosteo.explotarSiEsDiscosteo(producto, preparacion, tablaUtilizada, cantidad));
        }

        return movimientos;
    }

    private String obtenerValorTabla(int row, int col) {
        Object value = tblProductos.getValueAt(row, col);
        return value != null ? value.toString() : "";
    }

    private ResultadoValidacionInventario validarInventarioProductos(String baseUtilizada) {
        List<FilaProductoTabla> filas = extraerFilasDeTabla();
        ServicioValidacionFactura servicio = new ServicioValidacionFactura(new CargadorProducto() {
            @Override
            public ndProducto cargar(String codigo, String tabla) {
                return instancias.getSql().getDatosProducto(codigo, tabla);
            }
        });
        return servicio.validar(filas, baseUtilizada);
    }

    private ResultadoValidacionInventario validarInventarioProductos(String baseUtilizada, List<LineaProducto> originales) {
        List<FilaProductoTabla> filas = extraerFilasDeTabla();
        List<FilaProductoTabla> filasAValidar = originales.isEmpty()
                ? filas
                : calcularFilasNetasModificacion(filas, originales);
        ServicioValidacionFactura servicio = new ServicioValidacionFactura(new CargadorProducto() {
            @Override
            public ndProducto cargar(String codigo, String tabla) {
                return instancias.getSql().getDatosProducto(codigo, tabla);
            }
        });
        return servicio.validar(filasAValidar, baseUtilizada);
    }

    private List<FilaProductoTabla> calcularFilasNetasModificacion(List<FilaProductoTabla> filas, List<LineaProducto> originales) {
        List<FilaProductoTabla> filasNetas = new ArrayList<>();
        for (FilaProductoTabla fila : filas) {
            BigDecimal cantOriginal = BigDecimal.ZERO;
            for (LineaProducto linea : originales) {
                if (fila.getIdProducto().equals(linea.getCodigo())) {
                    cantOriginal = cantOriginal.add(Utilidades.convertirBigDecimal(linea.getCantidad()));
                }
            }
            BigDecimal incremento = fila.getCantidad().subtract(cantOriginal);
            if (incremento.compareTo(BigDecimal.ZERO) > 0) {
                filasNetas.add(new FilaProductoTabla(
                        fila.getIdProducto(),
                        fila.getPreparacion(),
                        incremento,
                        fila.getDescripcion()));
            }
        }
        return filasNetas;
    }

    private BigDecimal getCantidadOriginalProducto(String idProducto) {
        BigDecimal total = BigDecimal.ZERO;
        for (LineaProducto linea : lineasOriginales) {
            if (idProducto.equals(linea.getCodigo())) {
                total = total.add(Utilidades.convertirBigDecimal(linea.getCantidad()));
            }
        }
        return total;
    }

    private void recalcularInventarioTabla() {
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            Object valorActual = tblProductos.getValueAt(i, 38);
            if (valorActual == null || "N/A".equals(valorActual.toString())) {
                continue;
            }
            String idProducto = obtenerValorTabla(i, 32);
            BigDecimal stockActual = big.getMoneda(valorActual.toString());
            BigDecimal cantNueva = big.getBigDecimal(tblProductos.getValueAt(i, 3).toString().replace(",", "."));
            BigDecimal cantOriginal = getCantidadOriginalProducto(idProducto);
            BigDecimal netIncrement = cantNueva.subtract(cantOriginal);
            tblProductos.setValueAt(Utilidades.formatearCantidadVista(stockActual.subtract(netIncrement)), i, 39);
        }
    }

    private List<FilaProductoTabla> extraerFilasDeTabla() {
        List<FilaProductoTabla> filas = new ArrayList<>();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            filas.add(new FilaProductoTabla(
                    obtenerValorTabla(i, 32),
                    obtenerValorTabla(i, 21),
                    Utilidades.convertirBigDecimal(tblProductos.getValueAt(i, 13).toString()),
                    obtenerValorTabla(i, 1)));
        }
        return filas;
    }

    private InformacionAdicional construirInformacionAdicional() {
        /*boolean vieneDesdeUnPedido = (ndPedido != null && ndPedido.getIdFactura() != null);
         boolean vieneDesdeUnSepare = (ndSepare != null && ndSepare.getIdFactura() != null);
         boolean vieneDesdeUnOrdenServicio = nodoOrdenServicio != null;
         return new InformacionAdicional(vieneDesdeUnPedido, vieneDesdeUnSepare, vieneDesdeUnOrdenServicio);*/
        return new InformacionAdicional(false, false, false);
    }

    private void registrarMetodosDePago(VistaMetodoPagos devuelta) {

        if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) || saltarPasosFactura) {
            devuelta = new VistaMetodoPagos(instancias.getMenu(), true, big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                    instancias, ID_CLIENTE_CARGADO, big.getMoneda(txtSubTotal.getText()));
        }

        //MODULO DE DEVUELTA
        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor()) || this.tipoProceso.equals(TipoDocumento.MESA.getValor())) {
            if (devuelta == null) {
                if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                    devuelta = new VistaMetodoPagos(null, true, big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                            instancias, ID_CLIENTE_CARGADO, big.getMoneda(txtSubTotal.getText()));
                    devuelta.show();
                } else {
                    funcionalidadVentas.reiniciarPagos(instancias);
                }
            } else {
                funcionalidadVentas.reiniciarPagos(instancias);
                if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                    instancias.setEfectivoDevuelta(big.getMoneda(txtTotal.getText().replace("Total: ", "")));
                }
            }
        }
    }

    private boolean validarCancelacionFactura() {
        if (instancias.getCancelarFactura()) {
            System.out.println("DEVOLVIO LA FACTURA");
            funcionalidadVentas.reiniciarPagos(instancias);
            borrarAdiciones();
            return true;
        }

        return false;
    }

    private int obtenerTurno() {
        if (DatosMaestra.isTurnoActivo() && instancias.getConfiguraciones().isRestaurante()) {
            String turno = instancias.getSql().getTurno();
            return turno != null ? Integer.parseInt(turno.trim()) : 0;
        }

        if (txtTurno.isVisible()) {
            return Integer.parseInt(txtTurno.getText().trim());
        }

        return 0;
    }

    private boolean generarFacturacionElectronica(String tipoComprobante, String factura, String factura2) {
        boolean facturaElectronicaExitosa = false;
        ModeloFacturacionElectronica modeloFacturacionElectronica = crearModeloFacturacionEletronica(factura, factura2, DATOS_CLIENTE_CARGADO);

        try {
            facturaElectronicaExitosa = consumidorFacturacionElectronica.generarFacturacionElectronica(modeloFacturacionElectronica, false, false,
                    false, rdPos.isSelected());
        } catch (Exception ex) {
            System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
        }

        return facturaElectronicaExitosa;
    }

    private void aumentarTurno() {
        int nuevoTurno = funcionalidadVentas.incrementarTurno(instancias);
        txtTurno.setText(String.valueOf(nuevoTurno));
        instancias.getPedido().actualizarVistaConsecutivo();
    }

    private void revertirInventarioOriginal() throws SQLException {
        if (lineasOriginales.isEmpty()) {
            return;
        }

        TipoDocumento tipoAnulacion = obtenerTipoAnulacion();
        if (tipoAnulacion == null) {
            return;
        }

        String prefijoGeneral = TipoDocumento.obtenerPrefijoGeneralPorValor(this.tipoProceso);
        String tabla = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
        funcionalidadVentas.procesarMovimientoInventario(
                tipoAnulacion,
                prefijoGeneral + "-" + lbNoFactura.getText(),
                tabla,
                instancias.getUsuario(),
                construirMovimientosDesdeLineas(lineasOriginales),
                new ArrayList<DetalleProducto>(),
                new InformacionAdicional(false, false, false));
    }

    private TipoDocumento obtenerTipoAnulacion() {
        if (conversorDocumentoAFactura != null) {
            return conversorDocumentoAFactura.obtenerTipoAnulacion(this.tipoProceso);
        }

        return null;
    }

    private List<MovimientoInventario> construirMovimientosDesdeLineas(List<LineaProducto> lineas) throws SQLException {
        String tabla = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
        ServicioDiscosteo servicioDiscosteo = new ServicioDiscosteo(new CargadorProducto() {
            @Override
            public ndProducto cargar(String codigo, String t) {
                return instancias.getSql().getDatosProducto(codigo, t);
            }
        });
        List<MovimientoInventario> movimientos = new ArrayList<>();
        for (LineaProducto linea : lineas) {
            ndProducto producto = instancias.getSql().getDatosProducto(linea.getCodigo(), tabla);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(linea.getCantidad());
            movimientos.add(new MovimientoInventario(producto, cantidad, BigDecimal.ZERO, linea.getIdProd()));
            movimientos.addAll(servicioDiscosteo.explotarSiEsDiscosteo(producto, linea.getPreparacion(), tabla, cantidad));
        }
        return movimientos;
    }

    private void actualizarInventarioDocumento(TipoDocumento tipoDocumento) {
        InformacionAdicional informacionAdicional = new InformacionAdicional(false, false, false);
        if (tipoDocumento == TipoDocumento.FACTURACION) {
            informacionAdicional = construirInformacionAdicional();
        }

        String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
        try {
            funcionalidadVentas.procesarMovimientoInventario(tipoDocumento, "", tablaUtilizada,
                    instancias.getUsuario(), generarListadoProductos(tablaUtilizada), new ArrayList<DetalleProducto>(), informacionAdicional);
        } catch (SQLException ex) {
            Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BigDecimal obtenerCostoPreparacion(int linea, String preparacion) {
        ServicioDiscosteo servicioDiscosteo = new ServicioDiscosteo(new CargadorProducto() {
            @Override
            public ndProducto cargar(String codigo, String tabla) {
                return instancias.getSql().getDatosProducto(codigo, tabla);
            }
        }, servicioActualizacionPonderado);

        BigDecimal costoPreparacion = BigDecimal.ZERO;

        try {
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();
            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(linea, 32).toString(), tablaUtilizada);
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(linea, 3).toString());

            if (producto != null) {
                costoPreparacion = servicioDiscosteo.calcularCostoPreparacion(producto, preparacion, tablaUtilizada, cantidad);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE,
                    "Error al calcular costo de preparación", ex);
        }

        return costoPreparacion;
    }

    private BigDecimal obtenerPonderado(int fila, BigDecimal costoPreparacion) {
        BigDecimal ponderado = BigDecimal.ZERO;

        if (costoPreparacion.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal cantidad = Utilidades.convertirBigDecimal(tblProductos.getValueAt(fila, 3).toString());
            ponderado = costoPreparacion.divide(cantidad, 4, RoundingMode.HALF_UP);
        } else {
            try {
                UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(tblProductos.getValueAt(fila, 32).toString());
                ponderado = ultimoPonderado.getNuevoPonderado();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                ControladorAlertas.bigAlert("No se pudo consultar el último ponderado del producto");
            }
        }

        return ponderado;
    }

    private boolean agregarMovimientoCotizacion(MovimientoDocumento movimiento) {

        String cotizacionesAsociadas = "";

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                Object[] vector = {movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getVendedor(), "", metodos.fechaConsulta(metodosGenerales.fecha()),
                    metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                    movimiento.getFactura().replace(movimiento.getPrefijoGeneral() + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                    big.getMoneda(movimiento.getPorcentajeReteFuente()),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
                    EstadosTipoDocumento.PENDIENTE.getNombre(), "", instancias.getDevuelta(), movimiento.getFactura2(), instancias.getResolucion(),
                    metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    movimiento.getCopago(), txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                    "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), ""
                };

                ndCotizacion nodo = metodos.llenarCotizacion(vector);

                if (!instancias.getSql().agregarCotizacion(nodo)) {
                    boolean noPuedaGuardar = false;

                    instancias.getSql().eliminarCotizacion(movimiento.getFactura());
                    while (!noPuedaGuardar) {
                        noPuedaGuardar = instancias.getSql().eliminarCotizacion(movimiento.getFactura());
                    }

                    metodos.msgError(null, "Error al guardar la cotización");
                    return false;
                }
            }
        }

        return true;
    }

    private boolean agregarMovimientoOrdenServicio(MovimientoDocumento movimiento) {
        if (!daoOrdenServicio.guardarVehiculo(construirVehiculo(movimiento.getFactura2()))) {
            metodos.msgError(null, "Hubo un problema al guardar la orden de servicio");
            return false;
        }

        daoOrdenServicio.guardarDetalle(construirDetalleOrden(movimiento.getFactura()));
        if (!daoOrdenServicio.guardarLineas(construirLineasOrden(movimiento), movimiento.getFactura2())) {
            metodos.msgError(null, "Error al guardar la orden de servicio");
            return false;
        }

        return true;
    }

    private ndOServicio construirVehiculo(String factura) {
        ndOServicio nodo = new ndOServicio();
        nodo.setId(factura);
        nodo.setPlaca(txtPlaca.getText());
        nodo.setTipo(txtTipoVehiculo.getText());
        nodo.setModelo(txtModelo.getText());
        nodo.setNumeroChasis(txtNumChasis.getText());
        nodo.setFechaCompra(metodos.fechaConsulta(metodosGenerales.fecha()));
        nodo.setMarca(txtMarca.getText());
        nodo.setKm(txtKm.getText());
        nodo.setNumeroMotor(txtMotor.getText());
        nodo.setColor(txtColor.getText());
        nodo.setProblema(txtProblema.getText());
        return nodo;
    }

    private List<ModeloDetalleOrdenServicio> construirDetalleOrden(String idOrden) {
        List<ModeloDetalleOrdenServicio> detalles = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < tblArticulos.getRowCount(); i++) {
            if ((Boolean) tblArticulos.getValueAt(i, 2)) {
                detalles.add(new ModeloDetalleOrdenServicio(
                        idOrden,
                        tblArticulos.getValueAt(i, 0).toString(),
                        tblArticulos.getValueAt(i, 1).toString(),
                        (Boolean) tblArticulos.getValueAt(i, 2),
                        tblArticulos.getValueAt(i, 3).toString(),
                        tblArticulos.getValueAt(i, 4).toString(),
                        tblArticulos.getValueAt(i, 5).toString(),
                        num++
                ));
            }
        }
        return detalles;
    }

    private List<ndOServicio1> construirLineasOrden(MovimientoDocumento movimiento) {
        List<ndOServicio1> lineas = new ArrayList<>();
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                Object[] vector = {movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getVendedor(), "", metodos.fechaConsulta(metodosGenerales.fecha()),
                    metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                    movimiento.getFactura().replace(movimiento.getPrefijoGeneral() + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                    big.getMoneda(movimiento.getPorcentajeReteFuente()),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
                    "PENDIENTE", "", instancias.getDevuelta(), movimiento.getFactura2(), instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    BigDecimal.ZERO, txtPlaca1.getText(), "", "", "", "", "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), ""
                };
                lineas.add(metodos.llenarOServicio1(vector));
            }
        }
        return lineas;
    }

    private boolean agregarMovimientoPedido(MovimientoDocumento movimiento) {
        String cotizacionesAsociadas = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                Object[] vector = {movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getVendedor(), "",
                    metodos.fechaConsulta(metodosGenerales.fechaHora()), metodos.fechaConsulta(txtVencimiento.getText()),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                    movimiento.getFactura().replace(movimiento.getPrefijoGeneral() + "-", ""), false, "",
                    !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                    big.getMoneda(movimiento.getPorcentajeReteFuente()),
                    txtObservaciones.getText(), false, "", false, "", "",
                    metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
                    EstadosTipoDocumento.PENDIENTE.getNombre(), "", instancias.getDevuelta(), movimiento.getFactura(),
                    instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    movimiento.getCopago(), txtPlaca1.getText(), txtNombre.getText(), "", tblProductos.getValueAt(i, 31), "",
                    "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19),
                    big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), "", tblProductos.getValueAt(i, 29), tblProductos.getValueAt(i, 27)
                };

                ndPedido nodo = metodos.llenarPedido(vector);
                if (!instancias.getSql().agregarPedido(nodo)) {
                    instancias.getSql().eliminarPedido(movimiento.getFactura());
                    metodos.msgError(null, "Error al guardar el pedido");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean agregarMovimientoPlanSepare(MovimientoDocumento movimiento) {
        String cotizacionesAsociadas = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                Object[] vector = {movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getVendedor(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                    movimiento.getFactura().replace(movimiento.getPrefijoGeneral() + "-", ""), false, "",
                    !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                    big.getMoneda(movimiento.getPorcentajeReteFuente()),
                    txtObservaciones.getText(), false, "", false, "", "",
                    metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
                    EstadosTipoDocumento.PENDIENTE.getNombre(), "", instancias.getDevuelta(), movimiento.getFactura(),
                    instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    movimiento.getCopago(), txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                    "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19),
                    big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), tblProductos.getValueAt(i, 27), tblProductos.getValueAt(i, 29), ""
                };

                ndPlanSepare nodo = metodos.llenarPlanSepare(vector);
                if (!instancias.getSql().agregarPlanSepare(nodo)) {
                    instancias.getSql().eliminarSepare(movimiento.getFactura());
                    metodos.msgError(null, "Hubo un problema al guardar el plan separe");
                    return false;
                }
            }
        }
        guardarCuentaPorCobrar(movimiento.getFactura(), movimiento.getFactura(), "SEPARE");
        return true;
    }

    private boolean agregarMovimientoMesa(MovimientoDocumento movimiento) {
        String cotizacionesAsociadas = "";
        List<ModeloComanda> comandasAinsertar = new ArrayList<>();
        boolean esRestaurante = instancias.getConfiguraciones().isRestaurante();
        String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                String preparacionProducto = obtenerValorTabla(i, 21);

                Object[] vector = {movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getVendedor(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                    movimiento.getFactura().replace(movimiento.getPrefijoGeneral() + "-", ""), false, "",
                    !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                    big.getMoneda(movimiento.getPorcentajeReteFuente()),
                    txtObservaciones.getText(), false, "", false, "", "",
                    metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
                    EstadosTipoDocumento.PENDIENTE.getNombre(), "", instancias.getDevuelta(), movimiento.getFactura2(),
                    instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    movimiento.getCopago(), txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                    "PLATO-" + i, movimiento.getCongelada(), tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19),
                    big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    preparacionProducto, String.valueOf(movimiento.getTurno()), tblProductos.getValueAt(i, 27), tblProductos.getValueAt(i, 29),
                    big.getMoneda(txtTotalImpoconsumo.getText()),
                    tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                    big.getMoneda((String) tblProductos.getValueAt(i, 8)), ""
                };

                ndCongelada nodo = metodos.llenarCongelada(vector);
                if (!instancias.getSql().agregarCongelada(nodo)) {
                    instancias.getSql().eliminarMesa(movimiento.getFactura());
                    metodos.msgError(null, "Hubo un problema al guardar la factura");
                    return false;
                }

                if (esRestaurante) {
                    ModeloComanda comanda = construirComanda(i, movimiento.getCongelada(),
                            movimiento.getFactura(), movimiento.getTurno(), tablaUtilizada);
                    if (comanda != null) {
                        comandasAinsertar.add(comanda);
                    }
                }
            }
        }

        if (esRestaurante && !comandasAinsertar.isEmpty()) {
            DaoComanda daoComanda = new DaoComanda();
            if (!daoComanda.agregarComandaEnBatch(comandasAinsertar)) {
                Logger.getLogger(VistaFactura.class.getName()).log(Level.WARNING, "Se guardó la mesa pero hubo errores al procesar algunas comandas");
            }
        }

        return true;
    }

    private boolean agregarMovimientoCuentaCobro(MovimientoDocumento movimiento) {
        String cotizacionesAsociadas = "";
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                String hasta = chkSinEstablecer.isSelected()
                        ? metodos.desdeDate(dtDesde.getCurrent())
                        : metodos.desdeDate(dtHasta.getCurrent());

                Object[] vector = {movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getVendedor(), "",
                    metodos.fechaConsulta(metodosGenerales.fechaHora()), metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                    movimiento.getFactura().replace(movimiento.getPrefijoGeneral() + "-", ""), false, "",
                    !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "LOTECCOB-" + loteGeneral, instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"),
                    big.getMoneda(txtRtf.getText()), big.getMoneda(movimiento.getPorcentajeReteFuente()),
                    txtObservaciones.getText(), false, "", false, "", "",
                    metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
                    EstadosTipoDocumento.PENDIENTE.getNombre(), "", instancias.getDevuelta(), movimiento.getFactura2(),
                    instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    movimiento.getCopago(), txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                    "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19),
                    big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    cmbPeriodicidad.getSelectedItem(), metodos.desdeDate(dtDesde.getCurrent()), hasta,
                    txtCantIncremento.getText(), big.getMoneda(txtTotalImpoconsumo.getText()),
                    tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                    big.getMoneda((String) tblProductos.getValueAt(i, 8)), ""
                };

                ndCongelada nodo = metodos.llenarCongelada(vector);
                if (!instancias.getSql().agregarCuentaCobro(nodo)) {
                    metodos.msgError(null, "Hubo un problema al guardar la factura");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean agregarMovimientoFactura(MovimientoDocumento movimiento) {
        funcionalidadVentas.agregamosRegistrosMediosDePago(instancias, movimiento.getFactura2());

        if (instancias.getConfiguraciones().isFacturaElectronica() && Constantes.esFacturacionElectronica(movimiento.getTipoComprobante())) {
            if (!generarFacturacionElectronica(movimiento.getTipoComprobante(), movimiento.getFactura(), movimiento.getFactura2())) {
                borrarAdiciones();
                //Aqui tengo que borrar los medios de pago
                return false;
            }
        }

        if (this.esFacturaCredito) {
            calcularCuotasCredito();
            guardarCredito(movimiento.getFactura(), movimiento.getFactura2());
        }

        if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
            guardarCuentaPorCobrar(movimiento.getFactura(), movimiento.getFactura2(), "FACT");
        }

        instancias.getSql().agregarVerificarFactura(movimiento.getFactura(), ID_CLIENTE_CARGADO, movimiento.getFactura2(), terminal,
                big.getMoneda(txtTotal.getText().replace("Total: ", "")), movimiento.getFechaFactura(),
                metodos.fechaConsulta(txtVencimiento.getText()), metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)),
                movimiento.getVendedor(), movimiento.getCongelada(), txtPlaca1.getText(), String.valueOf(movimiento.getTurno()));

        List<ModeloComanda> comandasAinsertar = new ArrayList<>();
        boolean esRestaurante = instancias.getConfiguraciones().isRestaurante();
        String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {

            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                String imei = "", lote = "", idProd = "";

                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    imei = tblProductos.getValueAt(i, 27).toString();
                    lote = tblProductos.getValueAt(i, 28).toString();
                    idProd = tblProductos.getValueAt(i, 29).toString();
                } else if (instancias.getConfiguraciones().isParqueadero()) {
                    imei = tblProductos.getValueAt(i, 27).toString();
                }

                BigDecimal porcentajeDescuento = Utilidades.convertirBigDecimal(obtenerValorTabla(i, 5));
                String preparacion = obtenerValorTabla(i, 21);
                BigDecimal totalUtilidad;
                BigDecimal ponderado;

                if (!preparacion.isEmpty()) {
                    BigDecimal costoPreparacion = obtenerCostoPreparacion(i, preparacion);
                    BigDecimal precioTotal = big.getMoneda(tblProductos.getValueAt(i, 9).toString());
                    totalUtilidad = precioTotal.subtract(costoPreparacion);
                    ponderado = obtenerPonderado(i, costoPreparacion);
                } else {
                    totalUtilidad = big.getMoneda((String) tblProductos.getValueAt(i, 14));
                    if (totalUtilidad.compareTo(BigDecimal.ZERO) < 0) {
                        totalUtilidad = BigDecimal.ZERO;
                    }

                    ponderado = obtenerPonderado(i, BigDecimal.ZERO);
                }

                if (!agregarRegistroFactura(movimiento.getFactura(), movimiento.getFactura2(), movimiento.getPrefijoGeneral(),
                        movimiento.getVendedor(), movimiento.getCongelada(), String.valueOf(movimiento.getTurno()),
                        movimiento.getFechaFactura(), EstadosTipoDocumento.PENDIENTE.getNombre(), movimiento.getCopago(),
                        movimiento.getPorcentajeReteFuente(), porcentajeDescuento,
                        preparacion, totalUtilidad, ponderado, imei, lote, idProd, movimiento.getTipoComprobante(), i)) {
                    return false;
                }

                if (esRestaurante) {
                    ModeloComanda comanda = construirComanda(i, movimiento.getCongelada(), movimiento.getFactura(),
                            movimiento.getTurno(), tablaUtilizada);
                    if (comanda != null) {
                        comandasAinsertar.add(comanda);
                    }
                }
            }
        }

        if (esRestaurante && !comandasAinsertar.isEmpty()) {
            DaoComanda daoComanda = new DaoComanda();
            if (!daoComanda.agregarComandaEnBatch(comandasAinsertar)) {
                Logger.getLogger(VistaFactura.class.getName()).log(Level.WARNING,
                        "Se guardó la factura pero hubo errores al procesar algunas comandas");
            }
        }

        return true;
    }

    private boolean agregarRegistroFactura(String factura, String factura2, String prefijoGeneral,
            String vendedor, String congelada, String turno,
            String fechaFactura, String estado, BigDecimal copago,
            String porcentajeReteFuente, BigDecimal porcentajeDescuento,
            String preparacion, BigDecimal totalUtilidad, BigDecimal ponderado,
            String imei, String lote, String idProd, String tipoComprobante, int fila) {

        String cotizacionesAsociadas = "";
        String campoGarantia = "";
        String consecutivoCosteo = "";

        Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", fechaFactura,
            metodos.fechaConsulta(txtVencimiento.getText()), instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(),
            instancias.getTarjetaDevuelta(), big.getMoneda(txtTotal.getText().replace("Total: ", "")),
            big.getMoneda(txtTotalDescuentos.getText()),
            big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
            factura.replace(prefijoGeneral + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
            loteCuentasCobro, instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
            big.getMoneda(porcentajeReteFuente), txtObservaciones.getText(), false, "", false, "", "",
            metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), terminal,
            estado, "", instancias.getDevuelta(), factura2, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
            copago, txtPlaca1.getText(), campoGarantia, "", tblProductos.getValueAt(fila, 31), "",
            "", congelada, tblProductos.getValueAt(fila, 32), big.getMoneda((String) tblProductos.getValueAt(fila, 2)),
            tblProductos.getValueAt(fila, 3).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(fila, 6)),
            big.getMoneda((String) tblProductos.getValueAt(fila, 9)), big.getMoneda((String) tblProductos.getValueAt(fila, 33)),
            big.getMoneda((String) tblProductos.getValueAt(fila, 4)), "", totalUtilidad, "",
            porcentajeDescuento + "", tblProductos.getValueAt(fila, 1), tblProductos.getValueAt(fila, 12) + "", tblProductos.getValueAt(fila, 13).toString().replace(",", "."),
            EstadosTipoDocumento.FACTURADA.getNombre(), tblProductos.getValueAt(fila, 7), tblProductos.getValueAt(fila, 19), big.getMoneda((String) tblProductos.getValueAt(fila, 20)),
            preparacion, BigDecimal.ZERO, turno, big.getMoneda(txtTotalImpoconsumo.getText()), instancias.getFranquisia(),
            instancias.getComision(), instancias.getValorComision(), instancias.getTotalFacturaComision(), imei, lote, idProd,
            cmbMes.getSelectedItem(), instancias.getTarjetaCredito(), instancias.getTotalPropina(), instancias.getPorcPropina(),
            consecutivoCosteo, metodosGenerales.hora(), tblProductos.getValueAt(fila, 23).toString().replace(".", "").replace(",", "."),
            big.getMoneda((String) tblProductos.getValueAt(fila, 8)), chkSisteCredito.isSelected(), "", Utilidades.formatearCantidad(ponderado), tipoComprobante
        };

        ndFactura nodo = metodos.llenarFactura(vector);

        if (!daoFactura.agregarRegistro(nodo)) {
            boolean noPuedaGuardar = false;

            daoFactura.eliminarRegistro(factura);
            while (!noPuedaGuardar) {
                noPuedaGuardar = daoFactura.eliminarRegistro(factura);
            }

            metodos.msgError(null, "Error al guardar la factura");
            return false;
        }

        return true;
    }

    private ModeloComanda construirComanda(int fila, String congelada, String factura, int turno, String tablaUtilizada) {
        ServicioProcesadorComandas procesador = new ServicioProcesadorComandas(new CargadorProducto() {
            @Override
            public ndProducto cargar(String codigo, String tabla) {
                return instancias.getSql().getDatosProducto(codigo, tabla);
            }
        });

        String preparacion = obtenerValorTabla(fila, 21);
        String codigoProducto = tblProductos.getValueAt(fila, 32).toString();
        String nombreProducto = tblProductos.getValueAt(fila, 1).toString();
        BigDecimal cantidad = Utilidades.convertirBigDecimal(obtenerValorTabla(fila, 3));
        String consecutivo = "PLATO-" + fila;

        if (!preparacion.isEmpty()) {
            return procesador.construirComanda(codigoProducto, nombreProducto, preparacion,
                    tablaUtilizada, cantidad, congelada, factura, turno, "", consecutivo, instancias);
        } else {
            return procesador.construirComandaSimple(codigoProducto, nombreProducto, cantidad,
                    congelada, factura, turno, "", consecutivo);
        }
    }

    private void generarImpresionDocumento(String desde, String factura, String factura2, boolean imprimir) {
        //SI LA GENERA DESDE LA ORDEN MEDICA SACARA ESTE MENSAJE
        if (desde.equals("ordenMedica")) {

            if (instancias.isImprimirFacturaOrdenMedica()) {
                if (metodos.msgPregunta(null, "¿Desea imprimir la fact Nro." + factura.replace("FACT-", "") + "?") == 0) {

                    String observaciones = txtObservaciones.getText();

                    String legal = "", pie;

                    try {
                        legal = instancias.getLegal();
                    } catch (Exception ex) {
                        legal = "";
                    }

                    try {
                        pie = instancias.getPie();
                    } catch (Exception ex) {
                        pie = "";
                    }

                    String impresora = "", tipoFact = "";
                    if (rdMediaCarta.isSelected()) {
                        tipoFact = "facturaMedica";
                        impresora = DatosMaestra.getImpresoraMediaCarta();
                    } else if (rdCarta.isSelected()) {
                        tipoFact = "facturaMedicaCompleta";
                        impresora = DatosMaestra.getImpresoraCarta();
                    } else if (rdPos.isSelected()) {
                        tipoFact = "facturaMedica";
                        impresora = DatosMaestra.getImpresoraPos();
                    }

                    String impoconsumo = String.valueOf(DatosMaestra.isMostrarImpoconsumo());
                    String retenciones = String.valueOf(DatosMaestra.isMostrarRetenciones());
                    String condicion = funcionalidadVentas.condicionFactura(instancias, factura2);

                    // Original
                    instancias.getReporte().ver_Factura(observaciones,
                            instancias.getInformacionEmpresaCompleto(), legal, "Original", pie,
                            tipoFact, factura2, !DatosMaestra.isPrevisualizarFactura(), "", impresora, impoconsumo, retenciones, condicion, false);

                    // Copias configuradas
                    int cantidad = 0;
                    try {
                        cantidad = Integer.parseInt(DatosMaestra.getNumFactura());
                    } catch (NumberFormatException ignored) {
                    }
                    for (int i = 0; i < cantidad; i++) {
                        instancias.getReporte().ver_Factura(observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Copia " + (i + 1),
                                pie, tipoFact, factura2, !DatosMaestra.isPrevisualizarFactura(), "", impresora, impoconsumo, retenciones, condicion, false);
                    }
                }
            } else {
                metodos.msgExito(null, "Fact Nro." + factura.replace("FACT-", "") + " exitosa");
            }
        } else {
            if (imprimir) {
                imprimir(factura, factura2);
            }
        }

        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())) {
            if (instancias.isUbicacion()) {
                try {
                    if (metodos.msgPregunta(null, "¿Desea imprimir ubicación?") == 0) {
                        instancias.getReporte().ver_ubicacion(factura2, false);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    private void mostrarMensajeFinalizacionDocumento(String desde) {
        if (this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                || this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && lbTitulo.getText().equals("DOMICILIO")) {

            if (!saltarPasosFactura) {
                if (!desde.equals("ordenMedica") && this.tipoProceso.equals(TipoDocumento.FACTURACION.getValor())) {
                    lbObservaciones.requestFocus();
                    metodos.msgExito(null, "Factura Exitosa");
                }
            }

            if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && lbTitulo.getText().equals("DOMICILIO")) {
                metodos.msgExito(null, "Domicilio Exitoso");

                if (instancias.getConfiguraciones().isRestaurante()) {
                    instancias.getMenu().cambiarTitulo("MESAS");
                } else {
                    instancias.getMenu().cambiarTitulo("CONGELADAS");
                }
            }
        } else if (this.tipoProceso.equals(TipoDocumento.ORDER_SERVICIO.getValor())) {
            metodos.msgExito(null, "Orden Exitosa");
        } else if (this.tipoProceso.equals(TipoDocumento.COTIZACION.getValor())) {
            metodos.msgExito(null, "Cotización Exitosa");
        } else if (this.tipoProceso.equals(TipoDocumento.PEDIDO.getValor())) {
            metodos.msgExito(null, "Pedido Exitoso");
        } else if (this.tipoProceso.equals(TipoDocumento.PLAN_SEPARE.getValor())) {
            metodos.msgExito(null, "Separe Exitoso");
        } else if (this.tipoProceso.equals(TipoDocumento.MESA.getValor()) && !lbTitulo.getText().equals("DOMICILIO")) {
            if (!pasandoACongelada) {
                if (instancias.getConfiguraciones().isRestaurante()) {
                    metodos.msgExito(null, "Mesa Exitosa");
                } else {
                    metodos.msgExito(null, "Congelada Exitosa");
                }
            }

            btnReImprimir.setVisible(true);
            btnModificar.setVisible(true);
            btnGuardar.setVisible(true);
            btnGuardar.setText("FACTURAR");
            btnImprimir.setVisible(true);
        } else if (this.tipoProceso.equals(TipoDocumento.CUENTA_COBRO.getValor())) {
            if (!saltarPasosFactura) {
                metodos.msgExito(null, "Plantilla Exitosa");
            }
        }
    }

    private void mostrarDevueltaFactura() {
        if (!saltarPasosFactura && txtDiasPlazo.getText().equals("0") && DatosMaestra.isMostrarDevuelta()) {
            VistaDevuelta devueltaTotal = new VistaDevuelta(instancias.getMenu(), true, instancias, instancias.getDevuelta());
            devueltaTotal.setVisible(true);
        }
    }

    private void actualizarEstadoGuarderia(String factura) {
        if (!instancias.getSql().modificarGuarderia1(ndGuarderia, factura)) {
            metodos.msgError(null, "Error al modificar el estado de la guarderia");
        }

        metodos.msgExito(null, "Guarderia Finalizada");
        instancias.getGuarderia().actualizarTabla();
    }

    private void actualizarEstadoHospitalizacion(String factura) {
        if (!instancias.getSql().modificarHospitalizacion1(ndHospitalizacion, factura, big.getMoneda(txtTotal.getText().replace("Total: ", "")))) {
            metodos.msgError(null, "Error al modificar estado de la hosp");
        }

        instancias.getSql().eliminar_registro("bdMedicamentosProcesosAlertas", " idHospitalizacion = '" + ndHospitalizacion + "' ");
        metodos.msgExito(null, "Hospitalización Finalizada");
        instancias.getSql().modificarHospitalizacion(ndHospitalizacion, horasHospitalizacion, diasHospitalizacion);
        instancias.getIngresoHospitalizacion().cargarRegistros();
    }

    private void actualizarEstadoPeluqueria(String factura) {
        if (!instancias.getSql().modificarPeluqueria(ndPeluqueria, "Atendido", factura)) {
            metodos.msgError(null, "No fue posible modificar el estado de la Cita");
        }

        metodos.msgExito(null, "Cita Finalizada");
        instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
        instancias.getPeluqueria().cargarAgendas();
    }

    private void actualizarEstadoAgenda() {
        //Si es diferente de medico va a ingresar, ya que es de otro tipo y puede ingresar y cambiar el estado de la cita.
        String cliente = ID_CLIENTE_CARGADO;
        String cita = "";

        try {
            cita = instancias.getSql().getAgendasDelDia(cliente, metodos.fechaConsulta(metodosGenerales.fecha()));
        } catch (Exception e) {
        }

        if (!cita.equalsIgnoreCase("")) {
            if (!instancias.getSql().modificarCita(cita, "Atendido")) {
                metodos.msgError(null, "No fue posible modificar el estado de la Cita");
            }

            instancias.getAgendaConsulta().cargarAgendas(instancias.getMedico());
            instancias.getAgendaConsulta().cargarAgendas();
            instancias.getAgendaConsulta().show();
            try {
                instancias.getAgendaConsulta().setSelected(true);
            } catch (PropertyVetoException ex) {
            }
        }
    }

    private void solicitarPermisoParaLimpiar() {
        if (metodos.msgPregunta(null, "No se puede borrar ¿Pedir permiso?") == 0) {
            vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true, "No se puede limpiar la mesa.", "LIMPIAR",
                    instancias.getTitulo(), "borrarMesa");
            permisos.setLocationRelativeTo(null);
            permisos.setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnCambiarMesa;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnInformacionCliente;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevaParte;
    private javax.swing.JButton btnNuevaParte1;
    private javax.swing.JButton btnPasarACongelada;
    private javax.swing.JButton btnReImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkReteIva;
    private javax.swing.JCheckBox chkSinEstablecer;
    private javax.swing.JCheckBox chkSisteCredito;
    private javax.swing.JComboBox cmbListaPrecio;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JComboBox cmbPeriodicidad;
    private javax.swing.JComboBox cmbRtf;
    private javax.swing.JComboBox cmbTipoPlazo;
    private javax.swing.JComboBox cmbVendedor;
    private datechooser.beans.DateChooserCombo dtDesde;
    private datechooser.beans.DateChooserCombo dtFechaDesenvolso;
    private datechooser.beans.DateChooserCombo dtHasta;
    private javax.swing.ButtonGroup grpCopago;
    private javax.swing.ButtonGroup grupoTipoFactura;
    private javax.swing.ButtonGroup grupoTipoImpresion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane jtblComprobantes;
    private javax.swing.JLabel lbCambiarMesa;
    private javax.swing.JLabel lbCar;
    private javax.swing.JLabel lbCargarDocumento;
    private javax.swing.JLabel lbCelular1;
    private javax.swing.JLabel lbCupo;
    private javax.swing.JLabel lbDiasPlazo;
    private javax.swing.JLabel lbDireccion3;
    private javax.swing.JLabel lbDireccion4;
    private javax.swing.JLabel lbDireccion5;
    private javax.swing.JLabel lbDireccion6;
    private javax.swing.JLabel lbDireccion7;
    private javax.swing.JLabel lbDireccion8;
    private javax.swing.JTextField lbFacturaNo;
    private javax.swing.JLabel lbFechaVencimiento;
    private javax.swing.JLabel lbImpoconsumo;
    private javax.swing.JLabel lbNit;
    private javax.swing.JLabel lbNit1;
    private javax.swing.JTextField lbNoFactura;
    private javax.swing.JLabel lbNombre10;
    private javax.swing.JLabel lbNombre11;
    private javax.swing.JLabel lbNombre13;
    private javax.swing.JLabel lbNombre14;
    private javax.swing.JLabel lbNombre5;
    private javax.swing.JLabel lbNombre6;
    private javax.swing.JLabel lbNombre7;
    private javax.swing.JLabel lbNombre8;
    private javax.swing.JLabel lbNombre9;
    private javax.swing.JLabel lbObservaciones;
    private javax.swing.JTextField lbOtroConsecutivo;
    private javax.swing.JLabel lbProducto;
    private javax.swing.JLabel lbProducto1;
    private javax.swing.JLabel lbSubtotal;
    private javax.swing.JLabel lbTelefono3;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JLabel lbTotalDescuento;
    private javax.swing.JLabel lbTotalDescuento1;
    private javax.swing.JLabel lbTotalDescuento2;
    private javax.swing.JLabel lbVendedor1;
    private javax.swing.JLabel lbVendedor10;
    private javax.swing.JLabel lbVendedor11;
    private javax.swing.JLabel lbVendedor12;
    private javax.swing.JLabel lbVendedor13;
    private javax.swing.JLabel lbVendedor7;
    private javax.swing.JLabel lbVendedor8;
    private javax.swing.JLabel lbVendedor9;
    private javax.swing.JPanel pnlCambiarMesa;
    private javax.swing.JPanel pnlCredito;
    private javax.swing.JPanel pnlCuentaCobro;
    private javax.swing.JPanel pnlFacturacionAutomatica;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlGrupos;
    private javax.swing.JPanel pnlOcultar;
    private javax.swing.JPanel pnlOrdenServicio;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdCarta;
    private javax.swing.JRadioButton rdMediaCarta;
    private javax.swing.JRadioButton rdPos;
    private javax.swing.JRadioButton rdTipoCopago;
    private javax.swing.JRadioButton rdTipoNormal;
    private javax.swing.JScrollPane scrProductos1;
    private javax.swing.JTabbedPane tapControl;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblCuotas;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCantFacturados;
    private javax.swing.JTextField txtCantIncremento;
    private javax.swing.JLabel txtCantProductos;
    private javax.swing.JLabel txtCantUnidades;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCargar;
    private javax.swing.JTextField txtCartera;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtCopago;
    private javax.swing.JTextField txtCuotaInicial;
    private javax.swing.JTextField txtCuotas;
    private javax.swing.JTextField txtCupo;
    private javax.swing.JTextField txtDescGeneral;
    private javax.swing.JTextField txtDiasPlazo;
    private javax.swing.JTextField txtFechaFactura;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JLabel txtIva;
    private javax.swing.JTextField txtKm;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtMotor;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumChasis;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtPlaca1;
    private javax.swing.JTextField txtPorcentaje;
    private javax.swing.JTextArea txtProblema;
    private javax.swing.JLabel txtRiva;
    private javax.swing.JLabel txtRtf;
    private javax.swing.JLabel txtSubTotal;
    private javax.swing.JTextField txtTipoVehiculo;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JTextField txtTotalCredito;
    private javax.swing.JLabel txtTotalDescuentos;
    private javax.swing.JLabel txtTotalImpoconsumo;
    private javax.swing.JTextField txtTotalIntereses;
    private javax.swing.JLabel txtTotalIva;
    private javax.swing.JTextField txtTurno;
    private javax.swing.JTextField txtUltimaFacturaFecha;
    private javax.swing.JTextField txtValorCredito;
    private javax.swing.JTextField txtValorVenta;
    private javax.swing.JTextField txtVencimiento;
    // End of variables declaration//GEN-END:variables

}
