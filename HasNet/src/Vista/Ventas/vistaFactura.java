package Vista.Ventas;

import Consumidor.FacturacionElectronica.consumidorFacturacionElectronica;
import Controlador.Alertas.ControladorAlertas;
import dao.Configuraciones.DaoResoluciones;
import dao.InicioSesion.DaoInicioSesion;
import dao.Ventas.DaoFactura;
import Modelo.Ventas.CabeceraDocumento;
import Modelo.Ventas.DocumentoMovimiento;
import Modelo.Ventas.LineaProducto;
import Modelo.Ventas.FilaProductoTabla;
import Modelo.Ventas.ResultadoValidacionInventario;
import inventario.servicio.ServicioValidacionFactura;
import Enums.TipoDocumento;
import Enums.enumTipoIdentificacion;
import Enums.enumTipoPersona;
import Enums.DetalleTipoProducto;
import Enums.TipoProducto;
import Enums.enumBodegas;
import Modelo.DocumentosElectronicos.ModeloDescuentos;
import Modelo.DocumentosElectronicos.ModeloDetalleProductos;
import Modelo.DocumentosElectronicos.ModeloDetalleImpuestos;
import Modelo.FacturacionElectronica.Entrada.ModeloFacturacionElectronica;
import Modelo.InicioSesion.Terminal;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Inventario.UltimoPonderado;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.Constantes;
import Validaciones.Facturacion.squemaFacturacion;
import Validaciones.FacturacionElectronica.squemaFacturacionElectronica;
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
import Modelo.Terceros.ModeloContacto;
import inventario.servicio.ServicioActualizacionPonderado;
import inventario.servicio.ServicioInventario;
import Modelo.Ventas.OpcionPreparacion;
import Utilidades.Ventas.ParserPreparacion;
import Utilidades.Utilidades;
import Vista.Productos.VistaInventarioInicial;
import Vista.Solicitudes.vistaSolicitarPermisos;
import formularios.Parqueadero.buscPlacas;
import formularios.Ventas.buscProblemas;
import formularios.Ventas.buscTipoVehiculo;
import formularios.Ventas.dlgCotizacionesPendientes;
import formularios.Ventas.dlgEscojerMesa;
import formularios.Ventas.dlgInformacionCliente;
import formularios.Ventas.dlgOrdenesServicioPendientes;
import formularios.Ventas.dlgPagosProveedores;
import formularios.Ventas.dlgPedidosPendientes;
import formularios.Ventas.dlgPedirPermiso;
import formularios.Ventas.dlgProductosGrupo;
import formularios.Ventas.dlgProductosSinInventario;
import formularios.Ventas.dlgTipoDescuento;
import formularios.Ventas.impresionComanda;
import formularios.Ventas.infNuevaParte;
import formularios.infBuscadorCliente;
import formularios.productos.buscProductos;
import inventario.vista.VistaMovimientoDetalleProducto;
import formularios.productos.seleccionarPLU;
import formularios.terceros.buscClientes;
import inventario.servicio.CargadorProducto;
import inventario.servicio.ServicioDiscosteo;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class VistaFactura extends javax.swing.JPanel {

    DefaultTableModel modeloPro;
    DefaultTableModel modeloComprobantes;
    DefaultTableModel modeloInventario;
    DefaultTableModel modeloCredito;
    metodosGenerales metodos = new metodosGenerales();
    Instancias instancias;

    private DecimalFormat formatoDosDecimales = new DecimalFormat("#.00");
    private String ID_CLIENTE_CARGADO = null;
    private ModeloContacto DATOS_CLIENTE_CARGADO = null;
    private boolean DESCUENTO_GENERAL_CARGADO = false;
    private ControladorAlertas alertas = new ControladorAlertas();
    private squemaFacturacion squemaFacturacion = new squemaFacturacion();
    private squemaFacturacionElectronica squemaFacturacionElectronica = new squemaFacturacionElectronica();
    private consumidorFacturacionElectronica consumidorFacturacionElectronica = new consumidorFacturacionElectronica();

    private ServicioActualizacionPonderado servicioActualizacionPonderado = new ServicioActualizacionPonderado();

    private FuncionalidadVentas funcionalidadVentas = new FuncionalidadVentas();

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();
    private final DaoInicioSesion daoInicioSesion = new DaoInicioSesion();
    private final DaoFactura daoFactura = new DaoFactura();

    //Barra de titulo
    private Dimension dimBarra = null;
    boolean topeDescuento = false;
    private int cantDias = 0;
    DecimalFormat df = new DecimalFormat("#.00");

    private boolean focusDiasPlazo = false, cambioMesa = false, plu = false, desdeParqueadero = false, mesaCongelada = false, actualizarInventario = true,
            saltarPasosFactura = false, saltarPasosFactura1 = false, facturaCredito, modificarPedidoActivo = false, cargandoCongelada = false,
            solicitudPermiso = false, facturandoPedidos = false, preguntarLimpiar = true;

    private factura factura;
    private String tipoProceso, credito1, consecutivoMesa, idCosteo, descontarFisicoInventario = "SI",
            loteGeneral = "", permisoNumero = "", tipoActual = "", ter = "", loteCuentasCobro = "", fechaFacturaAutomatica = "";

    private BigDecimal costoCosteo;

    Object[] datos;
    Object[] productosMovimientos;
    Object[][] productosMovimientos1;

    public int cantProductosOrden = 0;
    private Vector<String> cotizaciones = null;

    //NODOS
    private String nodoOrdenServicio, trasladoBod = "", nodoCotizacion, ndPeluqueria = "", ndGuarderia = "", ndHospitalizacion = "", diasHospitalizacion = "",
            horasHospitalizacion = "", simbolo = "";
    private ndPlanSepare ndSepare;
    private ndCongelada cuentaCobro;
    private ndPedido ndPedido;

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

    public BigDecimal getCostoCosteo() {
        return costoCosteo;
    }

    public void setCostoCosteo(BigDecimal costoCosteo) {
        this.costoCosteo = costoCosteo;
    }

    public String getIdCosteo() {
        return idCosteo;
    }

    public void setIdCosteo(String idCosteo) {
        this.idCosteo = idCosteo;
    }

    public boolean isCambioMesa() {
        return cambioMesa;
    }

    public void setCambioMesa(boolean cambioMesa) {
        this.cambioMesa = cambioMesa;
    }

    public String getDescontarFisicoInventario() {
        return descontarFisicoInventario;
    }

    public void setDescontarFisicoInventario(String descontarFisicoInventario) {
        this.descontarFisicoInventario = descontarFisicoInventario;
    }

    public boolean isActualizarInventario() {
        return actualizarInventario;
    }

    public void setActualizarInventario(boolean actualizarInventario) {
        this.actualizarInventario = actualizarInventario;
    }

    public boolean isModificarPedidoActivo() {
        return modificarPedidoActivo;
    }

    public void setModificarPedidoActivo(boolean modificarPedidoActivo) {
        this.modificarPedidoActivo = modificarPedidoActivo;
    }

    public VistaFactura(String tipo) {
        initComponents();

        chkSisteCredito.setEnabled(false);

        lbCupo.setVisible(false);
        pnlOcultar.setVisible(false);
        pnlCambiarMesa.setVisible(false);
        pnlGarantia.setVisible(false);

        instancias = Instancias.getInstancias();
        consultarMaestros();

        pnlVisor.setVisible(false);

        tblImagenes.setDefaultRenderer(Object.class, new IconCellRenderer());
        tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());
        tblProductos.getColumnModel().getColumn(1).setCellRenderer(new WordWrapCellRenderer());

        pnlCuentaCobro.setVisible(false);
        btnPendientes.setVisible(false);
        jtblComprobantes.setVisible(false);

        simbolo = instancias.getSimbolo();

        modeloComprobantes = (DefaultTableModel) tblComprobantes.getModel();

        actualizarTablaResoluciones();

        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            txtDescGeneral.setVisible(false);
        }

        if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
            tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        if (!instancias.getConfiguraciones().isMedico()) {
            if (tblProductos.getColumnModel().getColumnCount() > 0) {
                tblProductos.getColumnModel().getColumn(17).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(17).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(17).setMaxWidth(0);
            }
            txtCopago.setVisible(false);
        }

        try {
            if (datos[17].equals("SI")) {
                if (tblProductos.getColumnModel().getColumnCount() > 0) {
                    tblProductos.getColumnModel().getColumn(10).setMinWidth(50);
//                    tblProductos.getColumnModel().getColumn(10).setPreferredWidth(150);
                    tblProductos.getColumnModel().getColumn(10).setMaxWidth(125);
                }
            } else {
                tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
            }
        } catch (Exception e) {
        }

        if (tipo.equals("credito")) {
            facturaCredito = true;
            this.tipoProceso = "facturacion";
        } //        else if (tipo.equals("congeladas")) {
        //            System.out.println("entro a las mesas");
        //            jLabel2.setVisible(false);
        //            jLabel2.setEnabled(false);
        //            this.tipo = "facturacion";
        //        } 
        else {
            facturaCredito = false;
            this.tipoProceso = tipo;
        }

        if (tipo.equals("facturacion") && (Boolean) datos[78]) {
            tblProductos.getColumnModel().getColumn(19).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(19).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(19).setMaxWidth(100);
        } else {
            tblProductos.getColumnModel().getColumn(19).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(19).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(19).setMaxWidth(0);
        }

        if (instancias.getConfiguraciones().isProductosDetallados()) {
            tblProductos.getColumnModel().getColumn(27).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(27).setPreferredWidth(200);
            tblProductos.getColumnModel().getColumn(27).setMaxWidth(300);
        } else {
            if (instancias.getConfiguraciones().isParqueadero()) {
                tblProductos.getColumnModel().getColumn(27).setMinWidth(100);
                tblProductos.getColumnModel().getColumn(27).setPreferredWidth(200);
                tblProductos.getColumnModel().getColumn(27).setMaxWidth(300);
            } else {
                tblProductos.getColumnModel().getColumn(27).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(27).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(27).setMaxWidth(0);
            }
        }

//        if (!tipo.equals("orden")) {
        tblProductos.getColumnModel().getColumn(26).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(26).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(26).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(25).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(25).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(25).setMaxWidth(0);
        tblProductos.getColumnModel().getColumn(24).setMinWidth(0);
        tblProductos.getColumnModel().getColumn(24).setPreferredWidth(0);
        tblProductos.getColumnModel().getColumn(24).setMaxWidth(0);
//        }

        // SI ES RESTAURANTE ACTIVAMOS EL PANEL 
        if (instancias.getConfiguraciones().isRestaurante()) {

            if (tblProductos.getColumnModel().getColumnCount() > 0) {
                tblProductos.setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{
                            "Codigo", "Descripción", "Valor/Unit", "Cant.", "Subtotal", "Desc %", "Desc " + this.simbolo, "Iva %", "Impo " + this.simbolo, "Total", "Ubicación",
                            "Referencia", "plu", "cant2", "ponderado", "Utilidad", "Estado", "Copago", "datoGrupo", "Pago Tercero", "Utilidad",
                            "Preparacion", "Borrar", "Impoconsumo", "Orden", "Aviso", "F. Entrega", "Imei", "Lote", "idProd", "paraComanda",
                            "permisoDesc", "idSistema", "Iva " + this.simbolo, "Grupo", "Medida", "ControlInv"
                        }
                ) {
                    boolean[] canEdit = new boolean[]{
                        false, false, true, true, false, true, true, false, false, false, false, false, false, false, false, false, false, true,
                        false, true, true, true, false, true, false, false, false, false, false, false, false, false, false, true, false, false, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit[columnIndex];
                    }
                });

                if (instancias.getConfiguraciones().getTipoImpresion().equals("Sin-Codigo")) {
                    tblProductos.getColumnModel().getColumn(0).setMinWidth(0);
                    tblProductos.getColumnModel().getColumn(0).setPreferredWidth(0);
                    tblProductos.getColumnModel().getColumn(0).setMaxWidth(0);
                } else {
                    tblProductos.getColumnModel().getColumn(0).setMinWidth(50);
                    tblProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
                    tblProductos.getColumnModel().getColumn(0).setMaxWidth(200);
                }

                tblProductos.getColumnModel().getColumn(1).setMinWidth(200);

                tblProductos.getColumnModel().getColumn(2).setMinWidth(80);
                tblProductos.getColumnModel().getColumn(2).setPreferredWidth(100);
                tblProductos.getColumnModel().getColumn(2).setMaxWidth(150);
                tblProductos.getColumnModel().getColumn(23).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(23).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(23).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(33).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(33).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(33).setMaxWidth(0);

                if (tipo.equals("facturacion") && (Boolean) datos[78]) {
                    tblProductos.getColumnModel().getColumn(19).setMinWidth(100);
                    tblProductos.getColumnModel().getColumn(19).setPreferredWidth(100);
                    tblProductos.getColumnModel().getColumn(19).setMaxWidth(100);
                } else {
                    tblProductos.getColumnModel().getColumn(19).setMinWidth(0);
                    tblProductos.getColumnModel().getColumn(19).setPreferredWidth(0);
                    tblProductos.getColumnModel().getColumn(19).setMaxWidth(0);
                }

                tblProductos.getColumnModel().getColumn(3).setMinWidth(35);
                tblProductos.getColumnModel().getColumn(3).setPreferredWidth(35);
                tblProductos.getColumnModel().getColumn(3).setMaxWidth(35);
                tblProductos.getColumnModel().getColumn(4).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(4).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(4).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(7).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(7).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(7).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(8).setMinWidth(80);
                tblProductos.getColumnModel().getColumn(8).setPreferredWidth(100);
                tblProductos.getColumnModel().getColumn(8).setMaxWidth(140);
                tblProductos.getColumnModel().getColumn(9).setMinWidth(80);
                tblProductos.getColumnModel().getColumn(9).setPreferredWidth(100);
                tblProductos.getColumnModel().getColumn(9).setMaxWidth(150);
                tblProductos.getColumnModel().getColumn(10).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(10).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(10).setMaxWidth(0);
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
                tblProductos.getColumnModel().getColumn(17).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(17).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(17).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(18).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(18).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(18).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(20).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(20).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(20).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(21).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(21).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(21).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(22).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(22).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(22).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(24).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(24).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(24).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(25).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(25).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(25).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(26).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(26).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(26).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(27).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(27).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(27).setMaxWidth(0);
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
                tblProductos.getColumnModel().getColumn(34).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(34).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(34).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(35).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(35).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(35).setMaxWidth(0);
                tblProductos.getColumnModel().getColumn(36).setMinWidth(0);
                tblProductos.getColumnModel().getColumn(36).setPreferredWidth(0);
                tblProductos.getColumnModel().getColumn(36).setMaxWidth(0);
            }

            tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());

        } else {
            pnlVisor.setVisible(false);
        }
        // FIN DEL RESTARANTE

        if (datos[6].toString().equals("porcentaje")) {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(45);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(45);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(45);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(0);
        } else {
            tblProductos.getColumnModel().getColumn(5).setMinWidth(0);
            tblProductos.getColumnModel().getColumn(5).setPreferredWidth(0);
            tblProductos.getColumnModel().getColumn(5).setMaxWidth(0);
            tblProductos.getColumnModel().getColumn(6).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        cargarTablaRestaurante();

        tblInventario.removeEditor();
        modeloPro = (DefaultTableModel) tblProductos.getModel();

//        combobox en tabla de inventario
        TableColumn tc = tblInventario.getColumnModel().getColumn(0);
        TableCellEditor tce = new DefaultCellEditor(cmbListas);
        tc.setCellEditor(tce);

        txtFechaFactura.setText(metodosGenerales.fecha());
        txtVencimiento.setText(metodosGenerales.fecha());
        setBorder(null);
        repaint();

//        tblProductos.setDefaultRenderer(Object.class, new cambiarColorTabla(3, 0));
        txtNit.requestFocus();
        btnVolver.setVisible(false);
        btnVolver1.setVisible(false);
        jPanel6.setVisible(false);
        btnActualizar.setVisible(false);
        btnReImprimir.setVisible(false);

        if (facturaCredito) {
            txtDiasPlazo.setEnabled(false);
            pnlCredito.setVisible(true);
            lbTitulo.setText("CREDITOS");
            modeloCredito = (DefaultTableModel) tblCuotas.getModel();
            dtFechaDesenvolso.setFormat(2);
            dtFechaDesenvolso.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
        } else {
            pnlCredito.setVisible(false);
        }

        if (instancias.getConfiguraciones().isRestaurante() && (tipo.equals("mesa") || tipo.equals("facturacion")
                || tipo.equals("pedido") || tipo.equals("cotizacion"))) {
            pnlVisor.setVisible(true);
        }

        ter = instancias.getTerminal();

//        lbNombre3.setVisible(false);
//        txtCartera.setVisible(false);
//        lbNombre2.setForeground(Color.red);
        setTipo(tipo);

        tblComprobantes.setValueAt(true, 0, 2);

        actualizarResolucion(0);
        actualizarConsecutivo(0);

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

    private ActionListener accion(final String opc) {
        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (opc) {
                    case "imprimir":
                        if ((btnGuardar1.isEnabled()) && (btnGuardar1.isVisible())) {
                            btnGuardar1ActionPerformed(null);
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
                        if ((btnActualizar.isEnabled()) && (btnActualizar.isVisible())) {
                            btnActualizarActionPerformed(null);
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
                                metodos.msgAdvertenciaAjustado(factura, "La cantidad no se puede aumentar");
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

    public void setTipo(String tipo) {
        switch (tipo) {
            case "credito":
                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);
                    tapControl.remove(2);

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                break;
            case "facturacion":
                lbTitulo.setText("FACTURACIÓN");
                lbFacturaNo.setText(instancias.getTituloFactura());

                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    pnlGarantia.setVisible(true);
                }

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);
                    tapControl.remove(2);

//                    if (!instancias.getConfiguraciones().isRestaurante()) {
//                        tapControl.remove(1);
//                    }
                }

                jtblComprobantes.setVisible(true);

                break;
            case "cotizacion":
                lbTitulo.setText("COTIZACIÓN");
                lbFacturaNo.setText("Cotización No.");

                btnPendientes.setVisible(true);
                btnReImprimir.setVisible(true);

                lbFechaVencimiento.setVisible(false);
                txtVencimiento.setVisible(false);

                lbDiasPlazo.setEnabled(false);
                txtDiasPlazo.setEnabled(false);

                chkSisteCredito.setEnabled(false);
                txtTurno.setVisible(false);
                lbOtroConsecutivo.setVisible(false);

                btnGuardar.setText("GUARDAR");

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);
                    tapControl.remove(2);

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                break;
            case "orden":
                lbTitulo.setText("ORDEN DE SERVICIO");
                lbFacturaNo.setText("Orden No.");
                btnPendientes.setVisible(true);
                btnPendientes.setText("ORDEN PEND");

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);

                    if (!instancias.getConfiguraciones().isServicioAutomotor()) {
                        tapControl.remove(2);
                    }

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                lbFechaVencimiento.setVisible(false);
                txtVencimiento.setVisible(false);

                lbDiasPlazo.setEnabled(false);
                txtDiasPlazo.setEnabled(false);
                chkSisteCredito.setEnabled(false);

                btnActualizar.setVisible(true);
                btnReImprimir.setVisible(true);
                btnGuardar.setText("GUARDAR");
                break;
            case "pedido":
                lbTitulo.setText("PEDIDOS");
                lbFacturaNo.setText("Pedido No.");
                btnActualizar.setVisible(true);
                btnReImprimir.setVisible(true);

                btnPendientes.setVisible(true);
                btnPendientes.setText("PEDIDOS PEND");

                btnGuardar.setText("GUARDAR");

                lbFechaVencimiento.setVisible(false);
                txtVencimiento.setVisible(false);

                lbDiasPlazo.setEnabled(false);
                txtDiasPlazo.setEnabled(false);
                chkSisteCredito.setEnabled(false);

                txtDescGeneral.setVisible(false);

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);
                    tapControl.remove(2);

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                break;
            case "separe":
                lbTitulo.setText("PLAN SEPARE");
                lbFacturaNo.setText("Separe No.");

                btnGuardar.setText("GENERAR");
                btnGuardar1.setText("GENERAR/IMPRIMIR");
                btnReImprimir.setVisible(true);

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);
                    tapControl.remove(2);

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                break;
            case "cuentaCobro":
                lbTitulo.setText("CUENTAS DE COBRO");
                lbFacturaNo.setText("Plantilla No.");
                pnlCuentaCobro.setVisible(true);

                txtDiasPlazo.setEnabled(false);
                txtVencimiento.setVisible(false);
                lbFechaVencimiento.setVisible(false);
                lbOtroConsecutivo.setVisible(false);
                txtTurno.setVisible(false);

                /*cmbCargar.setSelectedIndex(5);
                 cmbCargar.setEnabled(false);*/
                btnGuardar.setText("GUARDAR");
                btnGuardar1.setVisible(false);

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(2);

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                break;
            case "mesa":

                if (instancias.getConfiguraciones().isRestaurante()) {
                    if (instancias.getTitulo() == null) {
                        lbTitulo.setText("MESAS");
                    } else {
                        lbTitulo.setText(instancias.getTitulo());
                    }

                    lbFacturaNo.setText("MESA No.");
                } else {
                    lbTitulo.setText("CONGELADA");
                    lbFacturaNo.setText("CONGELADA No.");
                }

                btnVolver.setVisible(true);
                btnVolver1.setVisible(true);

                if (tapControl.getTabCount() == 4) {
                    tapControl.remove(3);
                    tapControl.remove(2);

                    if (!instancias.getConfiguraciones().isRestaurante()) {
                        tapControl.remove(1);
                    }
                }

                jtblComprobantes.setVisible(true);

                break;
        }

        if (instancias.isVentasPredeterminado()) {
            cargar1010();
        }
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
                modeloInventario.removeRow(num2);
                num2 = num2 - 1;
            } else {
            }

            num2 = num2 + 1;
        }

        tblProductos.removeEditor();
        tblInventario.removeEditor();
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
                        metodos.msgAdvertencia(factura, "Resolución sin consecutivo, verifique para que pueda continuar");
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
                            metodos.msgAdvertencia(factura, "Resolución sin consecutivo, verifique para que pueda continuar");
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

        Boolean tipoDomicilio = false;
        if (tipoProceso.equals("mesa")) {
            tipoProceso = "facturacion";
            tipoDomicilio = true;
        }

        switch (tipoProceso) {
            case "facturacion":

                //VERIFICAMOS SI TIENE UN TITULO ADICIONAL
                String titulo;
                if (tipoProceso.equals("facturacion") && instancias.getConfiguraciones().isRestaurante()) {
                    if ((Boolean) datos[54]) {
                        titulo = "Turno";
                    } else {
                        titulo = "";
                    }
                } else if ((Boolean) datos[57]) {
                    titulo = "Num Fact2";
                } else {
                    titulo = "";
                }

                String tipo = "";
                String impresora = "";

                //OBTENEMOS EL TIPO DE FACTURA QUE SE VA A IMPRIMIR
                if (rdMediaCarta.isSelected()) {
                    tipo = "factura" + instancias.getRegimen();
                    impresora = datos[82].toString();
                } else if (rdCarta.isSelected()) {
                    tipo = "facturaCompleta" + instancias.getRegimen();
                    impresora = datos[83].toString();
                } else if (rdPos.isSelected()) {
                    tipo = "pos" + instancias.getRegimen();
                    impresora = datos[81].toString();
                }

                //VALIDACIÓN PARA VER IMPOCONSUMO EN LA IMPRESION
                String impoconsumo = datos[84].toString();

                //VALIDACIÓN PARA VER RETENCIONES EN LA IMPRESION
                String retenciones = datos[85].toString();

                //VALIDACION PARA LA IMPRESION CON CODIGO, SIN CODIGO O IMEI
                String tipoImpr = instancias.getConfiguraciones().getTipoImpresion();
                if (tipoImpr.equals("Con-Codigo")) {
                } else if (tipoImpr.equals("Sin-Codigo")) {
                    tipo = tipo + "1";
                } else if (tipoImpr.equals("Imei")) {
                    tipo = tipo + "Imei";
                }

                //CREAMOS OBJETO PARA HACER CONTEO DE GRUPOS 
                Object[] grupos = new Object[tblProductos.getRowCount()];

                //HACEMOS CONTEO DE GRUPOS DE LOS PRODUCTOS DE FACTURA
                int ser = 0;
                if ((Boolean) datos[100]) {
                    for (int i = 0; i < tblProductos.getRowCount(); i++) {
                        String grupo = tblProductos.getValueAt(i, 34).toString();

                        Boolean entro = false;
                        for (int j = 0; j < grupos.length; j++) {
                            if (grupo.equals(grupos[j])) {
                                entro = true;
                            }
                        }

                        if (!entro) {
                            grupos[ser] = grupo;
                            ser++;
                        }
                    }
                }

                //OBTENEMOS LA SENTENCIA SQL
                String condicion;
                if ((Boolean) datos[50] && instancias.getConfiguraciones().isRestaurante()) {
                    condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' ");
                } else {
                    if (instancias.getConfiguraciones().isRestaurante()) {
                        condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' ");
                    } else {
                        condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' ");
                    }
                }

                //SI ESTA SELECCIONADA IMPRESION POR GRUPO
                if ((Boolean) datos[100]) {

                    for (int i = 0; i < ser; i++) {

                        String impresoraGrupo = "Sin_impresora";
                        Object[] datosGrupo;

                        String grupoActual = grupos[i].toString();

                        if (!grupoActual.equals("")) {
                            datosGrupo = instancias.getSql().getDatosGrupo(grupoActual);
                            impresoraGrupo = datosGrupo[5].toString();
                        } else {
                            impresoraGrupo = "Sin_impresora";
                        }

                        if ((Boolean) datos[50] && instancias.getConfiguraciones().isRestaurante()) {
                            if (grupoActual.equals("")) {
                                condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
                            } else {
                                condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupos[i] + "' ");
                            }
                        } else {
                            if (instancias.getConfiguraciones().isRestaurante()) {
                                if (grupoActual.equals("")) {
                                    condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
                                } else {
                                    condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupos[i] + "' ");
                                }
                            } else {
                                if (grupoActual.equals("")) {
                                    condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
                                } else {
                                    condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupos[i] + "' ");
                                }
                            }
                        }

                        String infoEmpresa = metodosGenerales.convertToMultiline(
                                instancias.getInformacionEmpresaCompleto()
                                + "\n" + instancias.getResolucion());

                        //System.out.println("aqui tiene que salir");
                        //System.out.println(infoEmpresa);
                        if (instancias.getRegimen().equals("")) {
                            instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, "Original", pie,
                                    tipo, factura2, !(Boolean) datos[68], titulo, impresoraGrupo, impoconsumo, retenciones, condicion, false);

                        } else {
                            instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, "Original", pie,
                                    tipo, factura2, !(Boolean) datos[68], titulo, impresoraGrupo, impoconsumo, retenciones, condicion, false);
                        }

                        try {
                            Thread.sleep(500);
                        } catch (Exception e) {
                        }
                    }
                } else {
                    instancias.getReporte().ver_Factura(observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Original",
                            pie, tipo, factura2, !(Boolean) datos[68], titulo, impresora, impoconsumo, retenciones, condicion, false);
                }

                //HACEMOS UN STOP PARA NO SATURAR LA IMPRESORA
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }

                if ((Boolean) datos[63]) {

                    String copias = "";
                    try {
                        copias = metodos.msgIngresarEnter(null, "Cantidad de copias");
                    } catch (Exception e) {
                    }

                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {

                                if ((Boolean) datos[100]) {
                                    for (int ix = 0; ix < ser; ix++) {

                                        String impresoraGrupo = "Sin_impresora";
                                        Object[] datosGrupo;

                                        String grupoActual = grupos[ix].toString();

                                        if (!grupoActual.equals("")) {
                                            datosGrupo = instancias.getSql().getDatosGrupo(grupoActual);
                                            impresoraGrupo = datosGrupo[5].toString();
                                        } else {
                                            impresoraGrupo = "Sin_impresora";
                                        }

                                        if ((Boolean) datos[50] && instancias.getConfiguraciones().isRestaurante()) {
                                            if (grupoActual.equals("")) {
                                                condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
//                                                condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo is Null ";
                                            } else {
                                                condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ");
//                                                condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo = '" + grupoActual + "'  ";
                                            }
                                        } else {
                                            if (instancias.getConfiguraciones().isRestaurante()) {
                                                if (grupoActual.equals("")) {
                                                    condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
//                                                    condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo is Null ";
                                                } else {
                                                    condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ");
//                                                    condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ";
                                                }
                                            } else {
                                                if (grupoActual.equals("")) {
                                                    condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
//                                                    condicion = " factura where factura = '" + factura2 + "' and Grupo is Null ";
                                                } else {
                                                    condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ");
//                                                    condicion = " factura where factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ";
                                                }
                                            }
                                        }

                                        String infoEmpresa = metodosGenerales.convertToMultiline(
                                                instancias.getInformacionEmpresaCompleto()
                                                + "\n" + instancias.getResolucion());

                                        instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, "Copia " + (i + 1),
                                                pie, tipo, factura2, !(Boolean) datos[68], titulo, impresoraGrupo, impoconsumo, retenciones, condicion, false);

                                        try {
                                            Thread.sleep(500);
                                        } catch (Exception e) {
                                        }
                                    }
                                } else {
                                    String infoEmpresa = metodosGenerales.convertToMultiline(
                                            instancias.getInformacionEmpresaCompleto()
                                            + "\n" + instancias.getResolucion());

                                    instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, "Copia " + (i + 1),
                                            instancias.getPie(), tipo, factura2, !(Boolean) datos[68], titulo, impresora, impoconsumo, retenciones, condicion, false);

                                }
                            }
                        }
                    } catch (Exception e) {
                    }

                } else {
                    int cantidad;

                    try {
                        cantidad = Integer.parseInt(datos[73].toString());
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {

                            if ((Boolean) datos[100]) {
                                for (int ix = 0; ix < ser; ix++) {

                                    String impresoraGrupo = "Sin_impresora";
                                    Object[] datosGrupo;

                                    String grupoActual = grupos[ix].toString();

                                    if (!grupoActual.equals("")) {
                                        datosGrupo = instancias.getSql().getDatosGrupo(grupoActual);
                                        impresoraGrupo = datosGrupo[5].toString();
                                    } else {
                                        impresoraGrupo = "Sin_impresora";
                                    }

                                    if ((Boolean) datos[50] && instancias.getConfiguraciones().isRestaurante()) {
                                        if (grupoActual.equals("")) {
                                            condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
//                                            condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo is Null ";
                                        } else {
                                            condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ");
//                                            condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo = '" + grupoActual + "'  ";
                                        }
                                    } else {
                                        if (instancias.getConfiguraciones().isRestaurante()) {
                                            if (grupoActual.equals("")) {
                                                condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
//                                                condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo is Null ";
                                            } else {
                                                condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ");
//                                                condicion = " facturaAgrupada where factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ";
                                            }
                                        } else {
                                            if (grupoActual.equals("")) {
                                                condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo is Null ");
//                                                condicion = " factura where factura = '" + factura2 + "' and Grupo is Null ";
                                            } else {
                                                condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ");
//                                                condicion = " factura where factura = '" + factura2 + "' and Grupo = '" + grupoActual + "' ";
                                            }
                                        }
                                    }
                                    String infoEmpresa = metodosGenerales.convertToMultiline(
                                            instancias.getInformacionEmpresaCompleto()
                                            + "\n" + instancias.getResolucion());

                                    instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, "Copia " + (i + 1),
                                            pie, tipo, factura2, !(Boolean) datos[68], titulo, impresoraGrupo, impoconsumo, retenciones, condicion, false);

                                    try {
                                        Thread.sleep(500);
                                    } catch (Exception e) {
                                    }
                                }
                            } else {
                                String infoEmpresa = metodosGenerales.convertToMultiline(
                                        instancias.getInformacionEmpresaCompleto()
                                        + "\n" + instancias.getResolucion());

                                instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, "Copia " + (i + 1),
                                        instancias.getPie(), tipo, factura2, !(Boolean) datos[68], titulo, impresora, impoconsumo, retenciones, condicion, false);

                            }
                        }
                    }
                }

                if (tipoDomicilio) {
                    this.tipoProceso = "mesa";
                    tipoDomicilio = false;
                }

                break;

            case "cotizacion":

                instancias.getReporte().ver_Cotiza(factura, observaciones, instancias.getInformacionEmpresa(), legal, getTipo(), !(Boolean) datos[70]);

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }

                if ((Boolean) datos[65]) {
                    String copias = JOptionPane.showInputDialog("Cantidad de copias");
                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Cotiza(factura, observaciones, instancias.getInformacionEmpresa(), legal, getTipo(), !(Boolean) datos[70]);
                            }
                        }
                    } catch (Exception e) {
                    }
                } else {
                    int cantidad;

                    try {
                        cantidad = Integer.parseInt(datos[75].toString());
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Cotiza(factura, observaciones, instancias.getInformacionEmpresa(), legal, getTipo(), !(Boolean) datos[70]);
                        }
                    }
                }
                break;
            case "orden":

                if (instancias.getConfiguraciones().isServicioAutomotor()) {
                    instancias.getReporte().ver_oServicio(factura, observaciones, !(Boolean) datos[69], txtTipoVehiculo.getText(), "");

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }

                    if ((Boolean) datos[64]) {
                        String copias = JOptionPane.showInputDialog("Cantidad de copias");

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_oServicio(factura, observaciones, !(Boolean) datos[69], txtTipoVehiculo.getText(), "");
                                }
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        int cantidad;

                        try {
                            cantidad = Integer.parseInt(datos[74].toString());
                        } catch (Exception e) {
                            cantidad = 0;
                        }

                        if (cantidad > 0) {
                            for (int i = 0; i < cantidad; i++) {
                                instancias.getReporte().ver_oServicio(factura, observaciones, !(Boolean) datos[69], txtTipoVehiculo.getText(), "");
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

                    instancias.getReporte().ver_oServicio(factura, observaciones, !(Boolean) datos[69], "", tipoImp);

                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                    }

                    if ((Boolean) datos[64]) {
                        String copias = JOptionPane.showInputDialog("Cantidad de copias");

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_oServicio(factura, observaciones, !(Boolean) datos[69], "", "OrdenNormal");
                                }
                            }
                        } catch (Exception e) {
                        }
                    } else {
                        int cantidad;

                        try {
                            cantidad = Integer.parseInt(datos[74].toString());
                        } catch (Exception e) {
                            cantidad = 0;
                        }

                        if (cantidad > 0) {
                            for (int i = 0; i < cantidad; i++) {
                                instancias.getReporte().ver_oServicio(factura, observaciones, !(Boolean) datos[69], "", "OrdenNormal");
                            }
                        }
                    }
                }

                break;
            case "pedido":
                String tipo1 = this.getTipo();

                instancias.getReporte().ver_Pedido(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !(Boolean) datos[72]);

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }

                if ((Boolean) datos[67]) {
                    String copias = JOptionPane.showInputDialog("Cantidad de copias");
                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Pedido(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Copia " + (i + 1), instancias.getPie(), this.getTipo(), !(Boolean) datos[72]);
                            }
                        }
                    } catch (Exception e) {
                    }
                } else {
                    int cantidad;

                    try {
                        cantidad = Integer.parseInt(datos[77].toString());
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Pedido(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Copia " + (i + 1), instancias.getPie(), this.getTipo(), !(Boolean) datos[72]);
                        }
                    }
                }
                break;
            case "separe":
                instancias.getReporte().ver_Separe(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !(Boolean) datos[71]);

                if ((Boolean) datos[66]) {
                    String copias = JOptionPane.showInputDialog("Cantidad de copias");
                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Separe(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !(Boolean) datos[71]);
                            }
                        }
                    } catch (Exception e) {
                    }
                } else {
                    int cantidad;

                    try {
                        cantidad = Integer.parseInt(datos[76].toString());
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Separe(factura2, observaciones, instancias.getInformacionEmpresaCompleto(), legal, "", instancias.getPie(), this.getTipo(), !(Boolean) datos[71]);
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

    public factura getFactura() {
        return factura;
    }

    public void setFactura(factura factura) {
        this.factura = factura;
    }

    public int getCantidadProductos() {
        return tblProductos.getRowCount();
    }

    public void anularOrdenServicio(String nota, String orden, String base) {

        if (!instancias.getSql().modificarOServicioFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), orden,
                nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
            metodos.msgError(null, "Error al agregar la fecha de la anulación");
            return;
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {

            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), base);

            double cantidad;
            double fisicoInventario;

            try {
                cantidad = Double.parseDouble(producto.getOrdenServicio().replace(",", "."));
            } catch (Exception e) {
                cantidad = 0;
            }

            try {
                fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
            } catch (Exception e) {
                fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
            }

            double cantidadTabla = Double.parseDouble(tblProductos.getValueAt(i, 3).toString().replace(",", "."));
            double total = cantidad - cantidadTabla;
            fisicoInventario = fisicoInventario + cantidadTabla;

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

            instancias.getSql().modificarInventario("ordenServicio", total1, tblProductos.getValueAt(i, 32).toString(), base);
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 32).toString(), base);
        }

        metodos.msgExito(null, "Orden de servicio anulado con éxito");

        if (!instancias.getSql().aumentarConsecutivo("ANULA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ANULA")[0]) + 1)) {
            metodos.msgError(null, "Hubo un problema al guardar en el consecutivo de la anulación");
        }

        limpiar(true, "SI");
    }

    public void anularPedido(String nota, String pedido, String base) {

        if (!instancias.getSql().modificarPedidoFechaAnulacion(metodos.fechaConsulta(metodosGenerales.fecha()), instancias.getUsuario(), pedido,
                nota, true, (String) instancias.getSql().getNumConsecutivo("ANULA")[0])) {
            metodos.msgError(null, "Hubo un problema al agregar la fecha de la anulación");
            return;
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {

            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), base);

            if (!producto.getUsuario().equals(TipoProducto.GENERICO.getValue())) {

                String preparacion = "";
                try {
                    preparacion = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                if (preparacion.equals("")) {
                    Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 32).toString());
                    for (int k = 0; k < productos.length; k++) {
                        String codigo = productos[k][0].toString();
                        String cant = productos[k][1].toString();
                        ndProducto insumo = instancias.getSql().getDatosProducto(codigo, base);

                        double cantidad;
                        double fisicoInventario;

                        try {
                            cantidad = Double.parseDouble(insumo.getPedidos().replace(",", "."));
                        } catch (Exception e) {
                            cantidad = 0;
                        }

                        try {
                            fisicoInventario = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                        } catch (Exception e) {
                            fisicoInventario = 0;
                        }

                        double cantidadTabla;
                        try {
                            cantidadTabla = Double.parseDouble(cant);
                        } catch (Exception e) {
                            cantidadTabla = Double.parseDouble(cant.substring(0, cant.length() - 2));
                        }

                        double total = cantidad - cantidadTabla;
                        fisicoInventario = fisicoInventario + cantidadTabla;

                        String total1 = String.valueOf(df.format(total)).replace(".", ",");
                        String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                        instancias.getSql().modificarInventario("pedidos", total1, codigo, base);
                        instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, base);
                    }
                } else {
                    if (ParserPreparacion.tienePreparacion(preparacion)) {
                        for (OpcionPreparacion opcion : ParserPreparacion.opciones(preparacion)) {
                            if (opcion.esAdicion()) {
                                String codigo = opcion.getCodigo();
                                String cant = opcion.getCantidad();
                                String estado = opcion.getEstado();

                                if (estado.equals(" true")) {

                                    ndProducto insumo = instancias.getSql().getDatosProducto(codigo, base);

                                    double cantidad;
                                    double fisicoInventario;

                                    try {
                                        cantidad = Double.parseDouble(insumo.getPedidos().replace(",", "."));
                                    } catch (Exception e) {
                                        cantidad = 0;
                                    }

                                    try {
                                        fisicoInventario = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                                    } catch (Exception e) {
                                        fisicoInventario = Double.parseDouble(insumo.getInventario().replace(",", "."));
                                    }

                                    double cantidadTabla;
                                    try {
                                        cantidadTabla = Double.parseDouble(cant);
                                    } catch (Exception e) {
                                        cantidadTabla = Double.parseDouble(cant.substring(0, cant.length() - 2));
                                    }

                                    double total = cantidad - cantidadTabla;
                                    fisicoInventario = fisicoInventario + cantidadTabla;

                                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

                                    instancias.getSql().modificarInventario("pedidos", total1, codigo, base);
                                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, base);
                                }
                            }
                        }
                    }
                }
            }

            double cantidad;
            double fisicoInventario;

            try {
                cantidad = Double.parseDouble(producto.getPedidos().replace(",", "."));
            } catch (Exception e) {
                cantidad = 0;
            }

            try {
                fisicoInventario = Double.parseDouble(producto.getFisicoInventario().replace(",", "."));
            } catch (Exception e) {
                fisicoInventario = Double.parseDouble(producto.getInventario().replace(",", "."));
            }

            double cantidadTabla = Double.parseDouble(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
            double total = cantidad - cantidadTabla;
            fisicoInventario = fisicoInventario + cantidadTabla;

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

            instancias.getSql().modificarInventario("pedidos", total1, tblProductos.getValueAt(i, 32).toString(), base);
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 32).toString(), base);

            String idProd = "";
            try {
                idProd = tblProductos.getValueAt(i, 29).toString();
            } catch (Exception e) {
            }

            String tipo = "";
            if (producto.getTipoProducto() != null) {
                if (producto.getTipoProducto().equals("IMEI")) {
                    tipo = "Imei";
                } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                    tipo = "Fecha/Lote";
                } else if (producto.getTipoProducto().equals("Color")) {
                    tipo = "Color";
                } else if (producto.getTipoProducto().equals("Serial")) {
                    tipo = "Serial";
                } else if (producto.getTipoProducto().equals("Talla")) {
                    tipo = "Talla";
                } else if (producto.getTipoProducto().equals("ColorTalla")) {
                    tipo = "ColorTalla";
                } else if (producto.getTipoProducto().equals("SerialColor")) {
                    tipo = "SerialColor";
                } else {
                    tipo = "";
                }
            }

            if (!idProd.equals("")) {
                if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                    instancias.getSql().modificarEstadoDetalleProductos(idProd, "DISPONIBLE");
                } else {
                    BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(idProd).replace(",", "."));
                    BigDecimal cantidadTabla1 = new BigDecimal(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
                    BigDecimal cantidadFinal = cantidadActual.add(cantidadTabla1);
                    instancias.getSql().modificarCantidadesDetalleProductos(idProd, cantidadFinal);
                }

            }
        }

        metodos.msgExito(null, "Pedido anulado con éxito");
        if (!instancias.getSql().aumentarConsecutivo("ANULA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("ANULA")[0]) + 1)) {
            metodos.msgError(null, "Error al guardar en el consecutivo de la anulación");
        }

        limpiar(true, "SI");
    }

    public void cargarCongelada(String mesa, String tipo, String titulo) {

        tapControl.setSelectedIndex(0);

        lbCargarDocumento.setVisible(false);
        txtCargar.setVisible(false);

        btnReImprimir.setVisible(false);
        pnlCambiarMesa.setVisible(false);

        if (tipo.equals("MESA")) {
            lbFacturaNo.setText("MESA No.");
            txtDiasPlazo.setEnabled(true);
        } else if (tipo.equals("CONG")) {
            lbFacturaNo.setText("CONGELADA No.");
            txtDiasPlazo.setEnabled(true);
        } else {
            lbFacturaNo.setText("DOMICILIO No.");
            txtDiasPlazo.setText("1");
            txtDiasPlazo.setEnabled(false);
        }

        lbNoFactura.setText((String) instancias.getSql().getNumConsecutivo("CONGELADA")[0]);
        limpiar(true, "");
        mesaCongelada = true;

        if (tipo.equals("MESA")) {
            lbTitulo.setText(titulo);
            instancias.setTitulo(titulo);
        } else if (tipo.equals("CONG")) {
            lbTitulo.setText(titulo);
            instancias.setTitulo(titulo.replace("                    ", ""));
        } else {
            lbTitulo.setText("DOMICILIO");
            instancias.setTitulo("DOMICILIO");
        }

        if (instancias.getTipoImpresion().equals("Pos")) {
            rdPos.setSelected(true);
        }

        instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "OCUPADO");

        if (!mesa.equals("")) {
//            btnGuardar.setVisible(false);
//            btnGuardar1.setVisible(false);

            btnGuardar.setText("FACTURAR");
            btnActualizar.setVisible(true);
            btnActualizar.setEnabled(true);

            if (lbTitulo.getText().contains("Mesa.")) {
                pnlCambiarMesa.setVisible(true);
            }

            btnReImprimir.setEnabled(true);
            btnReImprimir.setVisible(true);

            ndCongelada congelada = instancias.getSql().getDatosCongelada1(mesa);

            txtNit.setText(congelada.getCliente());
            cargarCliente(congelada.getCliente());
            cmbVendedor.setSelectedItem(congelada.getVendedor());
            txtTurno.setText(congelada.getTurno());

            Object[][] mat = instancias.getSql().getProductosVenta(congelada.getIdFactura());
            consecutivoMesa = congelada.getIdFactura().replace("CONGELADA-", "");
            productosMovimientos = new Object[mat.length];
            cargandoCongelada = true;

            int i = 0;
            for (Object[] reg : mat) {
                cargarProducto((String) reg[0], new Double((String) reg[4]) + "", Integer.parseInt((String) reg[3]),
                        (String) reg[12], "", (String) reg[13], false, "", "", "", "", "");

                tblProductos.setValueAt(new Double((String) reg[4]), i, 3);
                tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(reg[5])), i, 2);
                tblProductos.setValueAt(reg[10], i, 31);
                tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(reg[6])), i, 6);
                tblProductos.setValueAt(reg[11], i, 5);
                tblProductos.setValueAt(Integer.parseInt(reg[15].toString()), i, 23);
                tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(reg[14].toString())), i, 8);
                tblProductos.setValueAt(reg[7], i, 21);
                productosMovimientos[i] = reg[0];
                calcularTabla(i, false);
                i++;

            }

            String baseUtilizada = "bdProductos";
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                tblProductos.setValueAt("PLATO-" + j, j, 30);

                ndProducto nodoProd = instancias.getSql().getDatosProducto(tblProductos.getValueAt(j, 32).toString(), baseUtilizada);

                if (nodoProd.getUsuario().equals("FACTURA")) {
                    String preparacion = "";
                    try {
                        preparacion = tblProductos.getValueAt(j, 21).toString();
                    } catch (Exception e) {
                    }

                    if (!preparacion.equals("")) {
                        for (OpcionPreparacion opcion : ParserPreparacion.opciones(preparacion)) {
                            if (opcion.esAdicion()) {
                                String codigo = opcion.getCodigo();
                                String cant = opcion.getCantidad();
                                String estado = opcion.getEstado();

                                if (estado.equals(" true")) {
                                    ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                    if (nodo1.getGrupo() != null) {
                                        if (!nodo1.getGrupo().equals("GRP-02")) {
                                            Double fisicoInventario = Double.parseDouble(nodo1.getFisicoInventario().replace(",", "."));
                                            Double congeladas = Double.parseDouble(nodo1.getCongelada().replace(",", "."));

                                            fisicoInventario = fisicoInventario + Double.parseDouble(cant.replace(",", "."));
                                            congeladas = congeladas - Double.parseDouble(cant.replace(",", "."));

                                            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                                            String congeladas1 = String.valueOf(df.format(congeladas)).replace(".", ",");

                                            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                                            instancias.getSql().modificarInventario("congelada", congeladas1, codigo, baseUtilizada);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(j, 32).toString());
                        for (int k = 0; k < productos.length; k++) {

                            String codigo = productos[k][0].toString();
                            String cant = productos[k][1].toString();

                            ndProducto insumo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                            Double fisicoInventario = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                            Double congeladas = Double.parseDouble(insumo.getCongelada().replace(",", "."));

                            fisicoInventario = fisicoInventario + Double.parseDouble(cant.replace(",", "."));
                            congeladas = congeladas - Double.parseDouble(cant.replace(",", "."));

                            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                            String congeladas1 = String.valueOf(df.format(congeladas)).replace(".", ",");

                            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                            instancias.getSql().modificarInventario("congelada", congeladas1, codigo, baseUtilizada);
                        }
                    }
                } else {
                    Double fisicoInventario = Double.parseDouble(nodoProd.getFisicoInventario().replace(",", "."));
                    Double congeladas = Double.parseDouble(nodoProd.getCongelada().replace(",", "."));

                    fisicoInventario = fisicoInventario + Double.parseDouble(tblProductos.getValueAt(j, 13).toString().replace(",", "."));
                    congeladas = congeladas - Double.parseDouble(tblProductos.getValueAt(j, 13).toString().replace(",", "."));

                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                    String congeladas1 = String.valueOf(df.format(congeladas)).replace(".", ",");

                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(j, 32).toString(), baseUtilizada);
                    instancias.getSql().modificarInventario("congelada", congeladas1, tblProductos.getValueAt(j, 32).toString(), baseUtilizada);

                    tblInventario.setValueAt(big.setNumero(big.getMoneda(fisicoInventario1)), j, 1);

                    BigDecimal num1 = big.getMoneda(tblInventario.getValueAt(j, 1).toString());
                    BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(j, 3).toString().replace(",", "."));
                    BigDecimal total = num1.subtract(num2);
                    tblInventario.setValueAt(big.setNumero(total), j, 2);
                }

                // DESCONTAR DEL INVENTARIO DETALLADO //
                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    String cod = "";
                    try {
                        cod = tblProductos.getValueAt(j, 29).toString();
                    } catch (Exception e) {
                    }

                    if (!cod.equals("")) {
                        String tipoProd = "";

                        if (nodoProd.getTipoProducto() != null) {
                            if (nodoProd.getTipoProducto().equals("IMEI")) {
                                tipoProd = "Imei";
                            } else if (nodoProd.getTipoProducto().equals("Fecha/Lote")) {
                                tipoProd = "Fecha/Lote";
                            } else if (nodoProd.getTipoProducto().equals("Color")) {
                                tipoProd = "Color";
                            } else if (nodoProd.getTipoProducto().equals("Serial")) {
                                tipoProd = "Serial";
                            } else if (nodoProd.getTipoProducto().equals("Talla")) {
                                tipoProd = "Talla";
                            } else if (nodoProd.getTipoProducto().equals("ColorTalla")) {
                                tipoProd = "ColorTalla";
                            } else if (nodoProd.getTipoProducto().equals("SerialColor")) {
                                tipoProd = "SerialColor";
                            } else {
                                tipoProd = "";
                            }
                        }

                        if (tipoProd.equals("Imei") || tipoProd.equals("Serial") || tipoProd.equals("SerialColor")) {
                            instancias.getSql().modificarEstadoDetalleProductos(cod, "DISPONIBLE");
                        } else {
                            BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                            BigDecimal cantidadTabla = new BigDecimal(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
                            BigDecimal cantidadFinal = cantidadActual.add(cantidadTabla);
                            instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                        }
                    }
                }
                // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 
            }

            borrarAdiciones();
            lbNoFactura.setText(congelada.getIdFactura().replace("CONGELADA-", ""));
            txtObservaciones.setText(congelada.getObservacion());
            cargandoCongelada = false;

            if (!instancias.getUsuario().equals("ADMIN")) {
                if (!(Boolean) datos[107]) {
                    Object[][] vendedores = instancias.getSql().getVendedores1();

                    for (int ix = 0; ix < vendedores.length; ix++) {
                        String asociado = "";
                        try {
                            asociado = vendedores[ix][1].toString();
                        } catch (Exception e) {
                        }

                        if (instancias.getUsuario().equals(asociado)) {
                            btnGuardar.setVisible(false);
                            btnGuardar1.setVisible(false);
                        }
                    }
                }
            }

        } else {
            btnGuardar.setEnabled(true);
            btnGuardar.setVisible(true);
            btnGuardar.setText("GUARDAR");

            if (instancias.getConfiguraciones().isRestaurante()) {
                if (tipo.equals("DOMICILIO")) {
                    btnGuardar1.setVisible(true);
                } else {
                    btnGuardar1.setVisible(false);
                }
            }

            btnReImprimir.setVisible(false);
            btnActualizar.setVisible(false);
        }
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
        tapControl = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        scrProductos1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        lbProducto = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnBusProd = new javax.swing.JButton();
        scrInventario = new javax.swing.JScrollPane();
        tblInventario = new javax.swing.JTable();
        lbProducto1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        txtPorcentaje = new javax.swing.JTextField();
        lbVendedor1 = new javax.swing.JLabel();
        txtCargar = new javax.swing.JTextField();
        btnPendientes = new javax.swing.JButton();
        lbCargarDocumento = new javax.swing.JLabel();
        pnlVisor = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblImagenes = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
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
        jPanel4 = new javax.swing.JPanel();
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
        pnlGarantia = new javax.swing.JPanel();
        txtGarantiaSeñal = new javax.swing.JTextField();
        txtGarantiaFuncionamiento = new javax.swing.JTextField();
        cmbSeñal = new javax.swing.JComboBox();
        cmbFuncionamiento = new javax.swing.JComboBox();
        cmbListaPrecio = new javax.swing.JComboBox();
        rdTipoNormal = new javax.swing.JRadioButton();
        rdTipoCopago = new javax.swing.JRadioButton();
        txtDescGeneral = new javax.swing.JTextField();
        btnVolver = new javax.swing.JLabel();
        btnVolver1 = new javax.swing.JLabel();
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
        btnGuardar1 = new javax.swing.JButton();
        btnReImprimir = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
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
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

        tblProductos.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripción", "Valor/Unit", "Cant", "Subtotal", "Desc %", "Desc", "Iva %", "Impo", "Total", "Ubicación", "Referencia", "plu", "cant2", "ponderado", "Utilidad", "Estado", "Copago", "datoGrupo", "Pago Tercero", "Utilidad1", "Orden/Aviso", "Borrar", "Impo %", "Orden", "Aviso", "F. Entrega", "Detalle", "Lote", "IdProd", "paraComanda", "permisoDesc", "idSistema", "Iva", "Grupo", "Medida", "ControlInv"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true, true, false, false, false, false, false, false, false, false, false, false, true, false, true, true, true, false, true, true, true, true, false, false, false, true, true, true, true, true, false, true
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
            tblProductos.getColumnModel().getColumn(7).setMinWidth(35);
            tblProductos.getColumnModel().getColumn(7).setPreferredWidth(35);
            tblProductos.getColumnModel().getColumn(7).setMaxWidth(35);
            tblProductos.getColumnModel().getColumn(8).setMinWidth(80);
            tblProductos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(8).setMaxWidth(140);
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
            tblProductos.getColumnModel().getColumn(19).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(19).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(19).setMaxWidth(100);
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
            tblProductos.getColumnModel().getColumn(24).setMinWidth(20);
            tblProductos.getColumnModel().getColumn(24).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(24).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(25).setMinWidth(20);
            tblProductos.getColumnModel().getColumn(25).setPreferredWidth(80);
            tblProductos.getColumnModel().getColumn(25).setMaxWidth(120);
            tblProductos.getColumnModel().getColumn(26).setMinWidth(100);
            tblProductos.getColumnModel().getColumn(26).setPreferredWidth(100);
            tblProductos.getColumnModel().getColumn(26).setMaxWidth(100);
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
        }

        lbProducto.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
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

        tblInventario.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        tblInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Actual", "Final"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInventario.setToolTipText("Doble Click para cambiar la lista");
        tblInventario.setMinimumSize(new java.awt.Dimension(45, 203));
        tblInventario.setRowHeight(30);
        tblInventario.getTableHeader().setReorderingAllowed(false);
        tblInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventarioMouseClicked(evt);
            }
        });
        scrInventario.setViewportView(tblInventario);
        if (tblInventario.getColumnModel().getColumnCount() > 0) {
            tblInventario.getColumnModel().getColumn(0).setMinWidth(30);
            tblInventario.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblInventario.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        lbProducto1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbProducto1.setText("Cantidad:");
        lbProducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbProducto1KeyReleased(evt);
            }
        });

        txtCant.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        txtCant.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCant.setText("1");
        txtCant.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtCant.setEnabled(false);
        txtCant.setName("combo"); // NOI18N
        txtCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantActionPerformed(evt);
            }
        });
        txtCant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantFocusGained(evt);
            }
        });
        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantKeyReleased(evt);
            }
        });

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

        lbVendedor1.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        lbVendedor1.setText("Descuento:");

        txtCargar.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtCargar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCargar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCargarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCargarKeyTyped(evt);
            }
        });

        btnPendientes.setBackground(new java.awt.Color(242, 244, 244));
        btnPendientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPendientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/listado.png"))); // NOI18N
        btnPendientes.setText("COTIZAS PEND");
        btnPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnPendientes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPendientes.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendientesActionPerformed(evt);
            }
        });

        lbCargarDocumento.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        lbCargarDocumento.setText("Cargar documento:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrProductos1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbProducto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBusProd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbCargarDocumento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPendientes, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 267, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbVendedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbVendedor1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPendientes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lbCargarDocumento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCant, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProducto1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCodigoProducto, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBusProd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCargar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPorcentaje))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                    .addComponent(scrProductos1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );

        tapControl.addTab("Facturación", jPanel2);

        pnlVisor.setBackground(new java.awt.Color(255, 255, 255));
        pnlVisor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 0, 13))); // NOI18N

        tblImagenes.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        tblImagenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                " ", " ", " ", "", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblImagenes.setRowHeight(240);
        tblImagenes.getTableHeader().setResizingAllowed(false);
        tblImagenes.getTableHeader().setReorderingAllowed(false);
        tblImagenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblImagenesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblImagenes);

        javax.swing.GroupLayout pnlVisorLayout = new javax.swing.GroupLayout(pnlVisor);
        pnlVisor.setLayout(pnlVisorLayout);
        pnlVisorLayout.setHorizontalGroup(
            pnlVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVisorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1221, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlVisorLayout.setVerticalGroup(
            pnlVisorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVisorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapControl.addTab("Grupos", pnlVisor);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Century Gothic", 1, 18))); // NOI18N

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(lbNombre14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbNombre10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbNombre5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtPlaca))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lbNombre7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lbNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtKm)
                                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lbNombre11)
                                            .addComponent(lbNombre8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMotor, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(txtNumChasis, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbNombre13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(btnNuevaParte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevaParte1)))
                        .addGap(0, 422, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbNombre9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNumChasis, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lbNombre7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombre10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNombre13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtColor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevaParte, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnNuevaParte1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );

        tapControl.addTab("Registro Orden", jPanel6);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(425, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
        );

        tapControl.addTab("Facturación Automatica", jPanel4);

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

        pnlGarantia.setBackground(new java.awt.Color(255, 255, 255));

        txtGarantiaSeñal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGarantiaSeñal.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        txtGarantiaFuncionamiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtGarantiaFuncionamiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGarantiaFuncionamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGarantiaFuncionamientoActionPerformed(evt);
            }
        });

        cmbSeñal.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbSeñal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "año", "meses" }));

        cmbFuncionamiento.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        cmbFuncionamiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "año", "meses" }));
        cmbFuncionamiento.setMinimumSize(new java.awt.Dimension(50, 20));
        cmbFuncionamiento.setPreferredSize(new java.awt.Dimension(50, 20));

        javax.swing.GroupLayout pnlGarantiaLayout = new javax.swing.GroupLayout(pnlGarantia);
        pnlGarantia.setLayout(pnlGarantiaLayout);
        pnlGarantiaLayout.setHorizontalGroup(
            pnlGarantiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGarantiaLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(txtGarantiaSeñal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cmbSeñal, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142)
                .addComponent(txtGarantiaFuncionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(cmbFuncionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGarantiaLayout.setVerticalGroup(
            pnlGarantiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGarantiaLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(pnlGarantiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGarantiaSeñal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSeñal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGarantiaFuncionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFuncionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(pnlGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbListaPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        btnVolver.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        btnVolver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anterior.png"))); // NOI18N
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolverMouseClicked(evt);
            }
        });

        btnVolver1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnVolver1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVolver1.setText("VOLVER");
        btnVolver1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVolver1MouseClicked(evt);
            }
        });

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
        txtTotal.setText("Total: 0");

        lbSubtotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbSubtotal.setText("Subtotal:");

        txtSubTotal.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtSubTotal.setText("0");

        txtIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtIva.setText("IVA:");

        txtTotalIva.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalIva.setText("0");

        lbTotalDescuento.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbTotalDescuento.setText("Descuentos:");
        lbTotalDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTotalDescuentoMouseClicked(evt);
            }
        });

        txtTotalDescuentos.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalDescuentos.setText("0");

        lbImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        lbImpoconsumo.setText("Impoconsumo:");

        txtTotalImpoconsumo.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTotalImpoconsumo.setText("0");

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
        txtRtf.setText("0");

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
        txtRiva.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTotalDescuento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                .addComponent(txtIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lbImpoconsumo)
                            .addComponent(chkReteIva, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(txtRtf, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
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

        btnGuardar1.setBackground(new java.awt.Color(46, 204, 113));
        btnGuardar1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        btnGuardar1.setToolTipText("Ctrl+I");
        btnGuardar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnGuardar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGuardar1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnGuardar1.setMargin(new java.awt.Insets(2, 14, 2, 5));
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        btnGuardar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btnGuardar1KeyReleased(evt);
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

        btnActualizar.setBackground(new java.awt.Color(93, 173, 226));
        btnActualizar.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/actualizar.png"))); // NOI18N
        btnActualizar.setText("MODIFICAR");
        btnActualizar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setEnabled(false);
        btnActualizar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnActualizar.setMargin(new java.awt.Insets(2, 7, 2, 5));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnReImprimir)
                .addGap(3, 3, 3)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        lbOtroConsecutivo.setText("Otro Consecutivo:");
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
                                    .addComponent(lbOtroConsecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlCambiarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(73, 73, 73))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(0, 0, 0)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlOcultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tapControl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlCredito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnVolver1))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addComponent(tapControl)
                        .addGap(5, 5, 5)
                        .addComponent(pnlCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlOcultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );

        jScrollPane2.setViewportView(pnlFormulario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
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

        return tipoComprobante;
    }

    private boolean validacionesGeneralesDeFacturacion(ModeloContacto datosCliente) {
        String tipoComprobante = obtenerTipoComprobante();
        String vendedor = "";

        if (cmbVendedor.getItemCount() > 0) {
            vendedor = cmbVendedor.getSelectedItem().toString();
        }

        if (!squemaFacturacion.validaciones_facturacion(datosCliente)) {
            return false;
        }

        if (Constantes.esFacturacionElectronica(tipoComprobante)
                && (tipoProceso.equals(TipoDocumento.FACTURACION.getValor())
                || tipoProceso.equals(TipoDocumento.MESA.getValor()))) {
            if (!squemaFacturacionElectronica.validaciones_facturacionElectronica(datosCliente, vendedor, null, false, false)) {
                return false;
            }
        }

        if (!squemaFacturacion.validaciones_detalle_facturacion(tblProductos, tipoComprobante, tipoProceso)) {
            return false;
        }

        return true;
    }

    private String validacionInicialFactura(boolean imprimir) {

        //Recalculamos totales
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            calcularTabla(i, true);
        }

        tblProductos.removeEditor();
        tblInventario.removeEditor();

        ModeloContacto datosCliente = !ID_CLIENTE_CARGADO.equals("") ? instancias.getSql().getDatosTercero(ID_CLIENTE_CARGADO) : new ModeloContacto();
        if (!validacionesGeneralesDeFacturacion(datosCliente)) {
            return "";
        }

        //SI ES DOMICILIO: VALIDA QUE HALLAN RELACIONADO UN USUARIO AL DOMICILIO
        if (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO")) {
            if (txtNit.getText().equals("") || txtNit.getText().equals("1010")) {
                metodos.msgAdvertenciaAjustado(factura, "¡Debe asociar un cliente!");
                return "";
            }

            //SE ASIGNA AUTOMÁTICAMENTE 1 DIA DE PLAZO PARA EL DOMICILIO
            txtDiasPlazo.setText("1");
            calcularDiasPlazo(null);
        }

        //SI ES CUENTA COBRO: VALIDA QUE HALLAN RELACIONADO UN USUARIO A LA CUENTA DE COBRO
        if (tipoProceso.equals("cuentaCobro")) {
            if (txtNit.getText().equals("") || txtNit.getText().equals("1010")) {
                metodos.msgAdvertenciaAjustado(factura, "¡Debe asociar un cliente!");
                return null;
            }

            //VALIDA QUE HALLAN INGRESADO LA CANTIDAD DE INCREMENTO
            if (txtCantIncremento.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "Falta la cantidad para el incremento");
                return null;
            }
        }

        //SI ES UNA FACTURA A CREDITO, SE VALIDA QUE EL CLIENTE TENGA CUPO DE CREDITO
        if (!saltarPasosFactura) {
            if ((Boolean) datos[42]) {
                if (tipoProceso.equals("facturacion")) {
                    if (txtDiasPlazo.getText().equals("0") || txtDiasPlazo.getText().equals("")) {
                    } else {
                        if (big.getMoneda(txtCupo.getText()).compareTo(BigDecimal.ZERO) <= 0) {
                            metodos.msgAdvertenciaAjustado(null, "No tiene cupo de credito");
                            if (metodos.msgPregunta(factura, "¿Desea seguir con la factura?") != 0) {
                                return null;
                            }
                        }
                    }
                }
            }
        }

        //DÍAS DE PLAZO OBLIGATORIO SI ES UN PLAN SEPARE
        if (tipoProceso.equals("separe")) {
            if (txtDiasPlazo.getText().equals("0") || txtDiasPlazo.getText().equals("")) {
                metodos.msgAdvertencia(null, "No ha ingresado días de plazo");
                return null;
            }
        }

        tblProductos.removeEditor();
        tblInventario.removeEditor();

        String baseUtilizada = "bdProductos";
        Boolean facturarSinInventario = (Boolean) datos[79];

        if (!saltarPasosFactura) {
            ResultadoValidacionInventario resultadoValidacion = validarInventarioProductos(baseUtilizada);

            if (instancias.getConfiguraciones().isRestaurante()) {
                agregarAdicionesATabla(baseUtilizada);
            }

            if (!this.tipoProceso.equals("cotizacion") && resultadoValidacion.hayProductosSinInventario()) {
                if (!facturarSinInventario) {
                    metodos.msgError(factura, "No tiene inventario suficiente");
                    return "";
                } else {
                    dlgProductosSinInventario prodSinInventario = new dlgProductosSinInventario(null, true,
                            resultadoValidacion.getProductosSinInventarioTabla(),
                            resultadoValidacion.getProductosSinInventarioDisTabla());
                    prodSinInventario.setVisible(true);
                    if (instancias.getCancelarFactura()) {
                        borrarAdiciones();
                        facturandoPedidos = false;
                        instancias.setCancelarFactura(false);
                        return "";
                    }
                }
            }

            if (instancias.getRegimen().equals("")) {
                if (tipoProceso.equals("facturacion") || (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO"))) {
                    if (!resultadoValidacion.tieneBolsa()) {
                        if (!instancias.getConfiguraciones().isParqueadero()) {
                            if ((Boolean) datos[52]) {
                                int num = 0;
                                try {
                                    num = Integer.parseInt(metodos.msgIngresarEnter(null, "Ingrese # de bolsas"));
                                } catch (Exception e) {
                                    borrarAdiciones();
                                    metodos.msgError(factura, "Número no válido");
                                    return "";
                                }
                                if (num > 0) {
                                    cargarProducto("IMP01", String.valueOf(num), 1, "", "", "", true, "", "", "", "", "");
                                }
                            }
                        }
                    }
                }
            }
        }

        //SI ES MESA, SE SALTAN LOS PASOS DE LA FACTURA
        if (tipoProceso.equals("mesa")) {
            saltarPasosFactura = true;
        }

        //VALIDAMOS SI HACE PAGO A TERCEROS EN LA FACTURA
        int contador = 0;
        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal pago = big.getMoneda(tblProductos.getValueAt(i, 19).toString());
            if (pago.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal venta = big.getMoneda(tblProductos.getValueAt(i, 9).toString());
                BigDecimal pago1 = big.getMoneda(tblProductos.getValueAt(i, 19).toString());

                BigDecimal resta = venta.subtract(pago1);

                tblProductos.setValueAt(resta, i, 20);
                if (resta.compareTo(BigDecimal.ZERO) < 0) {
                    contador = contador + 1;
                }
            }
        }

        if (contador > 0) {
            metodos.msgAdvertencia(factura, "¡Hay algunos productos que no tienen utilidad!");
        }
        //FIN DE VALIDACION DE PAGO A TERCEROS

        //SE VALIDA EL INVENTARIO INGRESADO EN LA ORDEN DE SERVICIO
        if (tipoProceso.equals("orden")) {
            if (instancias.getConfiguraciones().isServicioAutomotor()) {
                Boolean entro = false;
                for (int i = 0; i < tblArticulos.getRowCount(); i++) {
                    if ((Boolean) tblArticulos.getValueAt(i, 2)) {
                        entro = true;
                    }
                }
                if (!entro) {
                    metodos.msgError(factura, "No ha ingresado el inventario");
                    return null;
                }
            }
        }

        //SE VALIDA SI ES UNA FACTURA A CREDITO, Y EL PORCENTAJE DE INTERES ESTA EN 0%
        if (facturaCredito) {
            int interes = Integer.parseInt(txtInteres.getText());
            if (interes == 0) {
                if (metodos.msgPregunta(null, "Interes del 0%. ¿Desea continuar?") != 0) {
                    return "";
                }
            }
        }

        //OBTENEMOS LOS DÍAS DE PLAZO DE LA FACTURA
        int diasPlazo = 0;
        try {
            diasPlazo = Integer.parseInt(txtDiasPlazo.getText());
        } catch (Exception e) {
            diasPlazo = 0;
        }

        //SI LA OPCIÓN DE SISTECREDITO ESTA SELECCIONADA, SE VÁLIDA QUE LOS DIAS DE PLAZO SEA MAYOR A 0
        if (chkSisteCredito.isSelected() && diasPlazo <= 0) {
            metodos.msgAdvertencia(null, "Debe ingresar los días de plazo para facturar por Sistecredito");
            return "";
        }

        //VALIDACIONES SI ES UNA FACTURA A CREDITO
        if (facturaCredito) {
            if (txtCuotas.getText().equals("0") || txtCuotas.getText().equals("")) {
                metodos.msgError(null, "No ha ingresado el número de cuotas");
                return null;
            }
            if (cmbTipoPlazo.getSelectedIndex() == 0) {
                metodos.msgError(null, "No ha seleccionado el tipo de plazo");
                return null;
            }

            if (metodos.fecha(metodos.desdeDate(dtFechaDesenvolso.getCurrent())).equals(metodosGenerales.fecha())) {
                metodos.msgError(null, "Seleccione la fecha de pago");
                return null;
            }

            int x = tblCuotas.getRowCount();
            if (x <= 0) {
                metodos.msgError(null, "No ha ingresado cuotas");
                return null;
            }
        }

        DATOS_CLIENTE_CARGADO = datosCliente;
        return facturar(null, imprimir, "");
    }

    public void modificarPedido(String pedido, Object[][] productos) {

        this.setTipo("pedido");
        txtCargar.setText(pedido);
        cargarMovimiento();

        for (int i = 0; i < productos.length; i++) {
            Boolean entro = false;
            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                if (productos[i][0].equals(tblProductos.getValueAt(j, 32))) {
                    entro = true;
                    tblProductos.setValueAt(productos[i][1].toString(), j, 3);
                    KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
                    tblProductosKeyReleased(x);
                    break;
                }
            }

            if (!entro) {
                cargarProducto(productos[i][0].toString(), productos[i][1].toString(), 1, "", "", "", true, "", "", "", "", "");
            }
            entro = false;
        }

        saltarPasosFactura = true;
        btnActualizarActionPerformed(null);
    }

    public void limpiar(boolean actualizar, String accion) {

        if (tipoProceso.equals("cuentaCobro")) {
            btnActualizar.setVisible(false);
        }

        fechaFacturaAutomatica = "";
        loteCuentasCobro = "";
        instancias.setCancelarFactura(false);

        chkSinEstablecer.setSelected(false);
        txtCantIncremento.setText("");
        txtCantFacturados.setText("");

        dtDesde.setSelectedDate(metodos.haciaDate(metodos.fecha3(metodosGenerales.fecha())));
        dtHasta.setSelectedDate(metodos.haciaDate(metodos.fecha3(metodosGenerales.fecha())));

        chkSisteCredito.setEnabled(false);
        chkSisteCredito.setSelected(false);

        facturandoPedidos = false;
        lbCupo.setVisible(false);
        txtGarantiaFuncionamiento.setText("");
        txtGarantiaSeñal.setText("");

        txtCant.setText(datos[87].toString());
        tblProductos.setEnabled(true);

        setTipo();
        txtNombre.setEnabled(false);
        txtNombre.setEditable(false);

        if (tipoProceso.equals("mesa")) {
            instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
        }

        if (accion.equals("SI")) {
            if (tipoProceso.equals("mesa")) {
                instancias.getSql().eliminar_registro("bdCongelada", " idFactura = '" + "CONGELADA-" + lbNoFactura.getText() + "' ");

                String baseUtilizada = "bdProductos";
                if (productosMovimientos != null) {
                    if (productosMovimientos.length > 0) {
                        for (int k = 0; k < productosMovimientos.length; k++) {
                            String sql1 = " where codigo = '" + productosMovimientos[k] + "' ";

                            String cong = "";
                            if (baseUtilizada.equals("bdProductos")) {
                                cong = "totalizadoCongelada";
                            } else if (baseUtilizada.equals("bdProductosBodega1")) {
                                cong = "totalizadoCongelada1";
                            } else if (baseUtilizada.equals("bdProductosBodega2")) {
                                cong = "totalizadoCongelada2";
                            } else if (baseUtilizada.equals("bdProductosBodega3")) {
                                cong = "totalizadoCongelada3";
                            } else if (baseUtilizada.equals("bdProductosBodega4")) {
                                cong = "totalizadoCongelada4";
                            }

                            Object[][] congelada = instancias.getSql().getTotalizadoCongelada(sql1, cong);

                            double total = 0;
                            try {
                                total = Double.parseDouble(congelada[0][1].toString().replace(",", "."));
                            } catch (Exception e) {
                            }

                            String total1 = String.valueOf(df.format(total)).replace(".", ",");
                            instancias.getSql().modificarInventario("congelada", total1, productosMovimientos[k].toString(), baseUtilizada);

                            ndProducto nodo = instancias.getSql().getDatosProducto(productosMovimientos[k].toString(), baseUtilizada);
                            actualizarFisicoInventario(nodo, productosMovimientos[k].toString(), baseUtilizada);
                        }
                    }
                }
            }
        }

        btnGuardar.setEnabled(true);
        btnGuardar.setVisible(true);

        btnGuardar1.setVisible(true);
        btnGuardar1.setEnabled(true);

//        if (tipo.equals("pedido") || tipo.equals("orden")) {
//            btnGuardar1.setText("GUARDAR/IMPRIMIR");
//            btnGuardar.setText("GUARDAR");
//        } else {
//
//            if (tipo.equals("separe")) {
//                btnGuardar1.setText("FACT/IMPRIMIR");
//                btnGuardar.setText("FACTURAR");
//            }
//
//            btnGuardar1.setText("FACT/IMPRIMIR");
//            btnGuardar.setText("FACTURAR");
//        }
        String turno = "";
        if (txtTurno.isVisible() && instancias.getConfiguraciones().isRestaurante()) {
            turno = instancias.getSql().getTurno();
            txtTurno.setText(turno);
        } else {
            txtTurno.setText("");
        }

        instancias.getMaestra().setTurno(turno);
        instancias.getMaestra().actualizarTurno();

//        lbNombre2.setForeground(Color.red);
        txtPlaca.setEnabled(true);
        txtModelo.setEnabled(true);
        txtTipoVehiculo.setEnabled(true);
        txtNumChasis.setEnabled(true);
        txtMarca.setEnabled(true);
        txtKm.setEnabled(true);
        txtMotor.setEnabled(true);
        txtColor.setEnabled(true);
        txtPlaca.setText("");
        txtModelo.setText("");
        txtTipoVehiculo.setText("");
        txtNumChasis.setText("");
        txtMarca.setText("");
        txtKm.setText("");
        txtMotor.setText("");
        txtColor.setText("");

        cotizaciones = null;
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
        focusDiasPlazo = false;

        //PANEL DE CREDITO
        if (facturaCredito) {
            txtCuotas.setText("");
            txtInteres.setText("");
            txtValorCredito.setText("");
            txtTotalIntereses.setText("");
            txtCuotaInicial.setText(this.simbolo + " 0");
            txtValorVenta.setText(this.simbolo + " 0");
            txtTotalCredito.setText(this.simbolo + " 0");
            txtValorCredito.setText(this.simbolo + " 0");
            txtTotalIntereses.setText(this.simbolo + " 0");
            cmbTipoPlazo.setSelectedIndex(0);
            dtFechaDesenvolso.setSelectedDate(metodos.haciaDate2(metodosGenerales.fecha()));
            DefaultTableModel c = (DefaultTableModel) tblCuotas.getModel();
            int h = tblCuotas.getRowCount();
            for (int m = 0; m < h; m++) {
                c.removeRow(0);
            }
        }

        txtMarca.setText("");
        txtKm.setText("");
        txtModelo.setText("");
        txtMotor.setText("");
        txtPlaca.setText("");
        txtNumChasis.setText("");
        txtColor.setText("");
        txtMotor.setText("");

        nodoCotizacion = null;
        nodoOrdenServicio = null;
        ndSepare = null;
        ndPedido = null;
        ndPeluqueria = "";
        ndGuarderia = "";
        ndHospitalizacion = "";
        diasHospitalizacion = "";
        horasHospitalizacion = "";

        if (tipoProceso.equals("mesa")) {
            btnReImprimir.setEnabled(true);
        } else {
            btnReImprimir.setEnabled(false);
        }

        btnActualizar.setEnabled(false);

        tblInventario.removeEditor();
        modeloInventario = (DefaultTableModel) tblInventario.getModel();

        while (tblProductos.getRowCount() > 0) {
            modeloPro.removeRow(0);
        }

        while (tblInventario.getRowCount() > 0) {
            modeloInventario.removeRow(0);
        }

        txtSubTotal.setText(this.simbolo + " 0");
        txtTotal.setText("Total: " + this.simbolo + " 0");
        txtTotalIva.setText(this.simbolo + " 0");
        txtTotalImpoconsumo.setText(this.simbolo + " 0");
        txtTotalDescuentos.setText(this.simbolo + " 0");

//        try {
//            this.setPlazo("0", big.getBigDecimal("0"));
//        } catch (Exception e) {
//        }
        if (actualizar) {
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

            if (instancias.isVentasPredeterminado() && !tipoProceso.equals("pedido") && !tipoProceso.equals("separe")) {
                cargar1010();
            }
        }

        activarCampos(true);
        cantProductosOrden = 0;

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

        DefaultTableModel modelo = (DefaultTableModel) tblArticulos.getModel();
        while (tblArticulos.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        productosMovimientos = null;
        productosMovimientos1 = null;

        actualizarInventario = true;

        if (accion.equals("SI")) {
            if (tipoProceso.equals("mesa")) {
                if (instancias.getConfiguraciones().isRestaurante()) {
                    instancias.getMesas().cargarRegistrosMesas();
                    instancias.getMesas().cargarRegistros();
                    instancias.getMesas().setSelected(true);
                    instancias.getMenu().cambiarTitulo("MESAS");
                } else {
                    instancias.getMesas1().cargarRegistros();
                    instancias.getMesas1().setSelected(true);
                }

                if (!instancias.getMenu().getSeVeElMenu()) {
                    instancias.getMenu().expandirMenu();
                }
            }
        }

        if (tipoProceso.equals("facturacion") || (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO"))) {
            instancias.setTitulo("");
        }

        tblProductos.removeEditor();
        tblInventario.removeEditor();
    }

    public void cargar1010() {
        txtNit.setText("1010");
        cargarCliente("1010");
    }

    public void cambiarListaCliente() {
        if (tblInventario.getSelectedRow() != -1) {
            ndProducto codigo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblInventario.getSelectedRow(), 32).toString(), "bdProductos");
            String valor = "";

            switch ((String) tblInventario.getValueAt(tblInventario.getSelectedRow(), 0)) {
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
            tblProductos.setRowSelectionInterval(tblInventario.getSelectedRow(), tblInventario.getSelectedRow());
            tblProductos.setValueAt(valor, tblInventario.getSelectedRow(), 2);
            tblProductos.transferFocus();

            //simulando enter sobre el producto
            KeyEvent evento = new KeyEvent(tblInventario, 0, 0, 0, 0);
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

            if (!(Boolean) datos[62]) {
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
        if (tblInventario.getSelectedRow() != -1) {

            String baseUtilizada = "bdProductos";

            ndProducto codigo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblInventario.getSelectedRow(), 32).toString(), baseUtilizada);
            String valor = "";

            switch ((String) tblInventario.getValueAt(tblInventario.getSelectedRow(), 0)) {
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
            tblProductos.setRowSelectionInterval(tblInventario.getSelectedRow(), tblInventario.getSelectedRow());
            tblProductos.setValueAt(valor, tblInventario.getSelectedRow(), 2);
//            tblProductos.editCellAt(tblInventario.getSelectedRow(), 6);
            tblProductos.transferFocus();

            //simulando enter sobre el producto
            KeyEvent evento = new KeyEvent(tblInventario, 0, 0, 0, 0);
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
            limpiar(true, "");
            txtCargar.setText(cargar);
            txtNit.setText("");
            txtNombre.setText("");
            txtObservaciones.setText("");
//            this.setNit("", "0", "0");
            NC = null;
            txtCargar.requestFocus();
        }
    }//GEN-LAST:event_txtCargarKeyReleased

    private void txtCargarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCargarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCargarKeyTyped

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

            String cant = txtCant.getText();
            cargarProducto(codigo, cant, 1, "", "", "", true, "", "", "", "", "");

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
            txtCant.setText(String.valueOf(cantidad));
            if (txtCant.getText().substring(txtCant.getText().length() - 1, txtCant.getText().length()).equals("0")) {
                txtCant.setText(txtCant.getText().substring(0, txtCant.getText().length() - 2));
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
                impresionComanda comanda = new impresionComanda(null, true, txtObservaciones.getText(),
                        datos, cmbVendedor.getSelectedItem().toString());
                comanda.setInstancias(instancias, lbNoFactura.getText(), true, null);
                comanda.setLocationRelativeTo(null);
                comanda.setVisible(true);
            }

            if (prefactura1) {
                BigDecimal totalNeto = big.getMoneda(txtSubTotal.getText());
                BigDecimal porcPropina = BigDecimal.ZERO, totalPropina = BigDecimal.ZERO;

                try {
                    porcPropina = big.getBigDecimal(datos[90].toString());
                } catch (Exception e) {
                }

                totalPropina = totalNeto.multiply(porcPropina).divide(big.getBigDecimal("100"));

                if (metodos.msgPregunta(null, "¿Desea incluir propina?") != 0) {
                    totalPropina = BigDecimal.ZERO;
                }

                String impresoraPrefactura = "";
                try {
                    impresoraPrefactura = datos[106].toString();
                } catch (Exception e) {
                }

                instancias.getReporte().ver_PrefacturaVenta("where idFactura = '" + "CONGELADA-" + lbNoFactura.getText() + "';", lbNoFactura.getText(),
                        txtObservaciones.getText(), lbNoFactura.getText(), "", instancias.getInformacionEmpresa(), totalPropina, (Boolean) datos[104], impresoraPrefactura);

                String copias = "";
                try {
                    copias = datos[102].toString();
                } catch (Exception e) {
                }

                try {
                    if (copias != null || !copias.equals("")) {
                        for (int i = 0; i < Integer.parseInt(copias); i++) {
                            instancias.getReporte().ver_PrefacturaVenta("where idFactura = '" + "CONGELADA-" + lbNoFactura.getText() + "';", lbNoFactura.getText(),
                                    txtObservaciones.getText(), lbNoFactura.getText(), "", instancias.getInformacionEmpresa(), totalPropina, (Boolean) datos[104],
                                    impresoraPrefactura);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }//GEN-LAST:event_btnReImprimirActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        if (!(Boolean) datos[109]) {
            if (this.tipoProceso.equals("mesa") && !instancias.getUsuario().equals("ADMIN")) {
                if (metodos.msgPregunta(null, "No se puede borrar ¿Pedir permiso?") == 0) {

                    vistaSolicitarPermisos permisos = new vistaSolicitarPermisos(null, true, "No se puede limpiar la mesa.", "LIMPIAR",
                            instancias.getTitulo(), "borrarMesa");
                    permisos.setLocationRelativeTo(null);
                    permisos.setVisible(true);
//                    dlgPedirPermiso permiso = new dlgPedirPermiso(null, true, "mesa");
//                    permiso.setLocationRelativeTo(null);
//                    permiso.setVisible(true);
                    return;
                } else {
                    return;
                }
            }
        }

        if (tipoProceso.equals("facturacion") || tipoProceso.equals("mesa")) {
            if (metodos.msgPregunta(null, "¿Desea limpiar la factura?") == 0) {
                limpiar(true, "SI");
            }
        } else {
            limpiar(true, "SI");
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        instancias.setCancelarFactura(false);

        if (tipoProceso.equals("pedido") && btnGuardar.getText().equals("FACTURAR")) {
            this.tipoProceso = "facturacion";
            tipoActual = "pedidoActual";
        }

        if (btnGuardar.getText().equals("GUARDAR")) {
            validacionInicialFactura(false);
        } else if (btnGuardar.getText().equals("FACTURAR") && (lbTitulo.getText().contains("CONG") || lbTitulo.getText().contains("Mesa."))) {
            facturarCongelada(false);
        } else {
            validacionInicialFactura(false);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardarKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGuardarActionPerformed(null);
        }
    }//GEN-LAST:event_btnGuardarKeyReleased

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if (tipoProceso.equals("pedido") && btnGuardar.getText().equals("FACTURAR")) {
            this.tipoProceso = "facturacion";
            tipoActual = "pedidoActual";
        }

        if (lbTitulo.getText().contains("CONG") || lbTitulo.getText().contains("Mesa.")) {
            facturarCongelada(true);
        } else {
            validacionInicialFactura(true);
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void btnGuardar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGuardar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardar1KeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        if (tblProductos.getRowCount() == 0) {
            metodos.msgError(null, "No ha añadido ningun producto");
            instancias.setCancelarFactura(true);
            return;
        }

        if (txtNombre.getText().equals("")) {
            metodos.msgAdvertenciaAjustado(factura, "Debe cargar un cliente");
            return;
        }

        int xyz = tblProductos.getRowCount();
        if (xyz > 0) {
            for (int i = 0; i < xyz; i++) {
                calcularTabla(i, true);
            }
        }

        Boolean facturarSinInventario = (Boolean) datos[79];

        String baseUtilizada = "bdProductos";

        if (this.tipoProceso.equals("mesa")) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                agregarAdicionesATabla(baseUtilizada);
            }
        }

        if (this.tipoProceso.equals("mesa")) {
            int cantProdFact = 0;
            if (instancias.getConfiguraciones().isRestaurante()) {
                for (int i = 0; i < tblProductos.getRowCount(); i++) {
                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
                    if (nodo.getUsuario().equals("FACTURA")) {

                        String opciones = "";
                        try {
                            opciones = tblProductos.getValueAt(i, 21).toString().split("; ")[1];
                        } catch (Exception e) {
                        }

                        if (opciones.equals("")) {
                            Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 32).toString());
                            cantProdFact = cantProdFact + productos.length;
                        } else {
                            cantProdFact = cantProdFact + opciones.split(", ").length;
                        }
                    }
                }
            }

            Object[][] productosSinInventario = new Object[tblProductos.getRowCount()][4];
            Object[][] productosSinInventarioDis = new Object[cantProdFact][5];

            Boolean entro = false;
            int ser = 0, ser1 = 0;

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

                BigDecimal num = BigDecimal.ZERO;
                try {
                    num = big.getBigDecimal(tblInventario.getValueAt(i, 2).toString().replace(",", "."));
                } catch (Exception e) {
                }

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
                                String cant = opcion.getCantidad();
                                String estado = opcion.getEstado();

                                if (estado.equals(" true")) {
                                    ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
                                    Double cant1 = Double.parseDouble(nodo1.getFisicoInventario().replace(",", "."));
                                    Double total = cant1 - Double.parseDouble(cant.replace(",", "."));

                                    if (total < 0) {
                                        productosSinInventarioDis[ser1][0] = nodo1.getIdSistema();
                                        productosSinInventarioDis[ser1][1] = nodo1.getDescripcion();
                                        productosSinInventarioDis[ser1][2] = cant1;
                                        productosSinInventarioDis[ser1][3] = total;
                                        productosSinInventarioDis[ser1][4] = cant;
                                        entro = true;
                                        ser1++;
                                    }
                                }
                            }
                        }
                    } else {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(tblProductos.getValueAt(i, 32).toString());

                        for (int k = 0; k < productos.length; k++) {
                            String codigo = productos[k][0].toString();
                            String cant = productos[k][1].toString();
                            ndProducto insumo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
                            Double cant1 = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                            Double total = cant1 - Double.parseDouble(cant.replace(",", "."));

                            if (total < 0) {
                                productosSinInventarioDis[ser1][0] = insumo.getIdSistema();
                                productosSinInventarioDis[ser1][1] = insumo.getDescripcion();
                                productosSinInventarioDis[ser1][2] = cant1;
                                productosSinInventarioDis[ser1][3] = total;
                                productosSinInventarioDis[ser1][4] = cant;
                                entro = true;
                                ser1++;
                            }
                        }
                    }
                } else {
                    if (num.compareTo(BigDecimal.ZERO) < 0) {
                        if (nodo.getManejaInventario()) {
                            productosSinInventario[ser][0] = nodo.getIdSistema();
                            productosSinInventario[ser][1] = nodo.getDescripcion();
                            productosSinInventario[ser][2] = tblInventario.getValueAt(i, 1);
                            productosSinInventario[ser][3] = tblInventario.getValueAt(i, 2);
                            entro = true;
                            ser++;
                        } else {
                            System.out.println("Este producto no maneja inventario.");
                        }
                    }
                }
            }

            if (entro) {
                if (!facturarSinInventario) {
                    metodos.msgError(factura, "No tiene inventario suficiente");
                    return;
                } else {

                    if (!cambioMesa) {
                        dlgProductosSinInventario prodSinInventario = new dlgProductosSinInventario(null, true, productosSinInventario, productosSinInventarioDis);
                        prodSinInventario.setVisible(true);
                    }

                    if (instancias.getCancelarFactura()) {
                        borrarAdiciones();
//                        instancias.setCancelarFactura(false);
                        return;
                    } else {
                    }
                }
            }
        }

        if (!saltarPasosFactura) {
            if (metodos.msgPregunta(null, "¿Desea continuar?") != 0) {
                borrarAdiciones();
                System.out.println("DEVOLVIO LA FACTURA");
                facturandoPedidos = false;
                return;
            }
        }

        String tip = tipo();
        String factura = tipo() + "-" + lbNoFactura.getText();
        String por = "";

        if (cmbRtf.getSelectedIndex() == 0) {
            por = "0";
        } else {
            por = cmbRtf.getSelectedItem().toString();
        }

        String vendedor;
        try {
            vendedor = cmbVendedor.getSelectedItem().toString();
        } catch (Exception e) {
            vendedor = "";
        }

        String ter = instancias.getTerminal();

        if (tipoProceso.equals("pedido")) {
            Object[][] mat = null;

            mat = instancias.getSql().getRegistrosPrePedidos(factura);

            for (int i = 0; i < mat.length; i++) {

                String codProd = mat[i][0].toString();
                ndProducto informacionProducto = instancias.getSql().getDatosProducto(codProd, baseUtilizada);

                if (informacionProducto.getUsuario().equals("ADMIN")) {

                } else {
                    String opciones2[];
                    String opciones = "";
                    try {
                        opciones = mat[i][15].toString().split("; ")[1];
                    } catch (Exception e) {
                    }

                    if (!opciones.equals("")) {
                        for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {

                            String codigo = opcion.getCodigo();
                            String cant = opcion.getCantidad();
                            String estado = opcion.getEstado();

                            if (estado.equals(" true")) {
                                ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                Double fisicoInventario = Double.parseDouble(nodo1.getFisicoInventario().replace(",", "."));
                                Double pedidos = Double.parseDouble(nodo1.getPedidos().replace(",", "."));

                                fisicoInventario = fisicoInventario + Double.parseDouble(cant.replace(",", "."));
                                pedidos = pedidos - Double.parseDouble(cant.replace(",", "."));

                                String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                                String pedidos1 = String.valueOf(df.format(pedidos)).replace(".", ",");

                                instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                                instancias.getSql().modificarInventario("pedidos", pedidos1, codigo, baseUtilizada);
                            }
                        }
                    } else {
                        Object[][] productos = instancias.getSql().getCantidadesDiscosteo(codProd);

                        for (int k = 0; k < productos.length; k++) {
                            String codigo = productos[k][0].toString();
                            String cant = productos[k][1].toString();

                            ndProducto insumo = instancias.getSql().getDatosProducto(codigo, baseUtilizada);
                            Double fisicoInventario = Double.parseDouble(insumo.getFisicoInventario().replace(",", "."));
                            Double pedidos = Double.parseDouble(insumo.getPedidos().replace(",", "."));

                            fisicoInventario = fisicoInventario + Double.parseDouble(cant.replace(",", "."));
                            pedidos = pedidos - Double.parseDouble(cant.replace(",", "."));

                            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                            String pedidos1 = String.valueOf(df.format(pedidos)).replace(".", ",");

                            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                            instancias.getSql().modificarInventario("pedidos", pedidos1, codigo, baseUtilizada);
                        }
                    }
                }
            }

            String pedido = instancias.getSql().pedidoExistente(factura);
            instancias.getSql().eliminarComanda(pedido, "pedido");
            instancias.getSql().eliminarPedido(pedido);

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                String preparacion = "";
                try {
                    preparacion = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fechaHora()),
                    metodos.fechaConsulta(txtVencimiento.getText()), instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(),
                    instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(), big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                    big.getMoneda(txtTotalDescuentos.getText()), big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                    factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                    "PENDIENTE", "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    BigDecimal.ZERO, txtPlaca1.getText(), txtNombre.getText(), "", "", "",
                    "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    preparacion, "", tblProductos.getValueAt(i, 29), tblProductos.getValueAt(i, 27)
                };

                ndPedido nodo = metodos.llenarPedido(vector);

                if (!instancias.getSql().agregarPedido(nodo)) {
                    boolean noPuedaGuardar = false;

                    instancias.getSql().eliminarOServicio(factura);
                    while (!noPuedaGuardar) {
                        noPuedaGuardar = instancias.getSql().eliminarOServicio(factura);
                    }

                    metodos.msgError(null, "Error al modificar el pedido");
                    return;
                }

                String preparacionProducto = "";
                try {
                    preparacionProducto = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {

                    instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                            preparacionProducto, "fisicoInventarioPedido", "");
                }

                agregarRegistrosComandas(i, "", baseUtilizada, "", factura, "");
            }

            String cong = "";
            if (baseUtilizada.equals("bdProductos")) {
                cong = "totalizadoPedidos";
            } else if (baseUtilizada.equals("bdProductosBodega1")) {
                cong = "totalizadoPedidos1";
            } else if (baseUtilizada.equals("bdProductosBodega2")) {
                cong = "totalizadoPedidos2";
            } else if (baseUtilizada.equals("bdProductosBodega3")) {
                cong = "totalizadoPedidos3";
            } else if (baseUtilizada.equals("bdProductosBodega4")) {
                cong = "totalizadoPedidos4";
            }

            for (int i = 0; i < productosMovimientos1.length; i++) {
                String sql1 = " where codigo = '" + productosMovimientos1[i][0] + "' ";
                Object[][] pedidos = instancias.getSql().getTotalizadoPedidos(sql1, cong);

                double total = 0;
                try {
                    total = Double.parseDouble(pedidos[0][1].toString().replace(",", "."));
                } catch (Exception e) {
                    System.out.println("fallo");
                }

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                instancias.getSql().modificarInventario("pedidos", total1, productosMovimientos1[i][0].toString(), baseUtilizada);

                ndProducto nodo = instancias.getSql().getDatosProducto(productosMovimientos1[i][0].toString(), baseUtilizada);
                actualizarFisicoInventario(nodo, productosMovimientos1[i][0].toString(), baseUtilizada);

                if (productosMovimientos1[i][2] != null) {
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

                    if (tipo.equals("")) {
                    } else if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(productosMovimientos1[i][2].toString(), "DISPONIBLE");
                    } else {
                        BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(productosMovimientos1[i][2].toString()).replace(",", "."));
                        BigDecimal cantidadTabla1 = new BigDecimal(productosMovimientos1[i][1].toString().replace(",", "."));
                        BigDecimal cantidadFinal = cantidadActual.add(cantidadTabla1);
                        instancias.getSql().modificarCantidadesDetalleProductos(productosMovimientos1[i][2].toString(), cantidadFinal);
                    }
                }
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                Boolean entro = false;
                for (int j = 0; j < productosMovimientos1.length; j++) {
                    if (tblProductos.getValueAt(i, 32).equals(productosMovimientos1[j][0])) {
                        entro = true;
                        break;
                    }
                }

                if (!entro) {
                    String sql1 = " where codigo = '" + tblProductos.getValueAt(i, 32) + "' ";
                    Object[][] pedidos = instancias.getSql().getTotalizadoPedidos(sql1, cong);

                    double total = 0;
                    try {
                        total = Double.parseDouble(pedidos[0][1].toString().replace(",", "."));
                    } catch (Exception e) {
                        System.out.println("fallo");
                    }

                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                    instancias.getSql().modificarInventario("pedidos", total1, pedidos[0][0].toString(), baseUtilizada);

                    ndProducto nodo = instancias.getSql().getDatosProducto(pedidos[0][0].toString(), baseUtilizada);
                    actualizarFisicoInventario(nodo, pedidos[0][0].toString(), baseUtilizada);
                }

                String codProd = tblProductos.getValueAt(i, 29).toString(), tipo = "";

                if (!codProd.equals("")) {

                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

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

                    if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(codProd, "PRESTADO");
                    } else {
                        BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(codProd).replace(",", "."));
                        BigDecimal cantidadTabla1 = new BigDecimal(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
                        BigDecimal cantidadFinal = cantidadActual.subtract(cantidadTabla1);
                        instancias.getSql().modificarCantidadesDetalleProductos(codProd, cantidadFinal);
                    }
                }
            }

            productosMovimientos1 = null;

            if (!saltarPasosFactura) {
                metodos.msgExito(null, "Pedido modificado con éxito");
            }

            limpiar(true, "SI");

        } else if (tipoProceso.equals("orden")) {

            String pedido = instancias.getSql().ordenExistente(factura);
            instancias.getSql().eliminarOServicio(pedido);

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                    factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                    "PENDIENTE", "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    BigDecimal.ZERO, txtPlaca1.getText(), "", "", "", "",
                    "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), ""
                };

                ndOServicio1 nodo = metodos.llenarOServicio1(vector);

                if (!instancias.getSql().agregarOServicio1(nodo)) {
                    boolean noPuedaGuardar = false;

                    instancias.getSql().eliminarOServicio(factura);
                    while (!noPuedaGuardar) {
                        noPuedaGuardar = instancias.getSql().eliminarOServicio(factura);
                    }

                    metodos.msgError(null, "Hubo un problema al modificar la orden");
                    return;
                }
            }

            String baseOrden = "";
            if (baseUtilizada.equals("bdProductos")) {
                baseOrden = "totalizadooservicio";
            } else if (baseUtilizada.equals("bdProductosBodega1")) {
                baseOrden = "totalizadooservicio1";
            } else if (baseUtilizada.equals("bdProductosBodega2")) {
                baseOrden = "totalizadooservicio2";
            } else if (baseUtilizada.equals("bdProductosBodega3")) {
                baseOrden = "totalizadooservicio3";
            } else if (baseUtilizada.equals("bdProductosBodega4")) {
                baseOrden = "totalizadooservicio4";
            }

            for (int i = 0; i < productosMovimientos.length; i++) {
                String sql1 = " where codigo = '" + productosMovimientos[i] + "' ";

                Object[][] oServicio = instancias.getSql().getTotalizadoOServicio(sql1, baseOrden);
                double total = 0;
                try {
                    total = Double.parseDouble(oServicio[0][1].toString().replace(",", "."));
                } catch (Exception e) {
                    System.out.println("fallo");
                }

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                instancias.getSql().modificarInventario("ordenServicio", total1, productosMovimientos[i].toString(), baseUtilizada);
                ndProducto nodo = instancias.getSql().getDatosProducto(productosMovimientos[i].toString(), baseUtilizada);
                actualizarFisicoInventario(nodo, productosMovimientos[i].toString(), baseUtilizada);
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                Boolean entro = false;
                for (int j = 0; j < productosMovimientos.length; j++) {
                    if (tblProductos.getValueAt(i, 32).equals(productosMovimientos[j])) {
                        entro = true;
                        break;
                    }
                }

                if (!entro) {
                    String sql1 = " where codigo = '" + tblProductos.getValueAt(i, 32) + "' ";

                    Object[][] oServicio = instancias.getSql().getTotalizadoOServicio(sql1, baseOrden);

                    double total = 0;
                    try {
                        total = Double.parseDouble(oServicio[0][1].toString().replace(",", "."));
                    } catch (Exception e) {
                        System.out.println("fallo");
                    }

                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                    instancias.getSql().modificarInventario("ordenServicio", total1, oServicio[0][0].toString(), baseUtilizada);

                    ndProducto nodo = instancias.getSql().getDatosProducto(oServicio[0][0].toString(), baseUtilizada);
                    actualizarFisicoInventario(nodo, oServicio[0][0].toString(), baseUtilizada);
                }
            }

            productosMovimientos = null;
            metodos.msgExito(null, "Orden modificada con éxito");
            limpiar(true, "SI");

        } else if (tipoProceso.equals("cuentaCobro")) {

            instancias.getSql().eliminarCuentaCobro(factura);

            String hasta = "";
            if (chkSinEstablecer.isSelected()) {
                hasta = metodos.desdeDate(dtDesde.getCurrent());
            } else {
                hasta = metodos.desdeDate(dtHasta.getCurrent());
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fechaHora()),
                    metodos.fechaConsulta(txtVencimiento.getText()), instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                    factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                    "PENDIENTE", "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    BigDecimal.ZERO, txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "", "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    cmbPeriodicidad.getSelectedItem(), metodos.desdeDate(dtDesde.getCurrent()), hasta, txtCantIncremento.getText(),
                    big.getMoneda(txtTotalImpoconsumo.getText()), tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                    big.getMoneda((String) tblProductos.getValueAt(i, 8)), ""
                };

                ndCongelada nodo = metodos.llenarCongelada(vector);

                if (!instancias.getSql().agregarCuentaCobro(nodo)) {
                    metodos.msgError(null, "Error al modificar la plantilla cobro");
                    return;
                }
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
            limpiar(true, "SI");

        } else if (tipoProceso.equals("mesa")) {

            ndCongelada nodo1 = instancias.getSql().getDatosCongelada(factura);

            instancias.getSql().eliminarMesa(factura);
            instancias.getSql().eliminarComanda(factura, "congelada");

            for (int i = 0; i < tblProductos.getRowCount(); i++) {

                Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                    instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                    factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                    "PENDIENTE", "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    BigDecimal.ZERO, txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                    "PLATO-" + i, nodo1.getConseMesa(), tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), nodo1.getTurno(), tblProductos.getValueAt(i, 27), tblProductos.getValueAt(i, 29),
                    big.getMoneda(txtTotalImpoconsumo.getText()), tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                    big.getMoneda((String) tblProductos.getValueAt(i, 8)), ""
                };

                nodo1 = metodos.llenarCongelada(vector);

                if (!instancias.getSql().agregarCongelada(nodo1)) {
                    boolean noPuedaGuardar = false;
                    instancias.getSql().eliminarOServicio(factura);
                    while (!noPuedaGuardar) {
                        noPuedaGuardar = instancias.getSql().eliminarMesa(factura);
                    }

                    metodos.msgError(null, "Error al modificar la congelada");
                    return;
                }

                String preparacionProducto = "";
                try {
                    preparacionProducto = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                ndProducto nodoProducto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

                if (nodoProducto.getUsuario().equalsIgnoreCase("FACTURA")) {

                    instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                            preparacionProducto, "fisicoInventario", "");
                }

                agregarRegistrosComandas(i, nodo1.getTurno(), baseUtilizada, "", "", factura);

                // DESCONTAR DEL INVENTARIO DETALLADO //
                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    String cod = "";
                    try {
                        cod = tblProductos.getValueAt(i, 29).toString();
                    } catch (Exception e) {
                    }

                    if (!cod.equals("")) {
                        String tipo = "";

                        if (nodoProducto.getTipoProducto() != null) {
                            if (nodoProducto.getTipoProducto().equals("IMEI")) {
                                tipo = "Imei";
                            } else if (nodoProducto.getTipoProducto().equals("Fecha/Lote")) {
                                tipo = "Fecha/Lote";
                            } else if (nodoProducto.getTipoProducto().equals("Color")) {
                                tipo = "Color";
                            } else if (nodoProducto.getTipoProducto().equals("Serial")) {
                                tipo = "Serial";
                            } else if (nodoProducto.getTipoProducto().equals("Talla")) {
                                tipo = "Talla";
                            } else if (nodoProducto.getTipoProducto().equals("ColorTalla")) {
                                tipo = "ColorTalla";
                            } else if (nodoProducto.getTipoProducto().equals("SerialColor")) {
                                tipo = "SerialColor";
                            } else {
                                tipo = "";
                            }
                        }

                        if (tipo.equals("Imei") || tipo.equals("Serial") || tipo.equals("SerialColor")) {
                            instancias.getSql().modificarEstadoDetalleProductos(cod, "CONGELADO");
                        } else {
                            BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                            BigDecimal cantidadTabla1 = new BigDecimal(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
                            BigDecimal cantidadFinal = cantidadActual.subtract(cantidadTabla1);
                            instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                        }
                    }
                }
                // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 

            }

            String cong = "";
            if (baseUtilizada.equals("bdProductos")) {
                cong = "totalizadoCongelada";
            } else if (baseUtilizada.equals("bdProductosBodega1")) {
                cong = "totalizadoCongelada1";
            } else if (baseUtilizada.equals("bdProductosBodega2")) {
                cong = "totalizadoCongelada2";
            } else if (baseUtilizada.equals("bdProductosBodega3")) {
                cong = "totalizadoCongelada3";
            } else if (baseUtilizada.equals("bdProductosBodega4")) {
                cong = "totalizadoCongelada4";
            }

            for (int i = 0; i < productosMovimientos.length; i++) {
                String sql1 = " where codigo = '" + productosMovimientos[i] + "' ";
                Object[][] congeladas = instancias.getSql().getTotalizadoCongelada(sql1, cong);

                double total = 0;
                try {
                    total = Double.parseDouble(congeladas[0][1].toString().replace(",", "."));
                } catch (Exception e) {
                    System.out.println("fallo");
                }

                String total1 = String.valueOf(df.format(total)).replace(".", ",");
                instancias.getSql().modificarInventario("congelada", total1, productosMovimientos[i].toString(), baseUtilizada);
                ndProducto nodo = instancias.getSql().getDatosProducto(productosMovimientos[i].toString(), baseUtilizada);
                actualizarFisicoInventario(nodo, productosMovimientos[i].toString(), baseUtilizada);
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                Boolean entro = false;
                for (int j = 0; j < productosMovimientos.length; j++) {
                    if (tblProductos.getValueAt(i, 32).equals(productosMovimientos[j])) {
                        entro = true;
                        break;
                    }
                }

                if (!entro) {
                    String sql1 = " where codigo = '" + tblProductos.getValueAt(i, 32) + "' ";
                    Object[][] oServicio = instancias.getSql().getTotalizadoCongelada(sql1, cong);

                    double total = 0;
                    try {
                        total = Double.parseDouble(oServicio[0][1].toString().replace(",", "."));
                    } catch (Exception e) {
                    }

                    String total1 = String.valueOf(df.format(total)).replace(".", ",");
                    instancias.getSql().modificarInventario("congelada", total1, tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
                    actualizarFisicoInventario(nodo, tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
                }
            }

            productosMovimientos = null;

            if (!saltarPasosFactura) {
                if (instancias.getConfiguraciones().isRestaurante()) {
                    Object[][] productosNuevos = new Object[tblProductos.getRowCount()][1];
                    int ser = 0;
                    for (int i = 0; i < tblProductos.getRowCount(); i++) {
                        if (!tblProductos.getValueAt(i, 30).equals("Nuevo")) {
                            productosNuevos[ser][0] = tblProductos.getValueAt(i, 30);
                            ser++;
                        }
                    }

                    impresionComanda comanda = new impresionComanda(null, true, txtObservaciones.getText(), datos, cmbVendedor.getSelectedItem().toString());
                    comanda.setInstancias(instancias, lbNoFactura.getText(), false, productosNuevos);
                    comanda.setLocationRelativeTo(null);
                    comanda.setVisible(true);
//                        cambioMesa = true;
//                        btnVolverMouseClicked(null);
//                        metodos.msgExito(null, "Mesa modificada con éxito");
                } else {
                    metodos.msgExito(null, "Congelada modificada con éxito");
                }
            }

            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getMenu().cambiarTitulo("MESAS");
                instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
                instancias.getMesas().cargarRegistrosMesas();
                instancias.getMesas().cargarRegistros();
                instancias.getMesas().setSelected(true);
            } else {
                instancias.getMesas1().cargarRegistros();
                instancias.getMesas1().setSelected(true);
            }

            if (!instancias.getMenu().getSeVeElMenu()) {
                instancias.getMenu().expandirMenu();
            }
        }

        saltarPasosFactura = false;
        modificarPedidoActivo = false;
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void lbFacturaNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyReleased

    private void lbFacturaNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbFacturaNoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbFacturaNoKeyTyped

    private void tblImagenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblImagenesMouseClicked
        int row = tblImagenes.getSelectedRow();
        String grupo = "";

        if (row == 0) {
            row = 2;
        }

        try {
            if (row % 2 > 0) {
                grupo = tblImagenes.getValueAt(tblImagenes.getSelectedRow(), tblImagenes.getSelectedColumn()).toString();
            } else {
                grupo = tblImagenes.getValueAt(tblImagenes.getSelectedRow() + 1, tblImagenes.getSelectedColumn()).toString();
            }
        } catch (Exception e) {
            metodos.msgError(null, "Seleccione un grupo válido");
        }

        if (grupo.equals("")) {
            metodos.msgError(null, "Seleccione un grupo válido");
        } else {
            dlgProductosGrupo migrupo = new dlgProductosGrupo(null, true, grupo, tipoProceso);
            migrupo.setLocationRelativeTo(null);
            migrupo.setVisible(true);
        }
    }//GEN-LAST:event_tblImagenesMouseClicked

    private void btnVolverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseClicked
        ndCongelada nodo = null;

        String factura = tipo() + "-" + lbNoFactura.getText();
        nodo = instancias.getSql().getDatosCongelada(factura);

        if (nodo.getIdFactura() != null) {
            if (txtNombre.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "Debe cargar un cliente");
                return;
            }

            saltarPasosFactura = true;
            btnActualizarActionPerformed(null);

            if (instancias.getCancelarFactura()) {
                borrarAdiciones();
                return;
            } else {
            }
        } else {
            instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
        }

        mesaCongelada = false;
        btnActualizar.setVisible(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setVisible(true);
        btnGuardar1.setVisible(true);
        btnReImprimir.setVisible(false);
        cambioMesa = false;

        if (instancias.getConfiguraciones().isRestaurante()) {
            instancias.getMesas().cargarRegistrosMesas();
            instancias.getMesas().cargarRegistros();
            instancias.getMesas().setSelected(true);
            instancias.getMenu().cambiarTitulo("MESAS");
        } else {
            instancias.getMesas1().cargarRegistros();
            instancias.getMesas1().setSelected(true);
        }

        if (!instancias.getMenu().getSeVeElMenu()) {
            instancias.getMenu().expandirMenu();
        }
    }//GEN-LAST:event_btnVolverMouseClicked

    private void btnVolver1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolver1MouseClicked
        ndCongelada nodo = null;

        String factura = tipo() + "-" + lbNoFactura.getText();
        nodo = instancias.getSql().getDatosCongelada(factura);

        if (nodo.getIdFactura() != null) {
            if (txtNombre.getText().equals("")) {
                metodos.msgAdvertenciaAjustado(null, "Debe cargar un cliente");
                return;
            }

            saltarPasosFactura = true;
            btnActualizarActionPerformed(null);

            if (instancias.getCancelarFactura()) {
                return;
            } else {
            }
        } else {
            instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
        }

        mesaCongelada = false;
        btnActualizar.setVisible(false);
        btnActualizar.setEnabled(false);
        btnGuardar.setVisible(true);
        btnGuardar1.setVisible(true);
        btnReImprimir.setVisible(false);
        cambioMesa = false;

        if (instancias.getConfiguraciones().isRestaurante()) {
            instancias.getMesas().cargarRegistrosMesas();
            instancias.getMesas().cargarRegistros();
            instancias.getMesas().setSelected(true);
        } else {
            instancias.getMesas1().cargarRegistros();
            instancias.getMesas1().setSelected(true);
        }

        if (!instancias.getMenu().getSeVeElMenu()) {
            instancias.getMenu().expandirMenu();
        }
    }//GEN-LAST:event_btnVolver1MouseClicked

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        String baseUtilizada = "bdProductos";
        String dato = "";

        if (evt.getClickCount() > 1 && (tblProductos.getSelectedColumn() == 5 || tblProductos.getSelectedColumn() == 6)) {
            if (instancias.getConfiguraciones().isFacturaElectronica()) {
                abrirModalDescuentosProducto(tblProductos.getSelectedRow());
            }
        }

        if (this.tipoProceso.equals("mesa") || this.tipoProceso.equals("facturacion") || this.tipoProceso.equals("pedido")) {
            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(tblProductos.getSelectedRow(), 32).toString(), baseUtilizada);

            if (nodo.getUsuario().equalsIgnoreCase(TipoProducto.PRODUCTO_DISENADO.getValue()) && evt.getClickCount() >= 1 && tblProductos.getSelectedColumn() == 1) {
                if (!this.tipoProceso.equals("mesa")) {
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

            if (!(Boolean) datos[62]) {
                if (this.tipoProceso.equals("mesa") && !instancias.getUsuario().equals("ADMIN")) {
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
                        if (!(Boolean) datos[51]) {
                            ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                            tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                        }
                        break;
                    case 2:
                        if (!(Boolean) datos[61]) {
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
                        tblInventario.removeEditor();

                        tblProductos.editCellAt(r, 3);
                        tblProductos.setColumnSelectionInterval(3, 3);
                        tblProductos.transferFocus();
                    } catch (Exception e) {
                    }

                } else if (tblProductos.getSelectedColumn() == 1) {

                    ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                    if (!(Boolean) datos[51]) {
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

                    if (!(Boolean) datos[51]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);
                        tblProductos.setValueAt(nodo.getDescripcion(), fila, 1);
                    }

                    if (!(Boolean) datos[61]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                        String lista = tblInventario.getValueAt(tblProductos.getSelectedRow(), 0).toString();
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

                    if (!(Boolean) datos[61]) {
                        ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(fila, 32).toString(), baseUtilizada);

                        String lista = tblInventario.getValueAt(fila, 0).toString();
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
                        metodos.msgAdvertencia(factura, "No tiene ninguna utilidad!");
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
            BigDecimal num1 = big.getMoneda(tblInventario.getValueAt(fila, 1).toString());
            BigDecimal num2 = big.getBigDecimal(tblProductos.getValueAt(fila, 3).toString().replace(",", "."));
            BigDecimal total = num1.subtract(num2);
            tblInventario.setValueAt(big.setNumero(total), fila, 2);
        } else {
            tblInventario.setValueAt("N/A", fila, 1);
            tblInventario.setValueAt("N/A", fila, 2);
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

        String factura = tipo() + "-" + lbNoFactura.getText();
        dlgEscojerMesa mesa = new dlgEscojerMesa(null, true, factura);
        mesa.setVisible(true);

        if (cambioMesa) {
            btnVolverMouseClicked(null);
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
            int num = Integer.parseInt(datos[49].toString());
            if (!rdPos.isSelected()) {
                if (num > 0) {
                    if (tblProductos.getRowCount() >= num) {
                        if (metodos.msgPregunta(factura, "Limite de productos, ¿Desea continuar?") != 0) {
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
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

    private void txtCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantActionPerformed

    private void txtCantFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantFocusGained

    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyPressed

    private void txtCantKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantKeyReleased

    private void btnNuevaParte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaParte1ActionPerformed
        infNuevaParte buscador = new infNuevaParte();
        String tipo = txtTipoVehiculo.getText();
        buscador.cargarArticulos("Moto");
        buscador.setVisible(true);
    }//GEN-LAST:event_btnNuevaParte1ActionPerformed

    private void txtNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNombreMouseClicked

    }//GEN-LAST:event_txtNombreMouseClicked

    private void btnPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendientesActionPerformed
        if (tipoProceso.equals("cotizacion")) {
            new dlgCotizacionesPendientes(null, false);
        } else if (tipoProceso.equals("orden")) {
            dlgOrdenesServicioPendientes pendiente = new dlgOrdenesServicioPendientes(null, true, "");
            pendiente.setVisible(true);
        } else if (tipoProceso.equals("pedido")) {
            dlgPedidosPendientes pendiente = new dlgPedidosPendientes(null, true, "", "");
            pendiente.setVisible(true);
        }
    }//GEN-LAST:event_btnPendientesActionPerformed

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

    private void txtGarantiaFuncionamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGarantiaFuncionamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGarantiaFuncionamientoActionPerformed

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
        if (instancias.getConfiguraciones().isFacturaElectronica()) {
            if (instancias.getDescuento().equals("peso")) {
                alertas.bigAlert("Cuando el tipo de descuento es en pesos ($), esta opción general no está disponible. "
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

    public void actualizarResolucion(int fila) {

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

        /* INFORMACION DE LA EMPRESA */
        if (null != datos[8]) {
            infoEmpresa = infoEmpresa + "" + (String) datos[8] + "";
        }

        if (null != datos[9]) {
            infoEmpresa = infoEmpresa + "\n" + (String) datos[9];
        }

        if (null != datos[10]) {
            infoEmpresa = infoEmpresa + "\n" + (String) datos[10];
        }

        if (null != datos[13]) {
            infoEmpresa = infoEmpresa + "\n" + (String) datos[13];
        }

        if (null != datos[14]) {
            infoEmpresa = infoEmpresa + "\n" + (String) datos[14];
        }
        /* FIN DE LA INFORMACION */

        String dato1 = "", dato2 = "";
        if (null != datos[16]) {
            dato1 = (String) datos[16];
        }

        if (null != datos[15]) {
            dato2 = (String) datos[15];
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
            if (datos[11] != null) {
                resolucion = datos[11].toString();
            }

            if (datos[12] != null) {
                numeracion = datos[12].toString();
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

    public void actualizarFisicoInventario(ndProducto nodo, String prod, String base) {
        double compras1, ventas1, nc1, ajusteEntrada1, ajusteSalida1, planSepare1, pedidos1, anulacion1, invInicial1, fisicoInventario,
                armado1, costeo1, ordenServicio1, congelada1, trasladoBod1, trasladoInternoEntrada1, trasladoInternoSalida1;

        compras1 = Double.parseDouble(nodo.getCompras().replace(",", "."));
        ventas1 = Double.parseDouble(nodo.getVentas().replace(",", "."));
        nc1 = Double.parseDouble(nodo.getNc().replace(",", "."));
        ajusteEntrada1 = Double.parseDouble(nodo.getAjusteEntrada().replace(",", "."));
        ajusteSalida1 = Double.parseDouble(nodo.getAjusteSalida().replace(",", "."));
        planSepare1 = Double.parseDouble(nodo.getPlanSepare().replace(",", "."));
        pedidos1 = Double.parseDouble(nodo.getPedidos().replace(",", "."));
        anulacion1 = Double.parseDouble(nodo.getAnulada().replace(",", "."));
        armado1 = Double.parseDouble(nodo.getArmado().replace(",", "."));
        costeo1 = Double.parseDouble(nodo.getCosteo().replace(",", "."));
        ordenServicio1 = Double.parseDouble(nodo.getOrdenServicio().replace(",", "."));
        invInicial1 = Double.parseDouble(nodo.getInventarioInicial().replace(",", "."));
        congelada1 = Double.parseDouble(nodo.getCongelada().replace(",", "."));

        trasladoBod1 = Double.parseDouble(nodo.getTrasladoBod().replace(",", "."));
        trasladoInternoEntrada1 = Double.parseDouble(nodo.getTrasladoInternoEntrada().replace(",", "."));
        trasladoInternoSalida1 = Double.parseDouble(nodo.getTrasladoInternoSalida().replace(",", "."));

        if (trasladoBod1 < 0) {
            trasladoBod1 = trasladoBod1 * -1;
            fisicoInventario = compras1 + invInicial1 + ajusteEntrada1 + anulacion1 + costeo1 + nc1 + trasladoInternoEntrada1 - ventas1
                    - ajusteSalida1 - planSepare1 - pedidos1 - armado1 - ordenServicio1 - congelada1 - trasladoInternoSalida1 - trasladoBod1;
        } else {
            fisicoInventario = trasladoBod1 + compras1 + invInicial1 + ajusteEntrada1 + anulacion1 + costeo1 + nc1 + trasladoInternoEntrada1 - ventas1
                    - ajusteSalida1 - planSepare1 - pedidos1 - armado1 - ordenServicio1 - congelada1 - trasladoInternoSalida1;
        }

        String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
        instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, prod, base);
    }

    public void facturarOrdenesServiciosDetalladas(Object[][] productos, String numPedido, String cliente, String diasPlazo) {
        txtNit.setText(cliente);
        cargarCliente(cliente);

        int dias = 0;
        try {
            dias = Integer.parseInt(diasPlazo);
        } catch (Exception e) {
            dias = 0;
        }

        if (dias > 0) {
            txtDiasPlazo.setText(diasPlazo);
            calcularDiasPlazo(null);
        }

        for (int i = 0; i < productos.length; i++) {
            cargarProducto(productos[i][0].toString(), productos[i][1].toString(), 1, "", "", "", false, "", "", "", "", "");
            tblProductos.setValueAt(productos[i][1].toString(), tblProductos.getRowCount() - 1, 3);
            tblProductos.setValueAt(productos[i][2].toString(), tblProductos.getRowCount() - 1, 2);
            calcularTabla(tblProductos.getRowCount() - 1, false);
        }

        saltarPasosFactura = false;
        btnGuardar1ActionPerformed(null);
    }

    public void cargarPedidosPendientes(String conse) {

        this.tipoProceso = "facturacion";
        Object[][] mat = instancias.getSql().getRegistrosPrePedidos(conse);

        int ser = tblProductos.getRowCount();
        int xyz = 0;

        for (Object[] reg : mat) {
            int plu = Integer.parseInt(reg[13].toString());

            if (plu == 1) {
                cargarProducto((String) reg[0], new Double((String) reg[3]) + "", plu, "", "", "", false, "", "", "", "", "");
            } else {
                cargarProducto((String) reg[0], new Double((String) reg[14]) + "", plu, "", "", "", false, "", "", "", "", "");
            }

            if (plu == 1) {
                tblProductos.setValueAt(new Double((String) reg[3]), ser, 3);
            } else {
                tblProductos.setValueAt(new Double((String) reg[14]), ser, 3);
            }

            tblProductos.setValueAt(mat[xyz][1], ser, 1);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(mat[xyz][2].toString())), ser, 2);
            tblProductos.setValueAt(mat[xyz][5].toString().replace(",", "."), ser, 5);
            tblProductos.setValueAt(mat[xyz][6].toString().replace(".", ","), ser, 6);
            tblProductos.setValueAt(mat[xyz][12], ser, 16);

            calcularTabla(ser, false);
            ser++;
            xyz++;
        }
    }

    public void limpiar() {
        instancias.setCancelarFactura(false);
        limpiar(true, "SI");
    }

    /*public String facturarCuentaCobro(String diasPlazo, String numCCobro, String bodega, BigDecimal mora, String lote, String fechaFactura) {
     saltarPasosFactura = true;
     saltarPasosFactura1 = true;

     if (diasPlazo.equals("")) {
     diasPlazo = "0";
     }

     if (fechaFactura.length() == 9) {
     fechaFactura = "0" + fechaFactura;
     }

     cmbCargar.setSelectedItem("Cargar Plantilla");
     txtCargar.setText(numCCobro.replace("CCOBRO-", ""));

     txtBodega.setText(bodega);
     cargarMovimiento();

     if (mora.compareTo(BigDecimal.ZERO) > 0) {
     cargarProducto("01", "1", 1, "", "", "", false, "", "", "", "", "");
     tblProductos.setValueAt(big.setMoneda(mora), tblProductos.getRowCount() - 1, 2);
     calcularTabla(tblProductos.getRowCount() - 1, false);
     }

     loteCuentasCobro = lote;
     txtDiasPlazo.setText(diasPlazo);
     calcularDiasPlazo(null);

     fechaFacturaAutomatica = metodos.fechaConsulta(fechaFactura);

     String factura = validacionInicialFactura(true);
     saltarPasosFactura = false;
     saltarPasosFactura1 = false;
     return factura;
     }*/
    public void facturarPedido(String nit, String diasPlazo, String tipoFactura, String numPedido, String bodega) {
        saltarPasosFactura = true;

        if (diasPlazo.equals("")) {
            diasPlazo = "0";
        }

        if (tipoFactura.equals("Muchos")) {
            this.tipoProceso = "facturacion";
            if (nit.equals("")) {
                txtNit.setText("1010");
                cargarCliente("1010");
            } else {
                txtNit.setText(nit);
                cargarCliente(nit);
                txtDiasPlazo.setText(diasPlazo);
                calcularDiasPlazo(null);
            }

            facturandoPedidos = true;
            descontarFisicoInventario = "NO";
            btnGuardar1ActionPerformed(null);
        } else {
            this.tipoProceso = "facturacion";
            txtCargar.setText(numPedido.replace("PEDIDO-", ""));
            cargarMovimiento();

            txtDiasPlazo.setText(diasPlazo);
            calcularDiasPlazo(null);
            btnGuardar1ActionPerformed(null);
        }

        saltarPasosFactura = false;
    }

    public void facturarCotizacion(String conse, String diasPlazo) {
        this.tipoProceso = "facturacion";
        txtCargar.setText(conse.replace("COTI-", ""));
        cargarMovimiento();

        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        txtObservaciones.setText(conse + " " + txtObservaciones.getText());
        saltarPasosFactura = true;
        btnGuardar1ActionPerformed(null);
    }

    public void facturarOrdenesServicios(String conse, String diasPlazo, String bodega) {
        this.tipoProceso = "facturacion";
        txtCargar.setText(conse.replace("OSERV-", ""));
        cargarMovimiento();

        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        txtObservaciones.setText(conse + ". " + txtObservaciones.getText());
        saltarPasosFactura = false;
        btnGuardar1ActionPerformed(null);
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
        }
        tblProductos.removeEditor();
        tblInventario.removeEditor();
    }

    private void cargarMovimientoCotizacion() {
        String idCotizacion = "COTI-" + txtCargar.getText();
        DocumentoMovimiento doc = daoFactura.cargarCotizacion(idCotizacion);
        if (doc.isEmpty()) {
            return;
        }

        if ("REALIZADO".equals(doc.getCabecera().getEstadoGeneral())) {
            metodos.msgAdvertenciaAjustado(null, "La cotización ya ha sido facturada.");
            txtCargar.setText("");
            return;
        }

        limpiar(true, "");
        nodoCotizacion = idCotizacion;

        cargarLineasCotizacion(doc.getLineas());
        mostrarResumenDocumento(doc.getCabecera(), true);

        if ("cotizacion".equals(tipoProceso)) {
            btnGuardar.setEnabled(false);
            btnGuardar1.setEnabled(false);
            btnReImprimir.setVisible(true);
            btnReImprimir.setEnabled(true);
            txtCargar.setText(idCotizacion.replace("COTI-", ""));
        }
    }

    private void cargarMovimientoOrdenServicio() {
        String orden = "OSERV-" + txtCargar.getText();
        DocumentoMovimiento doc = daoFactura.cargarOrdenServicio(orden);

        if (doc.isEmpty()) {
            metodos.msgError(null, "La orden no existe!");
            return;
        }

        limpiar(false, "");
        productosMovimientos = new Object[doc.getLineas().size()];
        cargarLineasDocumento(doc.getLineas(), true);
        nodoOrdenServicio = orden;
        mostrarResumenDocumento(doc.getCabecera(), false);

        ndOServicio nodoOrden = daoFactura.getDatosOServicio(orden);
        cargarDatosVehiculo(nodoOrden);
        aplicarEstadoVehiculo(daoFactura.getEstadoVehiculo(orden));

        txtNombre.requestFocus();

        if ("REALIZADO".equals(doc.getCabecera().getEstadoGeneral())) {
            metodos.msgAdvertencia(null, "Esta orden ya ha sido facturada y no se puede modificar.");
            activarCampos(false);
            btnActualizar.setEnabled(false);
            btnReImprimir.setEnabled(true);
        } else {
            activarCampos(true);
            btnActualizar.setEnabled(true);
            btnReImprimir.setEnabled(true);
        }

        if ("facturacion".equals(tipoProceso)) {
            nodoOrdenServicio = orden;
        } else {
            lbNoFactura.setText(orden.substring(6));
        }

        String placaReal = doc.getCabecera().getPlacaReal();
        if (placaReal != null) {
            txtPlaca1.setText(placaReal);
        }

        txtCargar.setText(orden.replace("OSERV-", ""));
    }

    private void cargarMovimientoFactura() {
        String idFactura = "FACT-" + txtCargar.getText();
        limpiar(false, "");

        DocumentoMovimiento doc = daoFactura.cargarMovimientoPrefactura(idFactura);
        if (doc.isEmpty()) {
            metodos.msgError(null, "La factura no existe!");
            return;
        }

        cargarLineasDocumento(doc.getLineas(), false);
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
        ndPlanSepare nodo = daoFactura.getDatosPlanSepare("SEPARE-" + txtCargar.getText());

        if (nodo.getIdFactura() == null) {
            metodos.msgError(null, "Número invalido");
            return;
        }

        String numSepare = txtCargar.getText();
        this.cargarPrefactura(nodo.getIdFactura().replace("SEPARE-", ""), "SEPARE-");
        ndSepare = nodo;
        txtCargar.setText(numSepare);
        lbNoFactura.setText(numSepare);
        btnReImprimir.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnGuardar1.setEnabled(false);
    }

    private void cargarMovimientoPedido() {
        String numPedido = txtCargar.getText();
        ndPedido nodo = daoFactura.getDatosPedido("PEDIDO-" + numPedido);

        if (nodo.getIdFactura() == null) {
            limpiar(false, "");
            if (txtCargar.getText().isEmpty()) {
                if (txtNombre.getText().isEmpty()) {
                    metodos.msgError(null, "Seleccioné un tercero para buscar el pedido");
                } else {
                    infBuscadorCliente buscador = new infBuscadorCliente();
                    buscador.cargarRegistros(txtNit.getText(), "pedido", instancias);
                    buscador.setVisible(true);
                }
            } else {
                metodos.msgError(null, "No hay una orden registrada con este número");
            }
            return;
        }

        this.cargarPrefactura(nodo.getIdFactura().replace("PEDIDO-", ""), "PEDIDO-");

        if ("REALIZADO".equals(nodo.getEstadoGeneral())) {
            if ("pedido".equals(tipoProceso)) {
                metodos.msgAdvertencia(null, "Este pedido ya ha sido facturada y no se puede modificar.");
            } else {
                metodos.msgAdvertencia(null, "Este pedido ya ha sido facturada.");
                limpiar(true, "");
                txtCargar.requestFocus();
                return;
            }
            activarCampos(false);
            btnActualizar.setEnabled(false);
            btnReImprimir.setEnabled(true);
        } else if ("ANULADA".equals(nodo.getEstadoGeneral())) {
            metodos.msgAdvertencia(null, "El pedido esta anulado.");
            limpiar(true, "");
            txtCargar.requestFocus();
            return;
        } else {
            activarCampos(true);
            btnActualizar.setEnabled(true);
            btnReImprimir.setEnabled(true);
        }

        ndPedido = nodo;
        txtObservaciones.setText(nodo.getObservacion());
        btnGuardar.setEnabled(false);
        btnGuardar1.setEnabled(false);
        lbNoFactura.setText(numPedido);
    }

    // -------------------------------------------------------------------------
    // Helpers de carga de productos en tabla
    // -------------------------------------------------------------------------
    private void cargarLineasCotizacion(List<LineaProducto> lineas) {
        for (int i = 0; i < lineas.size(); i++) {
            LineaProducto linea = lineas.get(i);
            cargarProducto(linea.getCodigo(), linea.getCantidad(), linea.getPlu(), "", "", "", false, "", "", "", "", "");
            tblProductos.setValueAt(linea.getRango(), i, 31);
            tblProductos.setValueAt(linea.getDescripcion(), i, 1);
            tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(linea.getPrecio())), i, 2);
            tblProductos.setValueAt(big.getBigDecimal(linea.getPorcDescuento()), i, 5);
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.setRowSelectionInterval(i, i);
            tblProductosKeyReleased(new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER));
        }
    }

    /**
     * Carga líneas de producto en la tabla para ORDEN DE SERVICIO y PREFACTURA.
     *
     * @param trackearOrden true para ORDEN DE SERVICIO: registra productos en
     * productosMovimientos e incrementa cantProductosOrden.
     */
    private void cargarLineasDocumento(List<LineaProducto> lineas, boolean trackearOrden) {
        for (int i = 0; i < lineas.size(); i++) {
            LineaProducto linea = lineas.get(i);
            cargarProducto(linea.getCodigo(), linea.getCantidad(), linea.getPlu(),
                    linea.getImei(), "", linea.getIdProd(), false, "", "", "", "", "");
            String rango = linea.getRango();
            if (rango != null && !rango.isEmpty()) {
                tblProductos.setValueAt(rango, i, 31);
            }
            tblProductos.setValueAt(linea.getDescripcion(), i, 1);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(linea.getPrecio())), i, 2);
            tblProductos.setValueAt(linea.getPorcDescuento().replace(",", "."), i, 5);
            tblProductos.setValueAt(linea.getDescuento().replace(".", ","), i, 6);
            tblProductos.setValueAt(linea.getPreparacion(), i, 16);
            if (trackearOrden) {
                cantProductosOrden++;
                productosMovimientos[i] = linea.getCodigo();
            }
            calcularTabla(i, false);
        }
    }

    // -------------------------------------------------------------------------
    // Helpers de resumen financiero y datos del documento
    // -------------------------------------------------------------------------
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
        btnActualizar.setEnabled(true);
        btnReImprimir.setEnabled(true);
        txtPlaca.setEnabled(false);
        txtModelo.setEnabled(false);
        txtTipoVehiculo.setEnabled(false);
        txtNumChasis.setEnabled(false);
        txtMarca.setEnabled(false);
        txtKm.setEnabled(false);
        txtMotor.setEnabled(false);
        txtColor.setEnabled(false);
        if (!"facturacion".equals(tipoProceso)) {
            btnGuardar.setVisible(false);
            btnGuardar1.setVisible(false);
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

            this.cargarPrefactura(num, tipo);
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
                btnActualizar.setEnabled(true);
                btnReImprimir.setEnabled(true);

                txtPlaca.setEnabled(false);
                txtModelo.setEnabled(false);
                txtTipoVehiculo.setEnabled(false);
                txtNumChasis.setEnabled(false);
                txtMarca.setEnabled(false);
                txtKm.setEnabled(false);
                txtMotor.setEnabled(false);
                txtColor.setEnabled(false);

                if (jPanel6.isVisible()) {
                    btnGuardar.setEnabled(false);
                    btnGuardar1.setEnabled(false);
                } else {
                    btnGuardar.setEnabled(true);
                    btnGuardar1.setEnabled(true);
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

    private String tipo() {
        String tip = "";

        switch (tipoProceso) {
            case "facturacion":
                tip = "FACT";
                break;
            case "cotizacion":
                tip = "COTI";
                break;
            case "orden":
                tip = "OSERV";
                break;
            case "pedido":
                tip = "PEDIDO";
                break;
            case "separe":
                tip = "SEPARE";
                break;
            case "mesa":
                tip = "CONGELADA";
                break;
            case "cuentaCobro":
                tip = "CCOBRO";
                break;
        }
        return tip;
    }

    public void eliminarFila() {
        int fila = tblProductos.getSelectedRow();
        if (tblProductos.getValueAt(fila, 16).equals("REALIZADO")) {
            metodos.msgAdvertencia(null, "No puede Borrar este producto");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        modelo.removeRow(fila);

        modeloInventario = (DefaultTableModel) tblInventario.getModel();
        modeloInventario.removeRow(fila);

        tblInventario.removeEditor();
        tblProductos.removeEditor();
        cargarTotales();
    }

    public String guardarValidacion(int idResolucionSeleccionada) {
        int idResolucion = Integer.parseInt(tblComprobantes.getValueAt(idResolucionSeleccionada, 0).toString());
        ModeloResolucion resolucion = daoResoluciones.obtenerInformacionResolucion(idResolucion);
        if (resolucion == null) {
            ControladorAlertas.alert("No se pudo obtener los datos de la resolución.");
        }

        String prefijo = (resolucion.getPrefijo() == null) ? "" : resolucion.getPrefijo();
        int consecutivo = resolucion.getConsecutivo();
        String facturaReal = "FACT-" + prefijo + consecutivo;

        int segundosEsperando = 0;
        Object[][] datosFactura = instancias.getSql().getVerificadorFactura(facturaReal);

        while (datosFactura.length > 0) {
            resolucion = daoResoluciones.obtenerInformacionResolucion(idResolucion);
            facturaReal = "FACT-" + prefijo + resolucion.getConsecutivo();
            datosFactura = instancias.getSql().getVerificadorFactura(facturaReal);

            try {
                Thread.sleep(1000);
                segundosEsperando++;

                if (segundosEsperando > 2) {
                    if (!daoResoluciones.aumentarConsecutivoResolucion(idResolucion)) {
                        ControladorAlertas.alertFail("Error al aumentar consecutivo de la resolución");
                    }
                    segundosEsperando = 0;
                }
            } catch (Exception e) {
                ControladorAlertas.alertFail("Error al aumentar consecutivo de la resolución");
            }
        }

        return "FACT-" + prefijo + resolucion.getConsecutivo();
    }

    private String facturar(VistaMetodoPagos devuelta, boolean imprimir, String desde) {

        //SI ES MESA O ESTA ACTIVO SALTAR PASOS DE FACTURA, NO MOSTRAR EL MODULO DE DEVUELTA
        if (tipoProceso.equals("mesa") || saltarPasosFactura) {
            devuelta = new VistaMetodoPagos(instancias.getMenu(), true, big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                    instancias, "", ID_CLIENTE_CARGADO, big.getMoneda(txtSubTotal.getText()));
        }

        //OBTENEMOS LA BASE DE LA BODEGA QUE SE ESTA UTILIZANDO
        String baseUtilizada = "bdProductos";

        //VALIDAMOS QUE SI LA FACTURA A REALIZAR TIENE RELACIONADO ALGUN PEDIDO, SE DESACTIVE EL MODULO DE DEVUELTA
        try {
            if (ndPedido.getIdFactura() != null) {
                devuelta = null;
            }
        } catch (Exception e) {
        }

        //VALIDAMOS QUE SI LA FACTURA A REALIZAR TIENE RELACIONADO ALGUNA COTIZACION, SE DESACTIVE EL MODULO DE DEVUELTA
        try {
            if (nodoCotizacion != null) {
                devuelta = null;
            }
        } catch (Exception e) {
        }

        //VALIDAMOS QUE SI LA FACTURA A REALIZAR ESTA COMPUESTA POR MUCHOS PEDIDOS
        if (facturandoPedidos) {
            devuelta = null;
        }

        //MODULO DE DEVUELTA
        if (!(tipoProceso.equals("orden") || tipoProceso.equals("pedido") || tipoProceso.equals("cuentaCobro") || tipoProceso.equals("separe")
                || facturaCredito || tipoProceso.equals("cotizacion") || desde.equals("ordenMedica"))) {

            if (devuelta == null) {
                //VALIDAMOS SI LA FACTURA ES A CONTADO
                if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                    String miTipo = "";
                    if (tipoProceso.equals("facturacion")) {
                        miTipo = "facturacion";
                    }

                    devuelta = new VistaMetodoPagos(null, true, big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                            instancias, miTipo, ID_CLIENTE_CARGADO, big.getMoneda(txtSubTotal.getText()));
                    devuelta.show();
                } else {
                    instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
                    instancias.setNcDevuelta(big.getBigDecimal("0"));
                    instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
                    instancias.setTarjetaCredito(big.getBigDecimal("0"));
                    instancias.setChequeDevuelta(big.getBigDecimal("0"));
                    instancias.setDevuelta(BigDecimal.ZERO);
                    instancias.setPorcPropina("0");
                    instancias.setPropina(big.getBigDecimal("0"));
                    instancias.setTotalPropina(big.getBigDecimal("0"));
                }
            } else {
                if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                    instancias.setEfectivoDevuelta(big.getBigDecimal(big.getMoneda(txtTotal.getText().replace("Total: ", ""))));
                    instancias.setNcDevuelta(big.getBigDecimal("0"));
                    instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
                    instancias.setTarjetaCredito(big.getBigDecimal("0"));
                    instancias.setChequeDevuelta(big.getBigDecimal("0"));
                    instancias.setDevuelta(BigDecimal.ZERO);
                    instancias.setPorcPropina("0");
                    instancias.setPropina(big.getBigDecimal("0"));
                    instancias.setTotalPropina(big.getBigDecimal("0"));
                } else {
                    instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
                    instancias.setNcDevuelta(big.getBigDecimal("0"));
                    instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
                    instancias.setTarjetaCredito(big.getBigDecimal("0"));
                    instancias.setChequeDevuelta(big.getBigDecimal("0"));
                    instancias.setDevuelta(BigDecimal.ZERO);
                    instancias.setPorcPropina("0");
                    instancias.setPropina(big.getBigDecimal("0"));
                    instancias.setTotalPropina(big.getBigDecimal("0"));
                }
            }
        } else {
            //VALIDACION DE FACTURAS MEDICAS FALTA !
        }

        //VALIDAMOS QUE SI CANCELARON LA FACTURA, SE REESTABLEZCAN AL VALOR INICIAL
        if (instancias.getCancelarFactura()) {
            System.out.println("DEVOLVIO LA FACTURA");
            instancias.setEfectivoDevuelta(big.getBigDecimal("0"));
            instancias.setNcDevuelta(big.getBigDecimal("0"));
            instancias.setTarjetaDevuelta(big.getBigDecimal("0"));
            instancias.setTarjetaCredito(big.getBigDecimal("0"));
            instancias.setChequeDevuelta(big.getBigDecimal("0"));
            instancias.setDevuelta(BigDecimal.ZERO);
            instancias.setPorcPropina("0");
            instancias.setPropina(big.getBigDecimal("0"));
            instancias.setTotalPropina(big.getBigDecimal("0"));
            facturandoPedidos = false;
            borrarAdiciones();
            return "";
        }

        //OBTENEMOS EL CONSECUTIVO DE CUALQUIER TIPO DE DOCUMENTO
        String tip = tipo();
        String factura = "", factura2 = "";
        if (tipoProceso.equals("facturacion") || (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO"))) {
            int fila = 0;
            //OBTENEMOS LA FILA DEL COMPROBANTE SELECCIONADO
            for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
                if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                    fila = i;
                }
            }

            //OBTENEMOS EL PREFIJO DEL COMPROBANTE
            String prefijo = "";

            if (null == tblComprobantes.getValueAt(fila, 8)) {
                prefijo = "";
            } else {
                prefijo = tblComprobantes.getValueAt(fila, 8).toString();
            }

            //VALIDAMOS QUE EL CONSECUTIVO DE FACTURACIÓN NO EXISTA.
            factura2 = guardarValidacion(fila);
            factura = factura2.replace(prefijo, "");
        } else if (tipoProceso.equals("orden")) {
            factura = "OSERV-" + instancias.getSql().getNumConsecutivoFact1("OSERV")[0].toString();
        } else if (tipoProceso.equals("cotizacion")) {
            factura = "COTI-" + instancias.getSql().getNumConsecutivoFact1("COTI")[0].toString();
        } else if (tipoProceso.equals("pedido")) {
            factura = "PEDIDO-" + instancias.getSql().getNumConsecutivoFact1("PEDIDO")[0].toString();
        } else if (tipoProceso.equals("separe")) {
            factura = "SEPARE-" + instancias.getSql().getNumConsecutivoFact1("SEPARE")[0].toString();
            factura2 = factura;
        } else if (tipoProceso.equals("mesa")) {
            factura = "CONGELADA-" + instancias.getSql().getNumConsecutivoFact1("CONGELADA")[0].toString();
        } else if (tipoProceso.equals("cuentaCobro")) {
            factura = "CCOBRO-" + instancias.getSql().getNumConsecutivoFact1("CCOBRO")[0].toString();
        }

        if (facturaCredito) {
            calcularCuotasCredito();
            guardarCredito(factura, factura2);
        }

        String por = "";
        if (cmbRtf.getSelectedIndex() == 0) {
            por = "0";
        } else {
            por = cmbRtf.getSelectedItem().toString();
        }

        String estado = "PENDIENTE";
        String cotizacionesAsociadas = "";

        try {
            for (String cotizacione : cotizaciones) {
                cotizacionesAsociadas = cotizacionesAsociadas + cotizacione + "-";
            }
        } catch (Exception e) {
        }

        //Se tienen que dejar las dos validaciones ya que una es cuando se hace desde el abono y la otra
        //cuando se carga directamente en la factura.
        if (desde.equals("facturarSepare")) {
            estado = "REALIZADO";
            cotizacionesAsociadas = "SEPARE-" + txtCargar.getText();
            instancias.getSql().cambiarEstadoSepare("FACTURADO", "SEPARE-" + txtCargar.getText());
            actualizarInventario = false;
        }

        try {
            if (ndSepare.getIdFactura() != null) {
                estado = "REALIZADO";
                cotizacionesAsociadas = "SEPARE-" + txtCargar.getText();
                instancias.getSql().cambiarEstadoSepare("FACTURADO", "SEPARE-" + txtCargar.getText());
                actualizarInventario = false;
            } else {
            }
        } catch (Exception e) {
        }

        BigDecimal copago = BigDecimal.ZERO;
        try {
            copago = big.getMoneda(txtCopago.getText());
        } catch (Exception e) {
            copago = BigDecimal.ZERO;
        }

        //OBTENEMOS EL VENDEDOR DE LA FACTURA
        String vendedor = "";
        try {
            vendedor = cmbVendedor.getSelectedItem().toString().equals("Seleccione un vendedor") ? "" : cmbVendedor.getSelectedItem().toString();
        } catch (Exception e) {
            vendedor = "";
        }

        String congelada = "";
        if (instancias.getConfiguraciones().isRestaurante()) {
            try {
                congelada = instancias.getTitulo();
            } catch (Exception e) {
            }
        } else {
            try {
                congelada = instancias.getTitulo().replace(": ", "-");
            } catch (Exception e) {
            }
        }

        if (tipoProceso.equals("facturacion")) {
            congelada = "SIN-CONSECUTIVO";
        }

        String turno = "";
        if ((Boolean) datos[54] && instancias.getConfiguraciones().isRestaurante()) {
            turno = instancias.getSql().getTurno();
        } else if (txtTurno.isVisible()) {
            turno = txtTurno.getText();
        }

        if (tipoProceso.equals("facturacion") || (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO"))) {

            agregamosRegistrosMediosDePago(factura2);

            String tipoComprobante = obtenerTipoComprobante();
            tipoComprobante = tipoComprobante.equals("") ? Constantes.FACTURACION_NORMAL : tipoComprobante;

            if (instancias.getConfiguraciones().isFacturaElectronica() && Constantes.esFacturacionElectronica(tipoComprobante)) {

                boolean facturaElectronicaExitosa = false;
                ModeloFacturacionElectronica modeloFacturacionElectronica = crearModeloFacturacionEletronica(factura, factura2, DATOS_CLIENTE_CARGADO);

                try {
                    facturaElectronicaExitosa = consumidorFacturacionElectronica.generarFacturacionElectronica(modeloFacturacionElectronica, false, false,
                            false, rdPos.isSelected());
                } catch (Exception ex) {
                    System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
                }

                if (!facturaElectronicaExitosa) {
                    borrarAdiciones();
                    facturandoPedidos = false;
                    instancias.setCancelarFactura(false);
                    return "";
                }
            }

            ndFactura nodo;
            //SI SE REALIZA LA FACTURA DESDE FACTURACIÓN AUTOMATICA SE TOMA LA FECHA QUE ESCOJA EL CLIENTE, SINO LA DEL SISTEMA
            String fechaFact = "";
            if (fechaFacturaAutomatica.equals("")) {
                fechaFact = metodos.fechaConsulta(metodosGenerales.fechaHora());
            } else {
                fechaFact = fechaFacturaAutomatica;
            }

            //AGREGAMOS EL REGISTRO TOTALIZADO EN VERIFICADOR DE FACTURAS 
            instancias.getSql().agregarVerificarFactura(factura, ID_CLIENTE_CARGADO, factura2, ter,
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), fechaFact,
                    metodos.fechaConsulta(txtVencimiento.getText()), metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)),
                    vendedor, congelada, txtPlaca1.getText(), turno);

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

                    BigDecimal porcDesc;
                    try {
                        porcDesc = big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN);
                    } catch (Exception e) {
                        porcDesc = BigDecimal.ZERO;
                    }

                    if (congelada == null) {
                        congelada = "";
                    }

                    String preparacion = "";

                    try {
                        preparacion = tblProductos.getValueAt(i, 21).toString();
                    } catch (Exception e) {
                    }


                    /*if (instancias.getConfiguraciones().isRestaurante()) {
                     String pedidoActivo = "No";
                     try {
                     if (ndPedido.getIdFactura() != null) {
                     pedidoActivo = "Si";
                     }
                     } catch (Exception e) {
                     }

                     if (pedidoActivo.equals("Si")) {
                     if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                     instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                     preparacion, "inventarioPedido", bodega);
                     }
                     } else {
                     if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                     instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                     preparacion, "descontarTodo", bodega);
                     }
                     }
                     } else {
                     if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                     instancias.getArmado().facturarPreparado(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                     preparacion, bodega);
                     }
                     }*/
                    String consecutivoCosteo = idCosteo;
                    BigDecimal costoCosteo1 = BigDecimal.ZERO, totalUtilidad = BigDecimal.ZERO;

                    try {
                        costoCosteo1 = costoCosteo;
                    } catch (Exception e) {
                    }

                    if (consecutivoCosteo == null) {
                        consecutivoCosteo = "";
                    }

                    if (!consecutivoCosteo.equals("")) {
                        totalUtilidad = big.getMoneda(tblProductos.getValueAt(i, 9).toString()).subtract(costoCosteo1);
                    } else {
                        totalUtilidad = big.getMoneda((String) tblProductos.getValueAt(i, 14));
                        if (totalUtilidad.compareTo(BigDecimal.ZERO) < 0) {
                            totalUtilidad = BigDecimal.ZERO;
                        }
                    }

                    String garantia = "";
                    if (!txtGarantiaSeñal.getText().equals("")) {
                        garantia = "Por señal: " + txtGarantiaSeñal.getText() + " " + cmbSeñal.getSelectedItem() + ". ";
                    }

                    if (!txtGarantiaFuncionamiento.getText().equals("")) {
                        garantia = garantia + "Por funcionamiento: " + txtGarantiaFuncionamiento.getText() + " " + cmbFuncionamiento.getSelectedItem();
                    }

                    BigDecimal ponderado = BigDecimal.ZERO;
                    try {
                        UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(tblProductos.getValueAt(i, 32).toString());
                        ponderado = ultimoPonderado.getNuevoPonderado();
                    } catch (SQLException ex) {
                        Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                        alertas.bigAlert("No se pudo consultar el último ponderado del producto");
                    }

                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", fechaFact,
                        metodos.fechaConsulta(txtVencimiento.getText()), instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(),
                        instancias.getTarjetaDevuelta(), big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                        big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        loteCuentasCobro, instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()),
                        big.getMoneda(por), txtObservaciones.getText(), false, "", false, "", "",
                        metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        estado, "", instancias.getDevuelta(), factura2, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        copago, txtPlaca1.getText(), garantia, "", tblProductos.getValueAt(i, 31), "",
                        "", congelada, tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 3).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", totalUtilidad, "",
                        porcDesc + "", tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 13).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda((String) tblProductos.getValueAt(i, 20)),
                        preparacion, BigDecimal.ZERO, turno, big.getMoneda(txtTotalImpoconsumo.getText()), instancias.getFranquisia(),
                        instancias.getComision(), instancias.getValorComision(), instancias.getTotalFacturaComision(), imei, lote, idProd,
                        cmbMes.getSelectedItem(), instancias.getTarjetaCredito(), instancias.getTotalPropina(), instancias.getPorcPropina(),
                        consecutivoCosteo, metodosGenerales.hora(), tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                        big.getMoneda((String) tblProductos.getValueAt(i, 8)), chkSisteCredito.isSelected(), "", ponderado, tipoComprobante
                    };

                    idCosteo = "";
                    costoCosteo = BigDecimal.ZERO;

                    nodo = metodos.llenarFactura(vector);

                    if (!instancias.getSql().agregarFactura(nodo)) {
                        boolean noPuedaGuardar = false;

                        instancias.getSql().eliminarFactura(factura);
                        while (!noPuedaGuardar) {
                            noPuedaGuardar = instancias.getSql().eliminarFactura(factura);
                        }

                        metodos.msgError(null, "Error al guardar la factura");
                        return null;
                    }
                }

                if (instancias.getConfiguraciones().isRestaurante()) {
                    agregarRegistrosComandas(i, turno, baseUtilizada, factura, "", "");
                }

                try {
                    if (nodoOrdenServicio != null) {
                        if (!instancias.getSql().modificarEstadosProcesos(nodoOrdenServicio, "REALIZADO", tblProductos.getValueAt(i, 32).toString(), "bdOServicio1")) {
                            metodos.msgError(null, "Hubo un problema al modificar el estado del producto");
                            return null;
                        }
                    }
                } catch (Exception e) {
                }

                try {
                    if (nodoCotizacion != null) {
                        if (!instancias.getSql().modificarEstadosProcesos(nodoCotizacion, "REALIZADO", tblProductos.getValueAt(i, 32).toString(), "bdCotizacion")) {
                            metodos.msgError(null, "Hubo un problema al modificar el estado del producto");
                            return null;
                        }
                    }
                } catch (Exception e) {
                }

                try {
                    if (ndPedido.getIdFactura() != null) {
                        if (!instancias.getSql().modificarEstadosProcesos(ndPedido.getIdFactura(), "REALIZADO", tblProductos.getValueAt(i, 32).toString(), "bdPedido")) {
                            metodos.msgError(null, "Error al modificar estado del producto");
                            return null;
                        }
                    }
                } catch (Exception e) {
                }
            }

            InformacionAdicional informacionAdicional = construirInformacionAdicional();
            TipoDocumento tipoMovimiento = TipoDocumento.FACTURACION;
            List<DetalleProducto> detallesProductos = new ArrayList<>();
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

            try {
                List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
                ServicioInventario servicioInventario = new ServicioInventario(productos, detallesProductos, tipoMovimiento, factura2,
                        tablaUtilizada, instancias.getUsuario(), informacionAdicional);
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            descontarFisicoInventario = "SI";
            trasladoBod = "NO";

            try {
                if ((Boolean) datos[54] && instancias.getConfiguraciones().isRestaurante()) {
                    int turno1 = Integer.parseInt(instancias.getSql().getTurno()) + 1;
                    if (turno1 > 100) {
                        turno1 = 1;
                    }

                    instancias.getSql().aumentarTurno(String.valueOf(turno1));
                    txtTurno.setText(String.valueOf(turno1));
                    instancias.getMaestra().setTurno(String.valueOf(turno1));
                    instancias.getMaestra().actualizarTurno();
                    instancias.getPedido().consultarMaestros();
                }
            } catch (Exception e) {
            }

            try {
                if (ndPedido.getIdFactura() != null) {
                    Object[][] mat = null;
                    mat = instancias.getSql().getRegistrosPrePedidos(ndPedido.getIdFactura());

                    for (int i = 0; i < mat.length; i++) {
                        String opciones2[];
                        String opciones = "";
                        try {
                            opciones = mat[i][15].toString().split("; ")[1];
                        } catch (Exception e) {
                        }

                        if (!opciones.equals("")) {
                            for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {

                                String codigo = opcion.getCodigo();
                                String cant = opcion.getCantidad();
                                String estado1 = opcion.getEstado();

                                if (estado1.equals(" true")) {
                                    ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                    Double fisicoInventario = Double.parseDouble(nodo1.getFisicoInventario().replace(",", "."));
                                    Double pedidos = Double.parseDouble(nodo1.getPedidos().replace(",", "."));

                                    fisicoInventario = fisicoInventario + Double.parseDouble(cant.replace(",", "."));
                                    pedidos = pedidos - Double.parseDouble(cant.replace(",", "."));

                                    String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");
                                    String pedidos1 = String.valueOf(df.format(pedidos)).replace(".", ",");

                                    instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, codigo, baseUtilizada);
                                    instancias.getSql().modificarInventario("pedidos", pedidos1, codigo, baseUtilizada);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            //PROCESO GUARDAR CUENTA POR COBRAR
            if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                if (txtDiasPlazo.getText().equals("")) {
                    txtDiasPlazo.setText("0");
                }

                boolean cuotas = false;
                if (facturaCredito) {
                    cuotas = true;
                }

                String tipoCxc = "FACT";
                if (tipoProceso.equals("separe")) {
                    tipoCxc = "SEPARE";
                }

                Object[] vectCxc = {factura, tipoCxc, "PEND", "", big.getMoneda(txtTotal.getText().replace("Total: ", "")), txtDiasPlazo.getText(),
                    metodos.fechaConsulta(txtVencimiento.getText()), instancias.getUsuario(), instancias.getTerminal(), cuotas, factura2};

                ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

                if (!instancias.getSql().agregarCxc(nodoCxc)) {
                    metodos.msgError(null, "Hubo un problema al guardar la factura en cartera");
                }
            }

        } else if (tipoProceso.equals("cotizacion")) {

            ndCotizacion nodo = instancias.getSql().getDatosCotizacion(factura);

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                        instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                        txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        estado, "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        copago, txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                        "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                        big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                        tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                        tblProductos.getValueAt(i, 21), ""
                    };

                    nodo = metodos.llenarCotizacion(vector);

                    if (!instancias.getSql().agregarCotizacion(nodo)) {

                        boolean noPuedaGuardar = false;

                        instancias.getSql().eliminarCotizacion(factura);
                        while (!noPuedaGuardar) {
                            noPuedaGuardar = instancias.getSql().eliminarCotizacion(factura);
                        }

                        metodos.msgError(null, "Error al guardar la cotización");
                        return null;
                    }
                }
            }
        } else if (tipoProceso.equals("orden")) {

            ndOServicio1 nodo = instancias.getSql().getDatosOServicio1(factura);
            factura2 = factura;

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                        instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                        txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        "PENDIENTE", "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        BigDecimal.ZERO, txtPlaca1.getText(), "", "", "", "", "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                        big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                        tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                        tblProductos.getValueAt(i, 21), ""
                    };

                    nodo = metodos.llenarOServicio1(vector);

                    if (!instancias.getSql().agregarOServicio1(nodo)) {

                        boolean noPuedaGuardar = false;

                        instancias.getSql().eliminarOServicio(factura);
                        while (!noPuedaGuardar) {
                            noPuedaGuardar = instancias.getSql().eliminarOServicio(factura);
                        }

                        metodos.msgError(null, "Error al guardar la orden de servicio");
                        return null;
                    }
                }
            }

            TipoDocumento tipoMovimiento = TipoDocumento.ORDER_SERVICIO;
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

            try {
                List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
                ServicioInventario servicioInventario = new ServicioInventario(productos, new ArrayList<DetalleProducto>(), tipoMovimiento, "", tablaUtilizada, instancias.getUsuario(), null);
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            //GUARDAR ORDEN SERVICIO
            Object[] vector2 = {factura, txtPlaca.getText(), txtTipoVehiculo.getText(), txtModelo.getText(), txtNumChasis.getText(),
                metodos.fechaConsulta(metodosGenerales.fecha()), txtMarca.getText(), txtKm.getText(), txtMotor.getText(), txtColor.getText(), txtProblema.getText()};

            ndOServicio nodoOrden = metodos.llenarOServicio(vector2);
            if (!instancias.getSql().agregarOServicio(nodoOrden)) {
                metodos.msgError(null, "Hubo un problema al guardar la orden de servicio");
                return null;
            }

            int slz = 1;
            for (int i = 0; i < tblArticulos.getRowCount(); i++) {
                if ((Boolean) tblArticulos.getValueAt(i, 2)) {

                    String id = tblArticulos.getValueAt(i, 0).toString();
                    String nombre = tblArticulos.getValueAt(i, 1).toString();
                    Boolean inventario = (Boolean) tblArticulos.getValueAt(i, 2);
                    String problemasDerecha = tblArticulos.getValueAt(i, 3).toString();
                    String problemasIzquierda = tblArticulos.getValueAt(i, 4).toString();
                    String observaciones = tblArticulos.getValueAt(i, 5).toString();

                    instancias.getSql().agregarDetalleOrdenServicio(factura, id, nombre, inventario, problemasDerecha, problemasIzquierda, observaciones, slz);
                    slz++;
                }
            }

        } else if (tipoProceso.equals("pedido")) {
            ndPedido nodo = instancias.getSql().getDatosPedido(factura);

            if (nodo.getIdFactura() != null) {
                if (!instancias.getSql().aumentarConsecutivo("PEDIDO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("PEDIDO")[0]) + 1)) {
                    metodos.msgError(null, "Error al aumentar consecutivo del pedido");
                }
                factura = "PEDIDO-" + instancias.getSql().getNumConsecutivoFact1("PEDIDO")[0].toString();
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                    /*if (instancias.getConfiguraciones().isRestaurante()) {

                     String preparacionProducto = "";
                     try {
                     preparacionProducto = tblProductos.getValueAt(i, 21).toString();
                     } catch (Exception e) {
                     }

                     if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                     instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                     preparacionProducto, "fisicoInventarioPedido", bodega);
                     }
                     }*/
                    agregarRegistrosComandas(i, turno, baseUtilizada, "", factura, "");

                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fechaHora()), metodos.fechaConsulta(txtVencimiento.getText()),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                        txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        estado, "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        copago, txtPlaca1.getText(), txtNombre.getText(), "", tblProductos.getValueAt(i, 31), "",
                        "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                        big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                        tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                        tblProductos.getValueAt(i, 21), "", tblProductos.getValueAt(i, 29), tblProductos.getValueAt(i, 27)
                    };

                    nodo = metodos.llenarPedido(vector);

                    if (!instancias.getSql().agregarPedido(nodo)) {
                        boolean noPuedaGuardar = false;

                        instancias.getSql().eliminarPedido(factura);
                        while (!noPuedaGuardar) {
                            noPuedaGuardar = instancias.getSql().eliminarPedido(factura);
                        }

                        metodos.msgError(null, "Error al guardar el pedido");
                        return null;
                    }
                }
            }

            TipoDocumento tipoMovimiento = TipoDocumento.PEDIDO;
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

            try {
                List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
                ServicioInventario servicioInventario = new ServicioInventario(productos, new ArrayList<DetalleProducto>(),
                        tipoMovimiento, "", tablaUtilizada, instancias.getUsuario(), null);
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if ((Boolean) datos[54] && instancias.getConfiguraciones().isRestaurante()) {
                    int turno1 = Integer.parseInt(instancias.getSql().getTurno()) + 1;
                    if (turno1 > 100) {
                        turno1 = 1;
                    }

                    instancias.getSql().aumentarTurno(String.valueOf(turno1));
                    txtTurno.setText(String.valueOf(turno1));
                    instancias.getMaestra().setTurno(String.valueOf(turno1));
                    instancias.getMaestra().actualizarTurno();
                    instancias.getFactura().consultarMaestros();
                }
            } catch (Exception e) {
            }

        } else if (tipoProceso.equals("separe")) {
            ndPlanSepare nodo = instancias.getSql().getDatosPlanSepare(factura);

            if (nodo.getIdFactura() != null) {
                if (!instancias.getSql().aumentarConsecutivo("SEPARE", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("SEPARE")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al aumentar el consecutivo del plan separe");
                }
                factura = "SEPARE-" + instancias.getSql().getNumConsecutivoFact1("SEPARE")[0].toString();
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {
                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()),
                        metodos.fechaConsulta(txtVencimiento.getText()),
                        instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                        txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        estado, "", instancias.getDevuelta(), factura, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        copago, txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                        "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                        big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                        tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19),
                        big.getMoneda(tblProductos.getValueAt(i, 20).toString()), tblProductos.getValueAt(i, 21), tblProductos.getValueAt(i, 27),
                        tblProductos.getValueAt(i, 29), ""
                    };

                    nodo = metodos.llenarPlanSepare(vector);

                    if (!instancias.getSql().agregarPlanSepare(nodo)) {

                        boolean noPuedaGuardar = false;

                        instancias.getSql().eliminarSepare(factura);
                        while (!noPuedaGuardar) {
                            noPuedaGuardar = instancias.getSql().eliminarSepare(factura);
                        }

                        metodos.msgError(null, "Hubo un problema al guardar el plan separe");
                        return null;
                    }
                }
            }

            TipoDocumento tipoMovimiento = TipoDocumento.PLAN_SEPARE;
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

            try {
                List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
                ServicioInventario servicioInventario = new ServicioInventario(productos, new ArrayList<DetalleProducto>(), tipoMovimiento, "", tablaUtilizada, instancias.getUsuario(), null);
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            //PROCESO GUARDAR CUENTA POR COBRAR
            if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
                if (txtDiasPlazo.getText().equals("")) {
                    txtDiasPlazo.setText("0");
                }

                boolean cuotas = false;
                if (facturaCredito) {
                    cuotas = true;
                }

                String tipoCxc = "SEPARE";

                Object[] vectCxc = {factura, tipoCxc, "PEND", "", big.getMoneda(txtTotal.getText().replace("Total: ", "")), txtDiasPlazo.getText(),
                    metodos.fechaConsulta(txtVencimiento.getText()), instancias.getUsuario(), instancias.getTerminal(), cuotas, factura};

                ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

                if (!instancias.getSql().agregarCxc(nodoCxc)) {
                    metodos.msgError(null, "Hubo un problema al guardar la factura en cartera");
                }
            }
        } else if (tipoProceso.equals("mesa") && !lbTitulo.getText().equals("DOMICILIO")) {
            ndCongelada nodo = instancias.getSql().getDatosCongelada(factura);

            if (nodo.getIdFactura() != null) {
                if (!instancias.getSql().aumentarConsecutivo("CONGELADA", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("CONGELADA")[0]) + 1)) {
                    metodos.msgError(null, "Hubo un problema al aumentar el consecutivo de la congelada");
                }
                factura = "CONGELADA-" + instancias.getSql().getNumConsecutivoFact1("CONGELADA")[0].toString();
            }

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                    String preparacionProducto = "";

                    try {
                        preparacionProducto = tblProductos.getValueAt(i, 21).toString();
                    } catch (Exception e) {
                    }

                    if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                        instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                                preparacionProducto, "fisicoInventario", "");
                    }

                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()),
                        metodos.fechaConsulta(txtVencimiento.getText()),
                        instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                        txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        estado, "", instancias.getDevuelta(), factura2, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        copago, txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "",
                        "PLATO-" + i, congelada, tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                        big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                        tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                        preparacionProducto, turno, tblProductos.getValueAt(i, 27), tblProductos.getValueAt(i, 29),
                        big.getMoneda(txtTotalImpoconsumo.getText()), tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                        big.getMoneda((String) tblProductos.getValueAt(i, 8)), ""
                    };

                    nodo = metodos.llenarCongelada(vector);

                    if (!instancias.getSql().agregarCongelada(nodo)) {
                        boolean noPuedaGuardar = false;
                        instancias.getSql().eliminarMesa(factura);
                        while (!noPuedaGuardar) {
                            noPuedaGuardar = instancias.getSql().eliminarMesa(factura);
                        }
                        metodos.msgError(null, "Hubo un problema al guardar la factura");
                        return null;
                    }
                }

                String preparacionProducto = "";
                try {
                    preparacionProducto = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                if (!preparacionProducto.equals("")) {
                    String opciones1 = "Adiciones: ", ingredientes1 = "Sin: ", aderezos1 = "Aderezos: ";
                    String opciones2[], aderezos2[];

                    String observaciones = "";
                    try {
                        observaciones = preparacionProducto.split("; ")[2];
                    } catch (Exception e) {
                    }

                    String opciones = preparacionProducto.split("; ")[1];
                    String aderezos = preparacionProducto.split("; ")[0];

                    //INGRESAMOS LOS PRODUCTOS ESCOJIDOS EN LOS PRODUCTOS CON CAMBIO
                    if (!opciones.equals("")) {
                        for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {

                            String principal = opcion.getPrincipal();
                            Boolean esAdicion = opcion.esAdicion();

                            if (esAdicion) {
                                String codigo = opcion.getCodigo();
                                String cant = opcion.getCantidad();
                                String estadoProducto = opcion.getEstado();

                                if (principal.equals("") || principal.equals(" ")) {
                                    if (estadoProducto.equals(" false")) {
                                        ndProducto nodoProd = instancias.getSql().getDatosProducto(opcion.getCodigo(), baseUtilizada);
                                        ingredientes1 = ingredientes1 + nodoProd.getDescripcion() + ", ";
                                    }
                                } else {
                                    if (!principal.equals(codigo)) {
                                        if (estadoProducto.equals(" true")) {
                                            ndProducto nodoProd = instancias.getSql().getDatosProducto(opcion.getCodigo(), baseUtilizada);
                                            if (nodoProd.getGrupo() != null) {
                                                if (nodoProd.getGrupo().equals("GRP-02")) {
                                                    if (cant.substring(cant.length() - 1, cant.length()).equals("0")) {
                                                        opciones1 = opciones1 + cant.substring(0, cant.length() - 2) + " " + nodoProd.getDescripcion() + ", ";
                                                    } else {
                                                        opciones1 = opciones1 + cant + " " + nodoProd.getDescripcion() + ", ";
                                                    }
                                                } else {
                                                    opciones1 = opciones1 + nodoProd.getDescripcion() + ", ";
                                                }
                                            } else {
                                                opciones1 = opciones1 + nodoProd.getDescripcion() + ", ";
                                            }
                                        }
                                    }
                                }
                            }

                            if (!opciones1.equals("Adiciones: ")) {
                                opciones1 = opciones1.substring(0, opciones1.length() - 2);
                            }

                            if (!ingredientes1.equals("Sin: ")) {
                                ingredientes1 = ingredientes1.substring(0, ingredientes1.length() - 2);
                            }
                        }
                    }

                    if (!aderezos.equals("")) {
                        aderezos2 = aderezos.split(", ");
                        for (int k = 0; k < aderezos2.length; k++) {
                            ndProducto nodoProd = instancias.getSql().getDatosProducto(aderezos2[k], baseUtilizada);
                            aderezos1 = aderezos1 + nodoProd.getDescripcion() + ", ";
                        }
                        aderezos1 = aderezos1.substring(0, aderezos1.length() - 2);
                    }

                    if (tipoProceso.equals("mesa")) {
                        if (!instancias.getSql().agregarComanda(factura, "", tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 1).toString(),
                                opciones1, ingredientes1, "", aderezos1, tblProductos.getValueAt(i, 3).toString(), observaciones, turno, "", "PLATO-" + i)) {
                            metodos.msgError(null, "Hubo un error al guardar la comanda.");
                        }
                    }
                } else {
                    if (!instancias.getSql().agregarComanda(factura, "", tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 1).toString(),
                            "", "", "", "", tblProductos.getValueAt(i, 3).toString(), "", turno, "", "PLATO-" + i)) {
                        metodos.msgError(null, "Hubo un error al guardar la comanda.");
                    }
                }
            }

            TipoDocumento tipoMovimiento = TipoDocumento.MESA;
            String tablaUtilizada = enumBodegas.TipoBodega.BODEGA_PRINCIPAL.getNombreTabla();

            try {
                List<MovimientoInventario> productos = generarListadoProductos(tablaUtilizada);
                ServicioInventario servicioInventario = new ServicioInventario(productos, new ArrayList<DetalleProducto>(), tipoMovimiento, "", tablaUtilizada, instancias.getUsuario(), null);
                servicioInventario.procesarMovimiento();
            } catch (SQLException ex) {
                Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if ((Boolean) datos[54] && instancias.getConfiguraciones().isRestaurante()) {
                    int turno1 = Integer.parseInt(instancias.getSql().getTurno()) + 1;
                    if (turno1 > 100) {
                        turno1 = 1;
                    }
                    instancias.getSql().aumentarTurno(String.valueOf(turno1));
                    txtTurno.setText(String.valueOf(turno1));
                    instancias.getMaestra().setTurno(String.valueOf(turno1));
                    instancias.getMaestra().actualizarTurno();
                    instancias.getPedido().consultarMaestros();
                }
            } catch (Exception e) {
            }

        } else if (tipoProceso.equals("cuentaCobro")) {

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                    String hasta = "";
                    if (chkSinEstablecer.isSelected()) {
                        hasta = metodos.desdeDate(dtDesde.getCurrent());
                    } else {
                        hasta = metodos.desdeDate(dtHasta.getCurrent());
                    }

                    Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fechaHora()),
                        metodos.fechaConsulta(txtVencimiento.getText()), instancias.getEfectivoDevuelta(), instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                        big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                        big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), cotizacionesAsociadas,
                        factura.replace(tip + "-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                        "LOTECCOB-" + loteGeneral, instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                        txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), ter,
                        estado, "", instancias.getDevuelta(), factura2, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                        copago, txtPlaca1.getText(), "", "", tblProductos.getValueAt(i, 31), "", "", "", tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                        tblProductos.getValueAt(i, 13).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                        big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", big.getMoneda((String) tblProductos.getValueAt(i, 14)), "",
                        big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                        tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 3).toString().replace(",", "."),
                        "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                        cmbPeriodicidad.getSelectedItem(), metodos.desdeDate(dtDesde.getCurrent()), hasta, txtCantIncremento.getText(),
                        big.getMoneda(txtTotalImpoconsumo.getText()), tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."),
                        big.getMoneda((String) tblProductos.getValueAt(i, 8)), ""
                    };

                    ndCongelada nodo = metodos.llenarCongelada(vector);

                    if (!instancias.getSql().agregarCuentaCobro(nodo)) {
                        metodos.msgError(null, "Hubo un problema al guardar la factura");
                        return null;
                    }
                }
            }
        }

        int fila = 0;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                fila = i;
            }
        }

        aumentarConsecutivo(fila);

        //CAMBIAR CONSECUTIVO 
        if (nodoOrdenServicio != null) {
            int cant = tblProductos.getRowCount();
            if (cantProductosOrden == cant) {
                if (!instancias.getSql().modificarOServicio1(nodoOrdenServicio, "", "REALIZADO")) {
                    metodos.msgError(null, "Hubo un problema al modificar el estado de la orden");
                    return null;
                }
                instancias.getSql().cambiarEstadoOrden1(nodoOrdenServicio, "FACTURADO");
            }
        }

        if (ndPedido != null) {
            int cant = tblProductos.getRowCount();
            if (cantProductosOrden == cant) {
                if (!instancias.getSql().modificarPedido(ndPedido.getIdFactura(), "", "REALIZADO")) {
                    metodos.msgError(null, "Error al modificar estado del pedido");
                    return null;
                }
                instancias.getSql().cambiarEstadoPedido("FACTURADO", ndPedido.getIdFactura());
            }
        }

        if (!ndGuarderia.equals("")) {
            if (!instancias.getSql().modificarGuarderia1(ndGuarderia, factura)) {
                metodos.msgError(null, "Error al modificar el estado de la guarderia");
            }

            metodos.msgExito(null, "Guarderia Finalizada");
            instancias.getGuarderia().actualizarTabla();
        }

        if (!ndHospitalizacion.equals("")) {
            if (!instancias.getSql().modificarHospitalizacion1(ndHospitalizacion, factura, big.getMoneda(txtTotal.getText().replace("Total: ", "")))) {
                metodos.msgError(null, "Error al modificar estado de la hosp");
            }

            instancias.getSql().eliminar_registro("bdMedicamentosProcesosAlertas",
                    " idHospitalizacion = '" + ndHospitalizacion + "' ");

            metodos.msgExito(null, "Hospitalización Finalizada");

            instancias.getSql().modificarHospitalizacion(ndHospitalizacion, horasHospitalizacion, diasHospitalizacion);

            instancias.getIngresoHospitalizacion().cargarRegistros();
        }

        if (!ndPeluqueria.equals("")) {
            if (!instancias.getSql().modificarPeluqueria(ndPeluqueria, "Atendido", factura)) {
                metodos.msgError(null, "No fue posible modificar el estado de la Cita");
            }

            metodos.msgExito(null, "Cita Finalizada");
            instancias.getPeluqueria().cargarAgendas(instancias.getMedico());
            instancias.getPeluqueria().cargarAgendas();
        }

        actualizarConsecutivo(fila);
        lbProducto.requestFocus();

        if (tipoProceso.equals("facturacion") || (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO"))) {
            //SINO SE ESTA LLAMANDO DESDE LA ORDEN MUESTRE EL MENSAJE SINO NO POR QUE SE MUESTRA MAS ABAJO.

            if (!saltarPasosFactura1) {
                if (!desde.equals("ordenMedica") && tipoProceso.equals("facturacion")) {
                    lbObservaciones.requestFocus();
                    metodos.msgExito(null, "Factura Exitosa");
                }
            }

            if (tipoProceso.equals("mesa") && lbTitulo.getText().equals("DOMICILIO")) {
                metodos.msgExito(null, "Domicilio Exitoso");

                if (instancias.getConfiguraciones().isRestaurante()) {
                    instancias.getMenu().cambiarTitulo("MESAS");
                } else {
                    instancias.getMenu().cambiarTitulo("CONGELADAS");
                }
            }
        } else if (tipoProceso.equals("orden")) {
            metodos.msgExito(null, "Orden Exitosa");
        } else if (tipoProceso.equals("cotizacion")) {
            metodos.msgExito(null, "Cotización Exitosa");
        } else if (tipoProceso.equals("pedido")) {
            metodos.msgExito(null, "Pedido Exitoso");
        } else if (tipoProceso.equals("separe")) {
            metodos.msgExito(null, "Separe Exitoso");
        } else if (tipoProceso.equals("mesa") && !lbTitulo.getText().equals("DOMICILIO")) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                metodos.msgExito(null, "Mesa Exitosa");
            } else {
                metodos.msgExito(null, "Congelada Exitosa");
            }

            consecutivoMesa = lbNoFactura.getText();
            btnReImprimir.setVisible(true);
            btnActualizar.setVisible(true);
            btnGuardar.setVisible(true);
            btnGuardar.setText("FACTURAR");
            btnGuardar1.setVisible(true);
        } else if (tipoProceso.equals("cuentaCobro")) {
            if (!saltarPasosFactura) {
                metodos.msgExito(null, "Plantilla Exitosa");
            }
        }

        if (tipoProceso.equals("facturacion")) {
            if (txtDiasPlazo.getText().equals("0")) {
                if ((Boolean) datos[91]) {
                    if (!saltarPasosFactura) {
                        VistaDevuelta devueltaTotal = new VistaDevuelta(instancias.getMenu(), true, instancias, instancias.getDevuelta());
                        devueltaTotal.setVisible(true);
                    }
                }
            }
        }

        String impresoraComanda = "";
        try {
            impresoraComanda = datos[105].toString();
        } catch (Exception e) {
        }

        //IMPRIMIR LA COMANDA
        if (instancias.getConfiguraciones().isRestaurante()) {
            if (tipoProceso.equals("facturacion")) {
                if (ndPedido == null) {
                    if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {

                        instancias.getReporte().ver_Comanda("where factura = '" + factura + "'", factura, txtObservaciones.getText(),
                                factura, "", (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());

                        String copias = "";
                        try {
                            copias = datos[101].toString();
                        } catch (Exception e) {
                        }

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_Comanda("where factura = '" + factura + "'", factura, txtObservaciones.getText(),
                                            factura, "", (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }

            if (tipoProceso.equals("mesa")) {

                if (lbTitulo.getText().equals("DOMICILIO")) {
                    if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {
                        instancias.getReporte().ver_Comanda("where factura = '" + factura + "'", factura, txtObservaciones.getText(),
                                factura, "", (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());

                        String copias = "";
                        try {
                            copias = datos[101].toString();
                        } catch (Exception e) {
                        }

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_Comanda("where factura = '" + factura + "'", factura, txtObservaciones.getText(), factura,
                                            "", (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {
                        instancias.getReporte().ver_Comanda("where congelada = '" + factura + "'", factura, txtObservaciones.getText(),
                                factura, lbTitulo.getText(), (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());

                        String copias = "";
                        try {
                            copias = datos[101].toString();
                        } catch (Exception e) {
                        }

                        try {
                            if (copias != null || !copias.equals("")) {
                                for (int i = 0; i < Integer.parseInt(copias); i++) {
                                    instancias.getReporte().ver_Comanda("where congelada = '" + factura + "'", factura, txtObservaciones.getText(),
                                            factura, lbTitulo.getText(), (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());
                                }
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }

            if (tipoProceso.equals("pedido")) {
                if (metodos.msgPregunta(null, "¿Desea generar comanda?") == 0) {
                    instancias.getReporte().ver_Comanda("where pedido = '" + factura + "'", factura, txtObservaciones.getText(),
                            factura, lbTitulo.getText(), (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());

                    String copias = "";
                    try {
                        copias = datos[101].toString();
                    } catch (Exception e) {
                    }

                    try {
                        if (copias != null || !copias.equals("")) {
                            for (int i = 0; i < Integer.parseInt(copias); i++) {
                                instancias.getReporte().ver_Comanda("where pedido = '" + factura + "'", factura, txtObservaciones.getText(),
                                        factura, lbTitulo.getText(), (Boolean) datos[103], impresoraComanda, cmbVendedor.getSelectedItem().toString());
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        // FIN DE LA IMPRESION DE LA COMANDA

        if (tipoProceso.equals("mesa") && !lbTitulo.getText().equals("DOMICILIO")) {
            instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
        }

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
                        impresora = datos[82].toString();
                    } else if (rdCarta.isSelected()) {
                        tipoFact = "facturaMedicaCompleta";
                        impresora = datos[83].toString();
                    } else if (rdPos.isSelected()) {
                        tipoFact = "facturaMedica";
                        impresora = datos[81].toString();
                    }

                    String impoconsumo = datos[84].toString();
                    String retenciones = datos[85].toString();

                    String condicion;
                    if ((Boolean) datos[50] && instancias.getConfiguraciones().isRestaurante()) {
                        condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' ");
                    } else {
                        if (instancias.getConfiguraciones().isRestaurante()) {
                            condicion = metodos.sentenciaImpresionFactura("agrupada", " WHERE bdFactura.factura = '" + factura2 + "' ");
                        } else {
                            condicion = metodos.sentenciaImpresionFactura("", " WHERE bdFactura.factura = '" + factura2 + "' ");
                        }
                    }

                    //IMPRESIÓN ORIGINAL
                    instancias.getReporte().ver_Factura(observaciones,
                            instancias.getInformacionEmpresaCompleto(), legal, "Original", pie,
                            tipoFact, factura2, !(Boolean) datos[68], "", impresora, impoconsumo, retenciones, condicion, false);

                    //COPIAS DE LA IMPRESION
                    int cantidad;
                    try {
                        cantidad = Integer.parseInt(datos[73].toString());
                    } catch (Exception e) {
                        cantidad = 0;
                    }

                    if (cantidad > 0) {
                        for (int i = 0; i < cantidad; i++) {
                            instancias.getReporte().ver_Factura(observaciones, instancias.getInformacionEmpresaCompleto(), legal, "Copia " + (i + 1),
                                    pie, tipoFact, factura2, !(Boolean) datos[68], "", impresora, impoconsumo, retenciones, condicion, false);
                        }
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

        if (tipoProceso.equals("facturacion")) {
            if (instancias.isUbicacion()) {
                try {
                    if (metodos.msgPregunta(null, "¿Desea imprimir ubicación?") == 0) {
                        instancias.getReporte().ver_ubicacion(factura2, false);
                    }
                } catch (Exception e) {
                }
            }
        }

        if (cotizaciones != null) {
            for (String cotizacion : cotizaciones) {
                if (!instancias.getSql().modificarCotizaciones(cotizacion, factura, "FACTURADO")) {
                    metodos.msgError(null, "Hubo un problema al modificar el estado de la cotizacion");
                    return null;
                }
            }
        }

        if (tipoProceso.equals("facturacion")) {
            int cantidad = 0;
            //PAGOS A TERCEROS
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                BigDecimal pago = big.getMoneda(tblProductos.getValueAt(i, 19).toString());
                if (pago.compareTo(BigDecimal.ZERO) > 0) {
                    cantidad = cantidad + 1;
                }
            }

            Object[][] pagos = new Object[cantidad][4];

            int z = 0;
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                BigDecimal pago = big.getMoneda(tblProductos.getValueAt(i, 19).toString());
                if (pago.compareTo(BigDecimal.ZERO) > 0) {
                    pagos[z][0] = tblProductos.getValueAt(i, 1);
                    pagos[z][1] = tblProductos.getValueAt(i, 19);
                    pagos[z][2] = tblProductos.getValueAt(i, 32);
                    pagos[z][3] = factura;
                    z++;
                }
            }

            if (cantidad > 0) {
                dlgPagosProveedores pagosProveedores = new dlgPagosProveedores(null, true, pagos);
                pagosProveedores.setLocationRelativeTo(null);
                pagosProveedores.setVisible(true);
            }
        }

        if (tipoProceso.equals("orden")) {
            if (!saltarPasosFactura) {
                if (metodos.msgPregunta(null, "¿Desea generar factura?") == 0) {

                    this.tipoProceso = "facturacion";
                    txtCargar.setText(factura.replace("OSERV-", ""));
                    cargarMovimiento();
                    txtObservaciones.setText(factura + ". " + txtObservaciones.getText());
                    for (int i = 0; i < tblProductos.getRowCount(); i++) {
                        calcularTabla(i, true);
                    }

                    saltarPasosFactura = true;
                    instancias.setEfectivoDevuelta(big.getMoneda(txtTotal.getText().replace("Total: ", "")));
                    btnGuardar1ActionPerformed(null);
                }
            }

            this.tipoProceso = "orden";
        }

        if (facturaCredito) {
            instancias.getReporte().verPrestamo(credito1, instancias.getInformacionEmpresa());
        }

        //Si es diferente de medico va a ingresar, ya que es de otro tipo y puede ingresar y cambiar el estado de la cita.
        if (instancias.getConfiguraciones().isAgenda()) {

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

        fechaFacturaAutomatica = "";
        saltarPasosFactura = false;
        desdeParqueadero = false;

        if (tipoActual.equals("pedidoActual")) {
            this.tipoProceso = "pedido";
            tipoActual = "";
        }

        if (tipoProceso.equals("mesa")) {
            if (instancias.getConfiguraciones().isRestaurante()) {
                instancias.getMesas().cargarRegistrosMesas();
                instancias.getMesas().cargarRegistros();
                instancias.getMesas().setSelected(true);
            } else {
                instancias.getMesas1().cargarRegistros();
                instancias.getMesas1().setSelected(true);
            }

            if (!instancias.getMenu().getSeVeElMenu()) {
                instancias.getMenu().expandirMenu();
            }
        } else {
            limpiar(true, "");
        }

        lbNit.requestFocus();
        tblProductos.removeEditor();
        tblInventario.removeEditor();

        actualizarTablaResoluciones();

        return factura2;
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

    public void desdeLavadero(Object[][] Productos, String cliente, String diasPlazo, String placa, String obv) {
        limpiar(true, "");

        int i = 0;
        for (Object[] reg : Productos) {
            cargarProducto((String) reg[0], (String) reg[1], 1, "", "", "", true, "", "", "", "", "");
            tblProductos.setValueAt(big.setMonedaExacta(big.getMoneda(reg[3].toString())), i, 2);
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

        txtPlaca1.setText(placa);
        instancias.getLavadero().setFactura("FACT-" + lbNoFactura.getText());
        txtObservaciones.setText(obv);

        saltarPasosFactura = true;

        if (metodos.msgPregunta(null, "¿Desea imprimir factura?") == 0) {
            btnGuardar1ActionPerformed(null);
        } else {
            btnGuardarActionPerformed(null);
        }

    }

    public String desdeLavadero(Object[][] Productos, String cliente, String placa, String obv) {
        limpiar(true, "SI");

        int i = 0;
        for (Object[] reg : Productos) {
            cargarProducto((String) reg[0], (String) reg[1], 1, "", "", "", true, "", "", "", "", "");
            tblProductos.setValueAt(big.setMonedaExacta(big.getMoneda(reg[3].toString())), i, 2);
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.setRowSelectionInterval(i, i);
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
            i++;
        }

        txtNit.setText(cliente);
        cargarCliente(cliente);
        txtPlaca1.setText(placa);
        txtObservaciones.setText(obv);

        saltarPasosFactura = true;
        return validacionInicialFactura(false);
    }

    public void actualizarOrdenServicio(Object[][] Productos, String orden) {
        limpiar(true, "SI");

        orden = orden.replace("OSERV-", "");
        txtCargar.setText(orden);
        lbNoFactura.setText(orden);
        cargarOServicio(orden, "OSERV-");

        DefaultTableModel modelo = (DefaultTableModel) tblProductos.getModel();
        while (tblProductos.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        modeloInventario = (DefaultTableModel) tblInventario.getModel();
        while (tblInventario.getRowCount() > 0) {
            modeloInventario.removeRow(0);
        }

        int i = 0;
        btnActualizar.setEnabled(true);
        for (Object[] reg : Productos) {
            cargarProducto((String) reg[0], (String) reg[1], 1, "", "", "", false, "", "", "", "", "");

            tblProductos.setValueAt((String) reg[1], i, 3);
            tblProductos.setValueAt(big.setMonedaExacta(big.getMoneda(reg[3].toString())), i, 2);
//            tblProductos.setValueAt(big.getBigDecimal(reg[5].toString()), i, 5);

            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.setRowSelectionInterval(i, i);
            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
            tblProductosKeyReleased(x);
            i++;
        }
        txtCargar.setText(orden);
        int j = tblProductos.getRowCount();
        btnActualizarActionPerformed(null);
    }

    public void facturarCongelada(Boolean imprimir) {

        ModeloContacto datosCliente = !ID_CLIENTE_CARGADO.equals("") ? instancias.getSql().getDatosTercero(ID_CLIENTE_CARGADO) : new ModeloContacto();
        if (!validacionesGeneralesDeFacturacion(datosCliente)) {
            return;
        }

        String baseUtilizada = "bdProductos";
        if (instancias.getConfiguraciones().isRestaurante()) {
            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                ndProducto nodo = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

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
                                String cant = opcion.getCantidad();
                                String estado = opcion.getEstado();

                                if (estado.equals(" true")) {
                                    ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                    if (nodo1.getGrupo() != null) {
                                        if (nodo1.getGrupo().equals("GRP-02")) {
                                            cargarProducto(codigo, cant, 1, "", "", "", false, "", "", "", "", "");
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

        VistaMetodoPagos devuelta;
        if (txtFechaFactura.getText().equals(txtVencimiento.getText())) {
            String miTipo = "";
            if (tipoProceso.equals("facturacion")) {
                miTipo = "facturacion";
            }
            devuelta = new VistaMetodoPagos(instancias.getMenu(), true, big.getMoneda(txtTotal.getText().replace("Total: ", "")), instancias, miTipo,
                    ID_CLIENTE_CARGADO, big.getMoneda(txtSubTotal.getText()));
//            devuelta.setNC(big.getMoneda(txtNc.getText()));
            devuelta.show();
        }

        if (instancias.getCancelarFactura()) {
            borrarAdiciones();
            System.out.println("DEVOLVIO LA FACTURA");
            instancias.setCancelarFactura(false);
            return;
        }

        String factura = "", factura2 = "", prefijo = "";
        int fila = 0;
        for (int i = 0; i < tblComprobantes.getRowCount(); i++) {
            if ((Boolean) tblComprobantes.getValueAt(i, 2)) {
                fila = i;
            }
        }

        try {
            prefijo = tblComprobantes.getValueAt(fila, 8).toString();
        } catch (Exception e) {
            prefijo = "";
        }

        factura2 = guardarValidacion(fila);
        factura = factura2.replace(prefijo, "");

        String vendedor = "";
        try {
            vendedor = cmbVendedor.getSelectedItem().toString();
        } catch (Exception e) {
            vendedor = "";
        }

        String congelada = "";
        try {
            congelada = instancias.getTitulo();
        } catch (Exception e) {
        }

        String ter = instancias.getTerminal();

        String turno = "";
        if ((Boolean) datos[54] && instancias.getConfiguraciones().isRestaurante()) {
            turno = instancias.getSql().getTurno();
        } else if (txtTurno.isVisible()) {
            turno = txtTurno.getText();
        }

        agregamosRegistrosMediosDePago(factura2);

        String tipoComprobante = obtenerTipoComprobante();
        tipoComprobante = tipoComprobante.equals("") ? Constantes.FACTURACION_NORMAL : tipoComprobante;

        if (instancias.getConfiguraciones().isFacturaElectronica() && Constantes.esFacturacionElectronica(tipoComprobante)) {
            boolean facturaElectronicaExitosa = false;
            ModeloFacturacionElectronica modeloFacturacionElectronica = crearModeloFacturacionEletronica(factura, factura2, datosCliente);

            try {
                facturaElectronicaExitosa = consumidorFacturacionElectronica.generarFacturacionElectronica(modeloFacturacionElectronica, false, false,
                        false, rdPos.isSelected());
            } catch (Exception ex) {
                System.err.println("Hubo un error al enviar el JSON de la factura electronica: " + ex);
            }

            if (!facturaElectronicaExitosa) {
                borrarAdiciones();
                facturandoPedidos = false;
                instancias.setCancelarFactura(false);
                return;
            }
        }

        ndFactura nodo;
        instancias.getSql().agregarVerificarFactura(factura, ID_CLIENTE_CARGADO, factura2, ter,
                big.getMoneda(txtTotal.getText().replace("Total: ", "")),
                metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), vendedor, congelada, txtPlaca1.getText(), turno);

        int diasPlazo;
        try {
            diasPlazo = Integer.parseInt(txtDiasPlazo.getText());
        } catch (Exception e) {
            diasPlazo = 0;
        }

        BigDecimal efectivo = instancias.getEfectivoDevuelta();
        if (diasPlazo > 0) {
//            efectivo = big.getMoneda(txtTotal.getText().replace("Total: ", ""));
            efectivo = big.getMoneda("0");
        }

        String por = "";
        if (cmbRtf.getSelectedIndex() == 0) {
            por = "0";
        } else {
            por = cmbRtf.getSelectedItem().toString();
        }

        if (congelada.length() > 20) {
            Object[] nombres = congelada.split("<br>");
            congelada = nombres[0].toString().substring(26, nombres[0].toString().length());
        }

        String hora = metodosGenerales.hora();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            if (!tblProductos.getValueAt(i, 16).equals("REALIZADO")) {

                String imei = "", lote = "", idProd = "";

                if (instancias.getConfiguraciones().isProductosDetallados()) {
                    imei = tblProductos.getValueAt(i, 27).toString();
                    lote = tblProductos.getValueAt(i, 28).toString();
                    idProd = tblProductos.getValueAt(i, 29).toString();
                }

                String consecutivoCosteo = idCosteo;
                BigDecimal costoCosteo1 = BigDecimal.ZERO, totalUtilidad = BigDecimal.ZERO;

                try {
                    costoCosteo1 = costoCosteo;
                } catch (Exception e) {
                }

                if (consecutivoCosteo == null) {
                    consecutivoCosteo = "";
                }

                if (!consecutivoCosteo.equals("")) {
                    totalUtilidad = big.getMoneda(tblProductos.getValueAt(i, 9).toString()).subtract(costoCosteo1);
                } else {
                    totalUtilidad = big.getMoneda((String) tblProductos.getValueAt(i, 14));
                    if (totalUtilidad.compareTo(BigDecimal.ZERO) < 0) {
                        totalUtilidad = BigDecimal.ZERO;
                    }
                }

                BigDecimal ponderado = BigDecimal.ZERO;
                try {
                    UltimoPonderado ultimoPonderado = servicioActualizacionPonderado.obtenerUltimoPonderado(tblProductos.getValueAt(i, 32).toString());
                    ponderado = ultimoPonderado.getNuevoPonderado();
                } catch (SQLException ex) {
                    Logger.getLogger(VistaInventarioInicial.class.getName()).log(Level.SEVERE, null, ex);
                    alertas.bigAlert("No se pudo consultar el último ponderado del producto");
                }

                Object[] vector = {factura, ID_CLIENTE_CARGADO, vendedor, "", metodos.fechaConsulta(metodosGenerales.fecha()), metodos.fechaConsulta(txtVencimiento.getText()),
                    efectivo, instancias.getNcDevuelta(), instancias.getChequeDevuelta(), instancias.getTarjetaDevuelta(),
                    big.getMoneda(txtTotal.getText().replace("Total: ", "")), big.getMoneda(txtTotalDescuentos.getText()),
                    big.getMoneda(txtTotalIva.getText()), big.getMoneda(txtSubTotal.getText()), "",
                    factura.replace("FACT-", ""), false, "", !txtFechaFactura.getText().equals(txtVencimiento.getText()),
                    "", instancias.getUsuario(), big.getMoneda(txtRiva.getText()), big.getMoneda("0"), big.getMoneda(txtRtf.getText()), big.getMoneda(por),
                    txtObservaciones.getText(), false, "", false, "", "", metodos.fechaConsulta(metodos.sumarFecha(txtVencimiento.getText(), cantDias)), instancias.getTerminal(),
                    "PENDIENTE", "", instancias.getDevuelta(), factura2, instancias.getResolucion(), metodos.fechaConsulta(metodosGenerales.fecha()), "", "",
                    "0", txtPlaca1.getText(), "", "", "", "",
                    "", congelada, tblProductos.getValueAt(i, 32), big.getMoneda((String) tblProductos.getValueAt(i, 2)),
                    tblProductos.getValueAt(i, 3).toString().replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 6)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 9)), big.getMoneda((String) tblProductos.getValueAt(i, 33)),
                    big.getMoneda((String) tblProductos.getValueAt(i, 4)), "", totalUtilidad, "",
                    big.getBigDecimal(tblProductos.getValueAt(i, 5).toString().replace(",", ".")).setScale(2, RoundingMode.HALF_DOWN) + "",
                    tblProductos.getValueAt(i, 1), tblProductos.getValueAt(i, 12) + "", tblProductos.getValueAt(i, 13).toString().replace(",", "."),
                    "PENDIENTE", tblProductos.getValueAt(i, 7), tblProductos.getValueAt(i, 19), big.getMoneda(tblProductos.getValueAt(i, 20).toString()),
                    tblProductos.getValueAt(i, 21), BigDecimal.ZERO, turno, big.getMoneda(txtTotalImpoconsumo.getText()), instancias.getFranquisia(),
                    instancias.getComision(), instancias.getValorComision(), instancias.getTotalFacturaComision(), imei, lote, idProd, "",
                    instancias.getTarjetaCredito(), instancias.getTotalPropina(), instancias.getPorcPropina(), consecutivoCosteo, hora,
                    tblProductos.getValueAt(i, 23).toString().replace(".", "").replace(",", "."), big.getMoneda((String) tblProductos.getValueAt(i, 8)),
                    chkSisteCredito.isSelected(), "", ponderado, tipoComprobante
                };

                idCosteo = "";
                costoCosteo = BigDecimal.ZERO;

                nodo = metodos.llenarFactura(vector);

                if (!instancias.getSql().agregarFactura(nodo)) {
                    boolean noPuedaGuardar = false;
                    instancias.getSql().eliminarFactura(factura);
                    while (!noPuedaGuardar) {
                        noPuedaGuardar = instancias.getSql().eliminarFactura(factura);
                    }

                    metodos.msgError(null, "Hubo un problema al guardar la factura");
                }
            }

            if (instancias.getConfiguraciones().isRestaurante()) {
                if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                    String preparacion = "";

                    try {
                        preparacion = tblProductos.getValueAt(i, 21).toString();
                    } catch (Exception e) {
                    }

                    instancias.getArmado().facturarPlato(tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 3).toString(),
                            preparacion, "descontarTodo", "");
                }
            } else {
                try {
                    if (instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada).getUsuario().equalsIgnoreCase("FACTURA")) {
                        String preparacion = tblProductos.getValueAt(i, 21).toString();
                        instancias.getArmado().facturarPreparado(tblProductos.getValueAt(i, 32).toString(),
                                tblProductos.getValueAt(i, 3).toString(), preparacion, "");
                    }
                } catch (Exception e) {
                }
            }

            if (tipoProceso.equals("facturacion") || tipoProceso.equals("mesa")) {
                String cadena = "";

                try {
                    cadena = tblProductos.getValueAt(i, 21).toString();
                } catch (Exception e) {
                }

                if (!cadena.equals("")) {
                    String opciones1 = "Adiciones: ", ingredientes1 = "Sin: ", aderezos1 = "Aderezos: ";
                    String opciones2[], aderezos2[];

                    String observaciones = "";
                    try {
                        observaciones = cadena.split("; ")[2];
                    } catch (Exception e) {
                    }

                    String opciones = cadena.split("; ")[1];
                    String aderezos = cadena.split("; ")[0];

                    //INGRESAMOS LOS PRODUCTOS ESCOJIDOS EN LOS PRODUCTOS CON CAMBIO
                    if (!opciones.equals("")) {
                        for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {
                            Boolean esAdicion = opcion.esAdicion();
                            String principal = opcion.getPrincipal();

                            if (esAdicion) {
                                String codigo = opcion.getCodigo();
                                String cant = opcion.getCantidad();
                                String estadoProducto = opcion.getEstado();

                                if (principal.equals("") || principal.equals(" ")) {
                                    if (estadoProducto.equals(" false")) {
                                        ndProducto nodoProd = instancias.getSql().getDatosProducto(opcion.getCodigo(), baseUtilizada);
                                        ingredientes1 = ingredientes1 + nodoProd.getDescripcion() + ", ";
                                    }
                                } else {
                                    if (!principal.equals(codigo)) {
                                        if (estadoProducto.equals(" true")) {
                                            ndProducto nodoProd = instancias.getSql().getDatosProducto(opcion.getCodigo(), baseUtilizada);
                                            if (nodoProd.getGrupo() != null) {
                                                if (nodoProd.getGrupo().equals("GRP-02")) {
                                                    if (cant.substring(cant.length() - 1, cant.length()).equals("0")) {
                                                        opciones1 = opciones1 + cant.substring(0, cant.length() - 2) + " " + nodoProd.getDescripcion() + ", ";
                                                    } else {
                                                        opciones1 = opciones1 + cant + " " + nodoProd.getDescripcion() + ", ";
                                                    }
                                                } else {
                                                    opciones1 = opciones1 + nodoProd.getDescripcion() + ", ";
                                                }
                                            } else {
                                                opciones1 = opciones1 + nodoProd.getDescripcion() + ", ";
                                            }
                                        }
                                    }
                                }
                            }

                            if (!opciones1.equals("Adiciones: ")) {
                                opciones1 = opciones1.substring(0, opciones1.length() - 2);
                            }

                            if (!ingredientes1.equals("Sin: ")) {
                                ingredientes1 = ingredientes1.substring(0, ingredientes1.length() - 2);
                            }
                        }
                    }

                    if (!aderezos.equals("")) {
                        aderezos2 = aderezos.split(", ");
                        for (int k = 0; k < aderezos2.length; k++) {
                            ndProducto nodoProd = instancias.getSql().getDatosProducto(aderezos2[k], baseUtilizada);
                            aderezos1 = aderezos1 + nodoProd.getDescripcion() + ", ";
                        }
                        aderezos1 = aderezos1.substring(0, aderezos1.length() - 2);
                    }

                    if (tipoProceso.equals("mesa")) {
                        if (!instancias.getSql().agregarComanda(factura, "", tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 1).toString(),
                                opciones1, ingredientes1, "", aderezos1, tblProductos.getValueAt(i, 3).toString(), observaciones, turno, "", "PLATO-" + i)) {
                            metodos.msgError(null, "Error al guardar la comanda.");
                        }
                    } else if (tipoProceso.equals("facturacion")) {
                        if (!instancias.getSql().agregarComanda("", factura, tblProductos.getValueAt(i, 32).toString(), tblProductos.getValueAt(i, 1).toString(),
                                opciones1, ingredientes1, "", aderezos1, tblProductos.getValueAt(i, 3).toString(), observaciones, turno, "", "PLATO-" + i)) {
                            metodos.msgError(null, "Error al guardar la comanda.");
                        }
                    }
                }

                instancias.getSql().cambiarEstadoMesa(instancias.getTitulo(), "DISPONIBLE");
            }

            ndProducto producto = instancias.getSql().getDatosProducto(tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
            double cantidad;
            double inventario;
            double fisicoInventario;

            try {
                cantidad = Double.parseDouble(producto.getVentas().replace(",", "."));
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
                fisicoInventario = 0;
            }

            double cant2;
            try {
                cant2 = Double.parseDouble(tblProductos.getValueAt(i, 13).toString());
            } catch (Exception e) {
                cant2 = Double.parseDouble(tblProductos.getValueAt(i, 13).toString().substring(0, tblProductos.getValueAt(i, 13).toString().length() - 2));
            }

            inventario = inventario - cant2;
            fisicoInventario = fisicoInventario - cant2;
            double total = cantidad + cant2;

            String total1 = String.valueOf(df.format(total)).replace(".", ",");
            String inventario1 = String.valueOf(df.format(inventario)).replace(".", ",");
            String fisicoInventario1 = String.valueOf(df.format(fisicoInventario)).replace(".", ",");

            instancias.getSql().modificarInventario("ventas", total1, tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
            instancias.getSql().modificarInventario("inventario", inventario1, tblProductos.getValueAt(i, 32).toString(), baseUtilizada);
            instancias.getSql().modificarInventario("fisicoInventario", fisicoInventario1, tblProductos.getValueAt(i, 32).toString(), baseUtilizada);

            // DESCONTAR DEL INVENTARIO DETALLADO //
            if (instancias.getConfiguraciones().isProductosDetallados()) {
                String cod = "";
                try {
                    cod = tblProductos.getValueAt(i, 29).toString();
                } catch (Exception e) {
                }

                if (!cod.equals("")) {
                    String tipoProd = "";
                    if (producto.getTipoProducto() != null) {
                        if (producto.getTipoProducto().equals("IMEI")) {
                            tipoProd = "Imei";
                        } else if (producto.getTipoProducto().equals("Fecha/Lote")) {
                            tipoProd = "Fecha/Lote";
                        } else if (producto.getTipoProducto().equals("Color")) {
                            tipoProd = "Color";
                        } else if (producto.getTipoProducto().equals("Serial")) {
                            tipoProd = "Serial";
                        } else if (producto.getTipoProducto().equals("Talla")) {
                            tipoProd = "Talla";
                        } else if (producto.getTipoProducto().equals("ColorTalla")) {
                            tipoProd = "ColorTalla";
                        } else if (producto.getTipoProducto().equals("SerialColor")) {
                            tipoProd = "SerialColor";
                        } else {
                            tipoProd = "";
                        }
                    }

                    if (tipoProd.equals("Imei") || tipoProd.equals("Serial") || tipoProd.equals("SerialColor")) {
                        instancias.getSql().modificarEstadoDetalleProductos(cod, "NO-DISPONIBLE");
                    } else {
                        BigDecimal cantidadActual = new BigDecimal(instancias.getSql().getCantidadProductos(cod).replace(",", "."));
                        BigDecimal cantidadTabla1 = new BigDecimal(tblProductos.getValueAt(i, 13).toString().replace(",", "."));
                        BigDecimal cantidadFinal = cantidadActual.subtract(cantidadTabla1);
                        instancias.getSql().modificarCantidadesDetalleProductos(cod, cantidadFinal);
                    }
                }
            }
            // FIN DE DESCONTAR DEL INVENTARIO SEPARADO // 
        }

        this.tipoProceso = "facturacion";
        aumentarConsecutivo(fila);

        if (!txtFechaFactura.getText().equals(txtVencimiento.getText())) {
            if (txtDiasPlazo.getText().equals("")) {
                txtDiasPlazo.setText("0");
            }

            boolean cuotas = false;
            if (facturaCredito) {
                cuotas = true;
            }

            String tipoCxc = "FACT";

            Object[] vectCxc = {factura, tipoCxc, "PEND", "", big.getMoneda(txtTotal.getText().replace("Total: ", "")), txtDiasPlazo.getText(),
                metodos.fechaConsulta(txtVencimiento.getText()), instancias.getUsuario(), instancias.getTerminal(), cuotas, factura2
            };

            ndCxc nodoCxc = metodos.llenarCxc(vectCxc);

            if (!instancias.getSql().agregarCxc(nodoCxc)) {
                metodos.msgError(null, "Hubo un problema al guardar la factura en cartera");
            }
        }

        metodos.msgExito(null, "Factura Exitosa");
        instancias.setEfectivoDevuelta(big.getBigDecimal("0"));

        if (imprimir) {
            imprimir(factura, factura2);
        }

        this.tipoProceso = "mesa";

        instancias.getSql().eliminarMesa("CONGELADA-" + consecutivoMesa);

        if (instancias.getConfiguraciones().isRestaurante()) {
            instancias.getMesas().cargarRegistrosMesas();
            instancias.getMesas().cargarRegistros();
            instancias.getMesas().setSelected(true);
        } else {
            instancias.getMesas1().cargarRegistros();
            instancias.getMesas1().setSelected(true);
        }

        if (!instancias.getMenu().getSeVeElMenu()) {
            instancias.getMenu().expandirMenu();
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
            cargarProducto(prod, "1", 1, "", "", cadena, false, "", "", "", "", "");
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

            if ((Boolean) datos[95]) {
                txtDiasPlazo.setText(nodo.getPlazo());
                calcularDiasPlazo(null);
            }

            if ((Boolean) datos[42]) {
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

        if (facturaCredito) {
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

    public void desdeTraslado(String cliente, String[][] productos) {

        limpiar(true, "SI");
        txtNit.setText(cliente);
        cargarCliente(cliente);

        for (String[] producto : productos) {
            cargarProducto((String) producto[0], new Double((String) producto[1]).intValue() + "", 1, "", "", "", true, "", "", "", "", "");
        }

        tblProductos.editCellAt(tblProductos.getSelectedRow(), 6);
        tblProductos.setColumnSelectionInterval(6, 6);
        tblProductos.transferFocus();

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            tblProductos.setValueAt(productos[i][1], i, 3);

            BigDecimal valor, cantidad, descuento, iva, subtotal, total, porcentaje, aux;

            tblProductos.setValueAt(big.setMonedaExacta(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 6)))), i, 6);

            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 2)));
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 3)));
            subtotal = valor.multiply(cantidad);
            Double porcentaje2 = Double.parseDouble(String.valueOf(tblProductos.getValueAt(i, 5))) / 100;
            porcentaje = big.getBigDecimal(porcentaje2);
            descuento = big.getMoneda(big.setNumero(subtotal.multiply(porcentaje)));
            iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 7)));
            iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
            aux = valor.divide(iva, 2);
            aux = valor.subtract(aux);
            subtotal = (subtotal.subtract(descuento)).subtract(aux);
            total = subtotal.add(aux);

            tblProductos.setValueAt(big.setMonedaExacta(valor), i, 2);
            tblProductos.setValueAt(big.setMonedaExacta(subtotal), i, 4);
            tblProductos.setValueAt(big.setMonedaExacta(descuento), i, 6);
            tblProductos.setValueAt(big.setMonedaExacta(aux).replace(this.simbolo + " ", ""), i, 33);
            tblProductos.setValueAt(big.setMonedaExacta(total), i, 9);
        }
        cargarTotales();
    }

    public void desdeOrden(String cliente, String productos) {

        limpiar(true, "SI");
        txtNit.setText(cliente);
        cargarCliente(cliente);

        cargarProducto(productos, "1", 1, "", "", "", true, "", "", "", "", "");

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
//            tblProductos.setValueAt(productos[i][1], i, 3);
//            tblProductos.setValueAt(productos[i][2], i, 5);
//            tblProductos.setValueAt(productos[i][3], i, 2);

            BigDecimal valor, cantidad, descuento, iva, subtotal, total, porcentaje, aux;

            tblProductos.setValueAt(big.setMonedaExacta(big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 6)))), i, 6);

            valor = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 2)));
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 3)));
            subtotal = valor.multiply(cantidad);
            Double porcentaje2 = Double.parseDouble(String.valueOf(tblProductos.getValueAt(i, 5))) / 100;
            porcentaje = big.getBigDecimal(porcentaje2);
            descuento = big.getMoneda(big.setNumero(subtotal.multiply(porcentaje)));
            iva = big.getMoneda(String.valueOf(tblProductos.getValueAt(i, 7)));
            iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
            aux = valor.divide(iva, 2);
            aux = valor.subtract(aux);

            subtotal = (subtotal.subtract(descuento)).subtract(aux);
            total = subtotal.add(aux);

            tblProductos.setValueAt(big.setMonedaExacta(valor), i, 2);
            tblProductos.setValueAt(big.setMonedaExacta(subtotal), i, 4);
            tblProductos.setValueAt(big.setMonedaExacta(descuento), i, 6);
            tblProductos.setValueAt(big.setMonedaExacta(aux).replace(this.simbolo + " ", ""), i, 33);
            tblProductos.setValueAt(big.setMonedaExacta(total), i, 9);

        }

        saltarPasosFactura = false;
        cargarTotales();
        btnGuardarActionPerformed(null);
    }

    public void desdeHospitalizacion(String cliente, String consecutivo, String horas, String dias) {

        limpiar(true, "SI");
        txtNit.setText(cliente);
        cargarCliente(cliente);

        if (!horas.equals("0")) {
            cargarProducto("HSP1", horas, 1, "", "", "", true, "", "", "", "", "");
        }

        if (!dias.equals("0")) {
            cargarProducto("HSP2", dias, 1, "", "", "", true, "", "", "", "", "");
        }

        Object[][] productosAdicionales = instancias.getSql().getProductosHospitalizaciones(consecutivo);

        if (productosAdicionales != null) {
            for (int i = 0; i < productosAdicionales.length; i++) {
                cargarProducto(productosAdicionales[i][0].toString(), productosAdicionales[i][2].toString(), 1, "", "", "", false, "", "", "", "", "");
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

        limpiar(true, "SI");
        txtNit.setText(cliente);
        cargarCliente(cliente);

        if (!horas.equals("0")) {
            cargarProducto("GD1", horas, 1, "", "", "", true, "", "", "", "", "");
        }

        if (!dias.equals("0")) {
            cargarProducto("GD2", dias, 1, "", "", "", true, "", "", "", "", "");
        }

        Object[][] productosAdicionales = instancias.getSql().getProductosProductosServiciosAdicionales(consecutivo);
        if (productosAdicionales != null) {
            for (int i = 0; i < productosAdicionales.length; i++) {
                cargarProducto(productosAdicionales[i][0].toString(), productosAdicionales[i][1].toString(), 1, productosAdicionales[i][3].toString(),
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
        btnGuardar1ActionPerformed(null);
    }

    public void desdePeluqueria(String cliente, String productos, String consecutivo) {

        this.setVisible(true);

        limpiar(true, "SI");
        txtNit.setText(cliente);
        cargarCliente(cliente);

        cargarProducto(productos, "1", 1, "", "", "", true, "", "", "", "", "");

        Object[][] productosAdicionales = instancias.getSql().getProductosProductosServiciosAdicionales(consecutivo);
        if (productosAdicionales != null) {
            for (int i = 0; i < productosAdicionales.length; i++) {
                cargarProducto(productosAdicionales[i][0].toString(), productosAdicionales[i][1].toString(), 1, productosAdicionales[i][3].toString(),
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

    public String facturarServicioPTM(String servicio, int valor, String contrato) {
        limpiar(true, "SI");
        txtNit.setText("1010");
        cargarCliente("1010");
        cargarProducto("PTM", "1", 1, "", "", "", true, "", "", "", "", "");
        tblProductos.setValueAt("SERVICIO PTM (" + servicio + ")", 0, 1);
        tblProductos.setValueAt(big.setMoneda(big.getMoneda(String.valueOf(valor))), 0, 2);
        cargarTotales();
        txtObservaciones.setText("Contrato: " + contrato);
        saltarPasosFactura = true;
        return validacionInicialFactura(false);
//        btnGuardarActionPerformed(null);
    }

    public String desdeOrdenMedica(String cliente, String[][] productos, boolean opc) {
        limpiar(true, "");
        rdTipoNormal.setSelected(!opc);
        rdTipoCopago.setSelected(opc);

        txtNit.setText(cliente);
        cargarCliente(cliente);

        for (int i = 0; i < productos.length; i++) {
            try {
                if (!productos[i][0].equals("") || productos[i][0] != null) {
                    cargarProducto(productos[i][0], productos[i][1], 1, "", "", "", false, "", "", "", "", "");
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

        limpiar(true, "");

        rdTipoNormal.setSelected(!opc);
        rdTipoCopago.setSelected(opc);

        txtNit.setText(cliente);
        cargarCliente(cliente);

        cargarProducto(productos[0], productos[1], 1, "", "", "", false, "", "", "", "", "");

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

    public void desdeTrasladosBodegas(Object[][] datos, String cliente, String diasPlazo) {
        limpiar(true, "");

        trasladoBod = "SI";

        txtNit.setText(cliente);
        cargarCliente(cliente);

        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        for (int i = 0; i < datos.length; i++) {
            cargarProducto(datos[i][0].toString(), datos[i][1].toString(), 1, datos[i][3].toString(),
                    datos[i][4].toString(), datos[i][5].toString(), false, "", "", "", "", "");
            tblProductos.setValueAt(big.setMoneda(big.getMoneda(datos[i][2].toString())), tblProductos.getRowCount() - 1, 2);
            calcularTabla(tblProductos.getRowCount() - 1, false);
        }

        cargarTotales();

        saltarPasosFactura = true;
        btnGuardar1ActionPerformed(null);
    }

    public void nuevoTercero(String id) {
        txtNit.setText(id);
        ModeloContacto nodo = instancias.getSql().getDatosTercero(id);
        ID_CLIENTE_CARGADO = nodo.getIdSistema();
        txtNombre.setText(nodo.getNombre());
        txtCodigoProducto.requestFocus();
    }

    public void nuevoProducto(String id) {
        cargarProducto(id, "1", 1, "", "", "", true, "", "", "", "", "");
    }

    public void modificarCuentaCobro(String idCuenta) {
        txtCargar.setText(idCuenta);
        cargarMovimiento();

        BigDecimal porcAumento = BigDecimal.ZERO;
        try {
            porcAumento = big.getBigDecimal(datos[112].toString());
        } catch (Exception e) {
            porcAumento = BigDecimal.ONE;
        }

        for (int i = 0; i < tblProductos.getRowCount(); i++) {
            BigDecimal valorUnit = big.getMoneda(tblProductos.getValueAt(i, 2).toString());
            BigDecimal subtotal = valorUnit.multiply(porcAumento).divide(big.getBigDecimal(100), 2, RoundingMode.CEILING);

            valorUnit = subtotal.add(valorUnit);

            tblProductos.setValueAt(big.setMoneda(valorUnit), i, 2);
            calcularTabla(i, false);
        }

        saltarPasosFactura = true;
        btnActualizarActionPerformed(null);
    }

    public void desdeMensualidad(Object[][] Productos, String cliente, String diasPlazo, String placa) {

        limpiar(true, "");
        int i = 0;
        for (Object[] reg : Productos) {
            cargarProducto((String) reg[0], (String) reg[1], 1, "", "", "", false, "", "", "", "", (String) reg[3] + " hasta " + (String) reg[4]);
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
            btnGuardar1ActionPerformed(null);
        } else {
            btnGuardarActionPerformed(null);
        }
    }

    public void cargarTablaRestaurante() {

        Object[][] imagenes = instancias.getSql().getGruposVisualizarFactura();
        DefaultTableModel modelo = (DefaultTableModel) tblImagenes.getModel();

        while (tblImagenes.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        int cantGrupos = imagenes.length, cant = 0;
        while (cantGrupos - 5 > 0) {
            cant = cant + 1;
            cantGrupos = cantGrupos - 5;
        }
        if (cantGrupos > 0) {
            cant = cant + 1;
        }

        for (int i = 0; i < cant * 2; i++) {
            modelo.addRow(new Object[]{"", "", "", ""});
        }

        int contador = 0;
        ImageIcon img;
        if (imagenes.length > 0) {
            for (int i = 0; i < cant * 2; i = i + 2) {
                try {
                    for (int j = 0; j < 5; j++) {
                        ImageIcon fot = new ImageIcon(System.getProperty("user.dir") + "\\imagenes\\grupos\\IMG-" + imagenes[contador][2] + ".jpg");
                        Icon icono = new ImageIcon(fot.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
                        modelo.setValueAt(new JLabel(icono), i, j);
                        tblImagenes.setRowHeight(i, 150);
                        modelo.setValueAt(imagenes[contador][1] + ". " + imagenes[contador][0], i + 1, j);
                        tblImagenes.setRowHeight(i + 1, 35);
                        contador = contador + 1;
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public void cargarProducto(String codigo, String cantidad, int plu, String imei, String lote, String idProd, Boolean agrupar, String talla, String color,
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
                    String idPro = tblProductos.getValueAt(j, 29).toString();
                    if (!idPro.equals("")) {
                        if (idPro.equals(idProd)) {
                            if (!imei.equals("")) {
                                metodos.msgError(factura, "El imei '" + imei + "' ya esta cargado.");
                                return;
                            } else {
                                metodos.msgError(factura, "Este producto con el lote '" + lote + "' ya se cargó.");
                                return;

                            }
                        }
                    }
                }
            }

            tblProductos.setDefaultRenderer(Object.class, new IconCellRenderer());

            try {
                int num = Integer.parseInt(datos[49].toString());
                if (!rdPos.isSelected()) {
                    if (num > 0) {
                        if (tblProductos.getRowCount() >= num) {
                            if (metodos.msgPregunta(factura, "Limite de productos, ¿Desea continuar?") != 0) {
                                return;
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            if (cantidad.contains(".")) {
                cantidad = cantidad.replace(".", ",");
            }

            if (agrupar) {
                if (nodo.getCodigo() != null) {
                    if (nodo.getGrupo() != null) {
                        if (nodo.getCodigo().equals("IMP01") || nodo.getGrupo().equals("GRP-02")) {
                            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                                if (nodo.getIdSistema().equalsIgnoreCase((String) tblProductos.getValueAt(j, 32)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 12)) + "")) {
                                    tblProductos.setValueAt((big.getMoneda(tblProductos.getValueAt(j, 3).toString().replace(".", ",")).add(big.getMoneda(cantidad))).toString().replace(".", ","), j, 3);
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
                    if ((Boolean) datos[50]) {
                        if (nodo.getUsuario().equals("ADMIN")) {
                            for (int j = 0; j < tblProductos.getRowCount(); j++) {
                                if (nodo.getIdSistema().equalsIgnoreCase((String) tblProductos.getValueAt(j, 32)) && (plu + "").equals(((int) tblProductos.getValueAt(j, 12)) + "")) {
                                    tblProductos.setValueAt((big.getMoneda(tblProductos.getValueAt(j, 3).toString().replace(".", ",")).add(big.getMoneda(cantidad))).toString().replace(".", ","), j, 3);
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

                                    txtCant.setText(datos[87].toString());

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

            String tipo = "", productoEn = "";
            if (nodo.getTipoProd() != null) {
                if (nodo.getTipoProd().equals("Variable")) {
                    productoEn = "Desarrollo";
                    cantidad = "1";
                }
            }

            tipo = Enums.DetalleTipoProducto.obtenerTipoProducto(nodo.getTipoProducto());

            if (!tipo.equals("") && idProd.equals("") && !this.tipoProceso.equals("cotizacion")) {
                VistaMovimientoDetalleProducto compraDetallada = new VistaMovimientoDetalleProducto(null, true, nodo, null, "Salida", this.tipoProceso, BigDecimal.ZERO);
                compraDetallada.setLocationRelativeTo(null);
                compraDetallada.setVisible(true);
                return;

            } else if (productoEn.equals("Desarrollo") && instancias.getConfiguraciones().isRestaurante() && idProd.equals("") && !cargandoCongelada) {
                try {
                    instancias.getMenu().ocultarMenu("preparacion");
                    instancias.getPreparacion().cargarDatos(nodo.getIdSistema(), "", mesaCongelada, "", this.tipoProceso);
                    instancias.getPreparacion().setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(VistaFactura.class.getName()).log(Level.SEVERE, null, ex);
                }
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

                modeloInventario = (DefaultTableModel) tblInventario.getModel();
                BigDecimal aux = new BigDecimal("0.0"), iva, valor;
                valor = big.getBigDecimal(nodo.getL1());
                iva = big.getBigDecimal(nodo.getIva());
                iva = (iva.divide(big.getBigDecimal("100"))).add(big.getBigDecimal("1"));
                aux = valor.divide(iva, 2);
                aux = valor.subtract(aux);

                String cant = nodo.getFisicoInventario();
                cant = cant.replace(".", ",");

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
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad2()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L2";
                        break;
                    case 3:
                        cant2 = nodo.getCantidad3();
                        desc = nodo.getDescripcion3();
                        lista = nodo.getL3();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad3()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L3";
                        break;
                    case 4:
                        cant2 = nodo.getCantidad4();
                        desc = nodo.getDescripcion4();
                        lista = nodo.getL4();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad4()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L4";
                        break;
                    case 5:
                        cant2 = nodo.getCantidad5();
                        desc = nodo.getDescripcion5();
                        lista = nodo.getL5();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad5()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L5";
                        break;
                    case 6:
                        cant2 = nodo.getCantidad6();
                        desc = nodo.getDescripcion6();
                        lista = nodo.getL6();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad6()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L6";
                        break;
                    case 7:
                        cant2 = nodo.getCantidad7();
                        desc = nodo.getDescripcion7();
                        lista = nodo.getL7();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad7()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L7";
                        break;
                    case 8:
                        cant2 = nodo.getCantidad8();
                        desc = nodo.getDescripcion8();
                        lista = nodo.getL8();
                        cant = "" + big.getMoneda(cant).divide(big.getBigDecimal(nodo.getCantidad8()), 2, RoundingMode.HALF_DOWN);
                        lista1 = "L8";
                        break;
                }
                Double res = 0.0;

                try {
                    res = Double.parseDouble(cant.replace(",", ".")) - Double.parseDouble(cantidad);
                } catch (Exception e) {
                    try {
                        res = big.getBigDecimal(cant.replace(",", ".")).subtract(big.getBigDecimal(cantidad.replace(",", "."))).doubleValue();
                    } catch (Exception ex) {
                        res = Double.parseDouble(cant.replace(",", ".")) - Integer.parseInt(cantidad);
                    }
                }

                if (cant.equals(",00")) {
                    cant = "0";
                }

                if (!nodo.getManejaInventario()) {
                    modeloInventario.addRow(new Object[]{lista1, "N/A", "N/A"});
                } else {
                    modeloInventario.addRow(new Object[]{lista1, big.setNumero(big.getBigDecimal(cant.replace(",", "."))), res});
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
                    cantidad, big.setMoneda(big.getBigDecimal(lista)), "0", "0", big.setMoneda(big.getBigDecimal(nodo.getIva())).replace(this.simbolo + " ", ""),
                    this.simbolo + " 0", big.setMoneda(big.getBigDecimal(lista)),
                    nodo.getUbicacion1(), nodo.getReferencia(), plu,
                    (big.getBigDecimal(cant2).multiply(big.getMoneda(cantidad))), this.simbolo + " 0", "", "PENDIENTE", this.simbolo + " 0", datosGrupo, this.simbolo + " 0",
                    this.simbolo + " 0", cadena, new JLabel(icono), big.setMonedaExacta(big.getBigDecimal(nodo.getImpoconsumoVenta())).replace(this.simbolo + " ", ""), "", "", "",
                    detalle, lote, idProd, "Nuevo", "Sin-Permiso", nodo.getIdSistema(), big.setMoneda(big.getBigDecimal(aux)), grupo, nodo.getUnd(),
                    nodo.getManejaInventario()});
                txtCodigoProducto.setText("");

                tblProductos.scrollRectToVisible(tblProductos.getCellRect(tblProductos.getRowCount() - 1, 0, true));
                cargarTotales();

                tblProductos.setColumnSelectionInterval(0, 0);
                tblProductos.setRowSelectionInterval(modeloPro.getRowCount() - 1, modeloPro.getRowCount() - 1);

                if (!this.plu) {
                    if (cmbListaPrecio.getSelectedIndex() > 0) {
                        cmbListas.setSelectedItem(cmbListaPrecio.getSelectedItem());
                        tblInventario.setValueAt(cmbListaPrecio.getSelectedItem(), tblInventario.getRowCount() - 1, 0);
                        tblInventario.setColumnSelectionInterval(0, 0);
                        tblInventario.setRowSelectionInterval(tblInventario.getRowCount() - 1, tblInventario.getRowCount() - 1);
                        cambiarListaCliente();
                    }
                }
            }

            calcularTabla(modeloPro.getRowCount() - 1, false);
            txtCant.setText(datos[87].toString());

            if (instancias.isLector()) {
                txtCodigoProducto.requestFocus();
            } else {
                if (datos[97].toString().equals("Valor")) {
                    tblProductos.editCellAt(tblInventario.getRowCount() - 1, 2);
                    tblProductos.setColumnSelectionInterval(2, 2);
                    tblProductos.transferFocus();
                } else {
                    tblProductos.editCellAt(tblInventario.getRowCount() - 1, 3);
                    tblProductos.setColumnSelectionInterval(3, 3);
                    tblProductos.transferFocus();
                }
            }

            return;
        }

        if (codigo.equals("")) {
            try {
                int num = Integer.parseInt(datos[49].toString());
                if (!rdPos.isSelected()) {
                    if (num > 0) {
                        if (tblProductos.getRowCount() >= num) {
                            if (metodos.msgPregunta(factura, "Limite de productos, ¿Desea continuar?") != 0) {
                                return;
                            }
                        }
                    }
                }
            } catch (Exception e) {
            }

            ventanaProductos(codigo);
        } else {
            metodos.msgError(factura, "El codigo no existe");
            txtCodigoProducto.setText("");
            lbProducto.requestFocus();
        }
    }

    public void ventaDiseno(String mov, String cadena, String prod, String precio) {
        if (mov.equals("Guardar")) {

            for (int i = 0; i < tblProductos.getRowCount(); i++) {
                if (tblProductos.getValueAt(i, 32).equals(prod)) {
                    modeloPro.removeRow(i);
                    modeloInventario.removeRow(i);
                    break;
                }
            }

            String cant = datos[87].toString();
            cargarProducto(prod, cant, 1, "", "", cadena, false, "", "", "", "", "");

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

    public void cargarPrefactura(String factura, String tip) {
        limpiar(false, "");

        Object[][] mat = null;
        if (tipoProceso.equals("pedido")) {
            mat = instancias.getSql().getRegistrosPrePedidos(tip + factura);
        } else if (tipoProceso.equals("orden")) {
            mat = instancias.getSql().getRegistrosOrdenes(tip + factura);
        } else if (tipoProceso.equals("cuentaCobro")) {
            mat = instancias.getSql().getRegistrosCCobro(tip + factura);
        } else if (tipoProceso.equals("facturacion")) {
            if (tip.equals("PEDIDO-")) {
                mat = instancias.getSql().getRegistrosPrePedidos(tip + factura);
            } else if (tip.equals("OSERV-")) {
                mat = instancias.getSql().getRegistrosOrdenes(tip + factura);
            } else if (tip.equals("CCOBRO-")) {
                mat = instancias.getSql().getRegistrosCCobro(tip + factura);
            } else {
                mat = instancias.getSql().getRegistrosPrefacturas(tip + factura);
            }
        } else if (tipoProceso.equals("separe")) {
            mat = instancias.getSql().getRegistrosPlanSepare(tip + factura);
        }

        int ser = 0;
        productosMovimientos = new Object[mat.length];
        productosMovimientos1 = new Object[mat.length][3];

        cargandoCongelada = true;
        for (Object[] reg : mat) {
            int plu = Integer.parseInt(reg[13].toString());

            String idProd = "";
            if (tipoProceso.equals("pedido") || (tipoProceso.equals("facturacion") && tip.equals("PEDIDO-"))) {
                try {
                    idProd = reg[17].toString();
                } catch (Exception e) {
                }
            }

            if (plu == 1) {
                cargarProducto((String) reg[0], new Double((String) reg[3]) + "", plu, "", "", idProd, false, "", "", "", "", "");
            } else {
                cargarProducto((String) reg[0], new Double((String) reg[14]) + "", plu, "", "", idProd, false, "", "", "", "", "");
            }

            if (plu == 1) {
                tblProductos.setValueAt(new Double((String) reg[3]), ser, 3);
            } else {
                tblProductos.setValueAt(new Double((String) reg[14]), ser, 3);
            }

            tblProductos.setValueAt(mat[ser][1], ser, 1);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(mat[ser][2].toString())), ser, 2);
            tblProductos.setValueAt(mat[ser][5].toString().replace(",", "."), ser, 5);
            tblProductos.setValueAt(mat[ser][6].toString().replace(".", ","), ser, 6);
            tblProductos.setValueAt(mat[ser][12], ser, 16);

            try {
                tblProductos.setValueAt(mat[ser][16], ser, 31);
            } catch (Exception e) {
            }

            if (tipoProceso.equals("cuentaCobro") || tip.equals("CCOBRO-")) {
            } else {
                try {
                    tblProductos.setValueAt(mat[ser][15], ser, 21);
                } catch (Exception e) {
                }
            }

            if (tip.equalsIgnoreCase("OSERV-") || tip.equalsIgnoreCase("PEDIDO-")) {
                cantProductosOrden++;
                productosMovimientos[ser] = mat[ser][0].toString();
                productosMovimientos1[ser][0] = mat[ser][0].toString();
                productosMovimientos1[ser][1] = mat[ser][3].toString();
                productosMovimientos1[ser][2] = mat[ser][17].toString();
            }

            calcularTabla(ser, false);
            ser++;
        }

        cargandoCongelada = false;

        if (tipoProceso.equals("pedido")) {
            ndPedido nodo = instancias.getSql().getDatosPedido(tip + factura);
            cmbVendedor.setSelectedItem(nodo.getVendedor());
            txtNit.setText(nodo.getCliente());
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotal())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIva())));
            txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotal())));
            cargarCliente(nodo.getCliente());
            txtObservaciones.setText(nodo.getObservacion());
        } else if (tipoProceso.equals("orden")) {
            ndOServicio1 nodo = instancias.getSql().getDatosOServicio1(tip + factura);
            cmbVendedor.setSelectedItem(nodo.getVendedor());
            txtNit.setText(nodo.getCliente());
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotal())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIva())));
            txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotal())));
            cargarCliente(nodo.getCliente());
            txtObservaciones.setText(nodo.getObservacion());
        } else if (tipoProceso.equals("separe")) {
            ndPlanSepare nodo = instancias.getSql().getDatosPlanSepare(tip + factura);
            cmbVendedor.setSelectedItem(nodo.getVendedor());
            txtNit.setText(nodo.getCliente());
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotal())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIva())));
            txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotal())));
            cargarCliente(nodo.getCliente());
            txtObservaciones.setText(nodo.getObservacion());
        } else if (tipoProceso.equals("cuentaCobro")) {
            ndCongelada nodo = instancias.getSql().getDatosCuentaCobro(tip + factura);
            cmbVendedor.setSelectedItem(nodo.getVendedor());
            txtNit.setText(nodo.getCliente());
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotal())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIva())));
            txtCantIncremento.setText(mat[0][24].toString());
            txtCantFacturados.setText(mat[0][25].toString());

            if (null != mat[0][26]) {
                txtUltimaFacturaFecha.setText(mat[0][26].toString());
            }

            if (mat[0][23].toString().equals(mat[0][22].toString())) {
                chkSinEstablecer.setSelected(true);
            } else {
                chkSinEstablecer.setSelected(false);
            }

            dtHasta.setSelectedDate(metodos.haciaDate(mat[0][23].toString()));
            dtDesde.setSelectedDate(metodos.haciaDate(mat[0][22].toString()));
            cmbPeriodicidad.setSelectedItem(mat[0][21].toString());
            txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotal())));
            cargarCliente(nodo.getCliente());
            txtObservaciones.setText(nodo.getObservacion());
        } else if (tipoProceso.equals("facturacion")) {

            ndFactura nodo = instancias.getSql().getDatosFactura(tip + factura);

            if (tip.equals("PEDIDO-")) {
                ndPedido nodo1 = instancias.getSql().getDatosPedido(tip + factura);
                cmbVendedor.setSelectedItem(nodo1.getVendedor());
                txtNit.setText(nodo1.getCliente());
                txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo1.getSubtotalGeneral())));
                txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo1.getDescuentoGeneral())));
                txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo1.getIvaGeneral())));
                txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo1.getTotalGeneral())));
                cargarCliente(nodo1.getCliente());
                txtObservaciones.setText(nodo1.getObservacion());
            } else if (tip.equals("OSERV-")) {
                ndOServicio1 nodo1 = instancias.getSql().getDatosOServicio1(tip + factura);
                cmbVendedor.setSelectedItem(nodo1.getVendedor());
                txtNit.setText(nodo1.getCliente());
                txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo1.getSubtotalGeneral())));
                txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo1.getDescuentoGeneral())));
                txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo1.getIvaGeneral())));
                txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo1.getTotalGeneral())));
                cargarCliente(nodo1.getCliente());
                txtObservaciones.setText(nodo1.getObservacion());
            } else if (tip.equals("CCOBRO-")) {
                ndCongelada nodo1 = instancias.getSql().getDatosCuentaCobro(tip + factura);
                cmbVendedor.setSelectedItem(nodo1.getVendedor());
                txtNit.setText(nodo1.getCliente());
                txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo1.getSubtotalGeneral())));
                txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo1.getDescuentoGeneral())));
                txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo1.getIvaGeneral())));
                txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo1.getTotalGeneral())));
                cargarCliente(nodo1.getCliente());
                txtObservaciones.setText(nodo1.getObservacion());
            } else {
                cmbVendedor.setSelectedItem(nodo.getVendedor());
                txtNit.setText(nodo.getCliente());
                txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotalGeneral())));
                txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
                txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIvaGeneral())));
                txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotalGeneral())));
                cargarCliente(nodo.getCliente());
                txtObservaciones.setText(nodo.getObservacion());
            }
        }

        try {
            ndCxc nodoCxc = instancias.getSql().getDatosCxc(tip + factura);
            txtDiasPlazo.setText(Integer.toString(nodoCxc.getPlazo()));
        } catch (Exception e) {
            txtDiasPlazo.setText("0");
        }

        calcularDiasPlazo(null);
    }

    public void consultarMaestros() {

        try {
            datos = instancias.getSql().getDatosMaestra();

            if (tipoProceso != null) {
                if ((tipoProceso.equals("pedido") || tipoProceso.equals("facturacion")) && instancias.getConfiguraciones().isRestaurante()) {
                    if ((Boolean) datos[54]) {

                        lbOtroConsecutivo.setText("Turno");
                        txtTurno.setVisible(true);
                        lbOtroConsecutivo.setVisible(true);
                        txtTurno.setEnabled(false);

                        try {
                            txtTurno.setText(datos[55].toString());
                        } catch (Exception e) {
                            txtTurno.setText("");
                        }
                    } else {
                        lbOtroConsecutivo.setVisible(false);
                        txtTurno.setVisible(false);
                    }
                } else if (tipoProceso.equals("facturacion") && (Boolean) datos[57]) {
                    lbOtroConsecutivo.setText("Otro Consecutivo:");
                    txtTurno.setVisible(true);
                    lbOtroConsecutivo.setVisible(true);
                    txtTurno.setEnabled(true);
                } else {
                    txtTurno.setVisible(false);
                    lbOtroConsecutivo.setVisible(false);
                }
            } else {
                if (instancias.getConfiguraciones().isRestaurante()) {
                    if ((Boolean) datos[54]) {

                        lbOtroConsecutivo.setText("Turno");

                        txtTurno.setVisible(true);
                        lbOtroConsecutivo.setVisible(true);
                        txtTurno.setEnabled(false);

                        try {
                            txtTurno.setText(datos[55].toString());
                        } catch (Exception e) {
                            txtTurno.setText("");
                        }
                    } else {
                        lbOtroConsecutivo.setVisible(false);
                        txtTurno.setVisible(false);
                    }
                } else if ((Boolean) datos[57]) {
                    lbOtroConsecutivo.setText("Otro Consecutivo:");
                    txtTurno.setVisible(true);
                    lbOtroConsecutivo.setVisible(true);
                    txtTurno.setEnabled(true);
                } else {
                    txtTurno.setVisible(false);
                    lbOtroConsecutivo.setVisible(false);
                }
            }
        } catch (Exception e) {
        }
    }

    public void cargarProductos1(Object[][] productos) {
        String cantEstablecida = txtCant.getText();

        for (int i = 0; i < productos.length; i++) {
            ndProducto nodo = instancias.getSql().getDatosProducto(productos[i][0].toString(), "bdProductos");

            String codigo = productos[i][0].toString();
            String cantidad = productos[i][1].toString();
            if (cantidad.equals("0")) {
                cantidad = cantEstablecida;
            }

            this.plu = true;
            if (nodo.getUsuario().equals("ADMIN")) {
                cargarProducto(codigo, cantidad, 1, "", "", "", true, "", "", "", "", "");
            } else {
                for (int j = 0; j < Integer.parseInt(cantidad); j++) {
                    cargarProducto(codigo, "1", 1, "", "", "", true, "", "", "", "", "");
                }
            }
        }

        tblProductos.changeSelection(tblProductos.getRowCount() - 1, 0, false, false);
        tblProductos.removeEditor();
        tblInventario.removeEditor();

        if (tblProductos.editCellAt(tblProductos.getRowCount() - 1, 0)) {
            tblProductos.setColumnSelectionInterval(0, 0);
            tblProductos.transferFocus();
        }

        if (datos[97].toString().equals("Valor")) {
            tblProductos.editCellAt(tblInventario.getRowCount() - 1, 2);
            tblProductos.setColumnSelectionInterval(2, 2);
            tblProductos.transferFocus();
        } else {
            tblProductos.editCellAt(tblInventario.getRowCount() - 1, 3);
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

    public String desdeParqueadero(String cliente, String[][] productos, String diasPlazo, String placa) {
        limpiar(true, "");
        desdeParqueadero = true;

        txtNit.setText(cliente);
        cargarCliente(cliente);

        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        txtPlaca1.setText(placa);

        int a = 0;
        for (String[] producto : productos) {
            cargarProducto((String) producto[0], "1", 1, "", "", "", true, "", "", "", "", "");
            tblProductos.setValueAt(big.getMoneda(producto[1]), a, 2);
            a++;
        }

        cargarTotales();
//        for (int i = 0; i < tblProductos.getRowCount(); i++) {
//            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(tblProductos.getValueAt(i, 2))), i, 2);
//            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
//            tblProductosKeyReleased(x);
//        }

        String factura = validacionInicialFactura(false);

        return factura;
    }

    public String generarFacturaExterior(String cliente, String[][] productos, String diasPlazo, boolean devueltaSino, String lote, String mes) {
        limpiar(true, "");
        txtNit.setText(cliente);
        cargarCliente(cliente);
        txtDiasPlazo.setText(diasPlazo);
        calcularDiasPlazo(null);

        loteGeneral = lote;

        for (String[] producto : productos) {
            cargarProducto((String) producto[0], "1", 1, "", "", "", true, "", "", "", "", "");
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
        VistaMetodoPagos devuelta = new VistaMetodoPagos(null, false, big.getBigDecimal("0"), null, null, cliente, big.getBigDecimal("0"));

        if (devueltaSino) {
            devuelta = null;
        }

        cmbMes.setSelectedItem(mes);
        saltarPasosFactura = true;

//        btnGuardar1ActionPerformed(null);
        String fact = "CCOBRO-" + (String) instancias.getSql().getNumConsecutivo("CCOBRO")[0];
        btnGuardar1ActionPerformed(null);
        return fact;
    }

    public void desdePedido(String factura) {
        limpiar(false, "");
        Object[][] mat = instancias.getSql().getRegistrosPedidos2(factura);

        int i = 0;
        for (Object[] reg : mat) {

            cargarProducto((String) reg[0], new Double((String) reg[3]) + "", new Integer((String) reg[9]), "", "", "", false, "", "", "", "", "");

            tblProductos.setValueAt(big.setMonedaExacta(big.getBigDecimal(reg[2].toString())), i, 2);
            tblProductos.setValueAt(big.getBigDecimal(reg[5].toString()), i, 5);

            calcularTabla(i, false);
//            tblProductos.setColumnSelectionInterval(0, 0);
//            tblProductos.setRowSelectionInterval(i, i);
//            KeyEvent x = new KeyEvent(this, WIDTH, WIDTH, WIDTH, KeyEvent.VK_ENTER);
//            tblProductosKeyReleased(x);
            i++;
        }

        int j = tblProductos.getRowCount();

        ndPedido nodo = instancias.getSql().getDatosPedido(factura);

        txtNit.setText(nodo.getCliente());
        txtSubTotal.setText(big.setMonedaExacta(big.getBigDecimal(nodo.getSubtotal())));
        txtTotalDescuentos.setText(big.setMonedaExacta(big.getBigDecimal(nodo.getDescuentoGeneral())));
        txtTotalIva.setText(big.setMonedaExacta(big.getBigDecimal(nodo.getIva())));
        cargarCliente(nodo.getCliente());

        VistaMetodoPagos devuelta2 = new VistaMetodoPagos(null, true, null, null, null, null, null);
        facturar(devuelta2, true, "");

        instancias.getSql().cambiarEstadoPedido("REALIZADO", factura);

        calcularDiasPlazo(null);
    }

    public String generarFacturaExterior(String tipo, String factura, boolean devueltaSiNo, boolean estadoRealizado, String base) {

        limpiar(false, "");
        String desde = "";
        Object[][] mat = new Object[0][0];

        if (tipo.equals("separe")) {
            desde = "facturarSepare";
            mat = instancias.getSql().getRegistrosPlanSepare(factura);
        } else {
            mat = instancias.getSql().getRegistrosCuentaCobro1(factura);
        }

        int a = 0;
        for (Object[] reg : mat) {
            String imei = "", idProd = "";
            if (reg[15] != null) {
                imei = reg[15].toString();
            }

            if (reg[16] != null) {
                idProd = reg[16].toString();
            }

            int plu = Integer.parseInt(reg[13].toString());
            if (plu == 1) {
                cargarProducto((String) reg[0], new Double((String) reg[3]) + "", plu, imei, "", idProd, false, "", "", "", "", "");
            } else {
                cargarProducto((String) reg[0], new Double((String) reg[14]) + "", plu, imei, "", idProd, false, "", "", "", "", "");
            }

            tblProductos.setValueAt(mat[a][1], a, 1);
            tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(mat[a][2].toString())), a, 2);
            tblProductos.setValueAt(mat[a][5].toString().replace(",", "."), a, 5);
            tblProductos.setValueAt(mat[a][6].toString().replace(".", ","), a, 6);
            tblProductos.setValueAt(mat[a][12], a, 16);
            calcularTabla(a, false);

            a++;
        }

        if (tipo.equals("separe")) {
            ndPlanSepare nodo = instancias.getSql().getDatosPlanSepare(factura);
            cmbVendedor.setSelectedItem(nodo.getVendedor());
            txtNit.setText(nodo.getCliente());
            cargarCliente(nodo.getCliente());
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotalGeneral())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIvaGeneral())));
            txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotalGeneral())));
//            cmbCargar.setSelectedIndex(4);
            txtCargar.setText(factura.replace("SEPARE-", ""));
            ndSepare = nodo;
        } else {
            ndCongelada nodo = instancias.getSql().getDatosCuentaCobro(factura);
            cmbVendedor.setSelectedItem(nodo.getVendedor());
            txtNit.setText(nodo.getCliente());
            cargarCliente(nodo.getCliente());
            txtSubTotal.setText(big.setMoneda(big.getBigDecimal(nodo.getSubtotalGeneral())));
            txtTotalDescuentos.setText(big.setMoneda(big.getBigDecimal(nodo.getDescuentoGeneral())));
            txtTotalIva.setText(big.setMoneda(big.getBigDecimal(nodo.getIvaGeneral())));
            txtTotal.setText("Total: " + big.setMoneda(big.getBigDecimal(nodo.getTotalGeneral())));
            cmbMes.setSelectedItem(nodo.getPreparacion());
        }

        VistaMetodoPagos devuelta = new VistaMetodoPagos(null, false, big.getBigDecimal("0"), null, null, null, big.getBigDecimal("0"));
        if (devueltaSiNo) {
            devuelta = null;
        }

        saltarPasosFactura = true;
        return facturar(devuelta, true, desde);
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
        nodoOrdenServicio = null;
        btnActualizar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnGuardar1.setEnabled(true);
        DefaultTableModel x = (DefaultTableModel) tblInventario.getModel();
        int i, j = tblProductos.getRowCount();

        for (i = 0; i < j; i++) {
            x.removeRow(0);
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
        cargarProducto(producto[0][0], producto[0][1], 1, "", "", "", true, "", "", "", "", "");
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

    /*public void cargarDescuento(int fila, BigDecimal descPorc, BigDecimal totalDesc, String tipo) {
        
     System.out.println("entro al descuento: " + descPorc);
     if (descPorc == null) {
     tblProductos.setValueAt(totalDesc, fila, 6);
     } else {

     if (fila == 10000) {
     for (int i = 0; i < tblProductos.getRowCount(); i++) {
     tblProductos.setValueAt(descPorc, i, 5);
     calcularTabla(i, false);
     }
     } else {
     tblProductos.setValueAt(descPorc, fila, 5);
     }
     }

     if (fila == 10000) {
     for (int i = 0; i < tblProductos.getRowCount(); i++) {
     tblProductos.setValueAt(tipo, i, 31);
     calcularTabla(i, false);
     }
     } else {
     tblProductos.setValueAt(tipo, fila, 31);
     }
     }*/
    private void agregarRegistrosComandas(int filaUtil, String turnoUtil, String baseUtil, String facturaUtil, String pedidoUtil, String congeladaUtil) {

        String cadena = "";
        try {
            cadena = tblProductos.getValueAt(filaUtil, 21).toString();
        } catch (Exception e) {
        }

        if (!cadena.equals("")) {
            String opciones1 = "Adiciones: ", ingredientes1 = "Sin: ", aderezos1 = "Aderezos: ";
            String opciones2[], aderezos2[];

            String observaciones = "";
            try {
                observaciones = cadena.split("; ")[2];
            } catch (Exception e) {
            }

            String opciones = cadena.split("; ")[1];
            String aderezos = cadena.split("; ")[0];

            //INGRESAMOS LOS PRODUCTOS ESCOJIDOS EN LOS PRODUCTOS CON CAMBIO
            if (!opciones.equals("")) {
                for (OpcionPreparacion opcion : ParserPreparacion.opcionesDeSegmento(opciones)) {

                    String principal = opcion.getPrincipal();
                    Boolean esAdicion = opcion.esAdicion();

                    if (esAdicion) {
                        String codigo = opcion.getCodigo();
                        String cant = opcion.getCantidad();
                        String estadoProducto = opcion.getEstado();

                        if (principal.equals("") || principal.equals(" ")) {
                            if (estadoProducto.equals(" false")) {
                                ndProducto nodoProd = instancias.getSql().getDatosProducto(opcion.getCodigo(), baseUtil);
                                ingredientes1 = ingredientes1 + nodoProd.getDescripcion() + ", ";
                            }
                        } else {
                            if (!principal.equals(codigo)) {
                                if (estadoProducto.equals(" true")) {
                                    ndProducto nodoProd = instancias.getSql().getDatosProducto(opcion.getCodigo(), baseUtil);
                                    if (nodoProd.getGrupo() != null) {
                                        if (nodoProd.getGrupo().equals("GRP-02")) {
                                            if (cant.substring(cant.length() - 1, cant.length()).equals("0")) {
                                                opciones1 = opciones1 + cant.substring(0, cant.length() - 2) + " " + nodoProd.getDescripcion() + ", ";
                                            } else {
                                                opciones1 = opciones1 + cant + " " + nodoProd.getDescripcion() + ", ";
                                            }
                                        } else {
                                            opciones1 = opciones1 + nodoProd.getDescripcion() + ", ";
                                        }
                                    } else {
                                        opciones1 = opciones1 + nodoProd.getDescripcion() + ", ";
                                    }
                                }
                            }
                        }
                    }
                }

                if (!opciones1.equals("Adiciones: ")) {
                    opciones1 = opciones1.substring(0, opciones1.length() - 2);
                }

                if (!ingredientes1.equals("Sin: ")) {
                    ingredientes1 = ingredientes1.substring(0, ingredientes1.length() - 2);
                }
            }

            if (!aderezos.equals("")) {
                aderezos2 = aderezos.split(", ");
                for (int k = 0; k < aderezos2.length; k++) {
                    ndProducto nodoProd = instancias.getSql().getDatosProducto(aderezos2[k], baseUtil);
                    aderezos1 = aderezos1 + nodoProd.getDescripcion() + ", ";
                }
                aderezos1 = aderezos1.substring(0, aderezos1.length() - 2);
            }

            if (!instancias.getSql().agregarComanda(congeladaUtil, facturaUtil, tblProductos.getValueAt(filaUtil, 32).toString(), tblProductos.getValueAt(filaUtil, 1).toString(),
                    opciones1, ingredientes1, "", aderezos1, tblProductos.getValueAt(filaUtil, 3).toString(), observaciones, turnoUtil, pedidoUtil, "PLATO-" + 1)) {
                metodos.msgError(null, "Error al guardar la comanda.");
            }
        } else {
            if (!instancias.getSql().agregarComanda(congeladaUtil, facturaUtil, tblProductos.getValueAt(filaUtil, 32).toString(), tblProductos.getValueAt(filaUtil, 1).toString(),
                    "", "", "", "", tblProductos.getValueAt(filaUtil, 3).toString(), "", turnoUtil, pedidoUtil, "PLATO-" + filaUtil)) {
                metodos.msgError(null, "Error al guardar la comanda.");
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
                            String cant = opcion.getCantidad();
                            String estado = opcion.getEstado();
                            BigDecimal cantidadTotal = cantidadProducto.multiply(Utilidades.convertirBigDecimal(cant));

                            if (estado.equals(" true")) {
                                ndProducto nodo1 = instancias.getSql().getDatosProducto(codigo, baseUtilizada);

                                if (nodo1.getGrupo() != null) {
                                    if (nodo1.getGrupo().equals("GRP-02")) {
                                        cargarProducto(codigo, Utilidades.formatearCantidad(cantidadTotal), 1, "", "", "", false, "", "", "", "", "");
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

        BigDecimal cantidad = BigDecimal.ONE;
        try {
            cantidad = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 3)).replace(".", ","));
        } catch (Exception e) {
            Logger.getLogger("Error al obtener la cantidad y se deja por default en uno");
        }

        BigDecimal valorDescuento = BigDecimal.ZERO;
        try {
            valorDescuento = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 6)));
        } catch (Exception e) {
            Logger.getLogger("Error al obtener el descuento y se deja por default en cero");
        }
        tblProductos.setValueAt(big.setMonedaExacta(valorDescuento), fila, 6);

        BigDecimal valorUnitario = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 2)));
        BigDecimal subtotal = valorUnitario.multiply(cantidad);
        BigDecimal copago = big.getMoneda(String.valueOf(tblProductos.getValueAt(fila, 17)));

//        BigDecimal impoconsumo, iva, totalImpoconsumo = BigDecimal.ZERO, totalIva = BigDecimal.ZERO, subtotalGeneral = BigDecimal.ZERO;
        BigDecimal descuento, total, porcentaje2, compra, utilidadMax, utilidadMin;
        Boolean entro = false;

        try {
            if (!(Boolean) datos[61]) {
                String lista = tblInventario.getValueAt(fila, 0).toString();
                if (lista.equals("L1")) {
                    if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL1())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                    }
                } else if (lista.equals("L2")) {
                    if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL2())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), fila, 2);
                    }
                } else if (lista.equals("L3")) {
                    if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL3())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), fila, 2);
                    }
                } else if (lista.equals("L4")) {
                    if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL4())) != 0) {
                        tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), fila, 2);
                    }
                }
            }

            tblProductos.setValueAt(big.setMoneda(valorUnitario), fila, 2);
        } catch (Exception e) {
            String lista = tblInventario.getValueAt(fila, 0).toString();
            if (lista.equals("L1")) {
                if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL1())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL1())), fila, 2);
                }
            } else if (lista.equals("L2")) {
                if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL2())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL2())), fila, 2);
                }
            } else if (lista.equals("L3")) {
                if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL3())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL3())), fila, 2);
                }
            } else if (lista.equals("L4")) {
                if (valorUnitario.compareTo(big.getBigDecimal(nodo.getL4())) != 0) {
                    tblProductos.setValueAt(big.setMoneda(big.getBigDecimal(nodo.getL4())), fila, 2);
                }
            }
        }

        String tipo = "", productoEn = "";
        try {
            if (nodo.getTipoProd().equals("Variable") || nodo.getTipoProd().equals("Fijas")) {
                productoEn = "Desarrollo";
            }
        } catch (Exception e) {
        }

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

        if (!this.tipoProceso.equals("cotizacion")) {
            if (!productoEn.equals("")) {
                cantidad = BigDecimal.ONE;
            }

//            if (!tipo.equals("")) {
//                cantidad = BigDecimal.ONE;
//            }
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

                Boolean solicitudPermisos = Boolean.parseBoolean(datos[96].toString());
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
            alertas.bigAlert("No se pudo consultar el último ponderado del producto");
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
            alertas.alertFail("Revisar la ciudad y departamento del cliente");
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
        modeloFacturacionElectronica.setResponsabilidadesFiscales(obtenerResponsabilidadesFiscales());

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

        List<Integer> ivas = new ArrayList<Integer>();
        List<Integer> impoconsumos = new ArrayList<Integer>();
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

    private String obtenerResponsabilidadesFiscales() {
        String[] responsabilidades = datos[113].toString().split(", ");
        String responsabilidadesFiscales = "";
        for (int i = 0; i < responsabilidades.length; i++) {
            responsabilidadesFiscales = responsabilidadesFiscales + responsabilidades[i].split(" /")[0] + ";";
        }

        if (!responsabilidadesFiscales.equals("")) {
            responsabilidadesFiscales = responsabilidadesFiscales.substring(0, responsabilidadesFiscales.length() - 1);
        }

        return responsabilidadesFiscales;
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

    private void agregamosRegistrosMediosDePago(String factura2) {
        //OBTENEMOS LA HORA EN LA QUE SE REALIZA LA FACTURA
        String hora = metodosGenerales.hora();
        //AGREGAR FORMAS DE PAGOS
        if (instancias.getEfectivoDevuelta().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "10", "EFECTIVO", instancias.getEfectivoDevuelta(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getChequeDevuelta().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "20", "CHEQUE", instancias.getChequeDevuelta(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getTarjetaDevuelta().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "49", "TARJETA_DEBITO", instancias.getTarjetaDevuelta(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }

        if (instancias.getTarjetaCredito().compareTo(BigDecimal.ZERO) > 0) {
            String idPago = "PAGO-" + instancias.getSql().getNumConsecutivoFact1("FORMAPAGO")[0].toString();
            instancias.getSql().agregarFormaPago(idPago, factura2, "48", "TARJETA_CREDITO", instancias.getTarjetaCredito(), "",
                    metodos.fechaConsulta(metodosGenerales.fecha()), hora, instancias.getUsuario());
            instancias.getSql().aumentarConsecutivo("FORMAPAGO", Integer.parseInt((String) instancias.getSql().getNumConsecutivo("FORMAPAGO")[0]) + 1);
        }
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

    /**
     * Recorre todos los productos de la tabla y valida si hay inventario
     * suficiente para procesar el documento. Usa ServicioDiscosteo para
     * explosionar los productos armados/preparados en sus insumos. Delega en
     * ServicioValidacionFactura.
     */
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

    /**
     * Lee de tblProductos los datos que la vista aporta a la validación de
     * inventario.
     */
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
        boolean vieneDesdeUnPedido = (ndPedido != null && ndPedido.getIdFactura() != null);
        boolean vieneDesdeUnSepare = (ndSepare != null && ndSepare.getIdFactura() != null);
        boolean vieneDesdeUnOrdenServicio = nodoOrdenServicio != null;
        return new InformacionAdicional(vieneDesdeUnPedido, vieneDesdeUnSepare, vieneDesdeUnOrdenServicio);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBusProd;
    private javax.swing.JButton btnBuscTerceros;
    private javax.swing.JButton btnCambiarMesa;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnInformacionCliente;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnNuevaParte;
    private javax.swing.JButton btnNuevaParte1;
    private javax.swing.JButton btnPendientes;
    private javax.swing.JButton btnReImprimir;
    private javax.swing.JLabel btnVolver;
    private javax.swing.JLabel btnVolver1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkReteIva;
    private javax.swing.JCheckBox chkSinEstablecer;
    private javax.swing.JCheckBox chkSisteCredito;
    private javax.swing.JComboBox cmbFuncionamiento;
    private javax.swing.JComboBox cmbListaPrecio;
    private javax.swing.JComboBox cmbListas;
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JComboBox cmbPeriodicidad;
    private javax.swing.JComboBox cmbRtf;
    private javax.swing.JComboBox cmbSeñal;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
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
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlGarantia;
    private javax.swing.JPanel pnlOcultar;
    private javax.swing.JPanel pnlVisor;
    private javax.swing.JMenuItem popBorrar;
    private javax.swing.JRadioButton rdCarta;
    private javax.swing.JRadioButton rdMediaCarta;
    private javax.swing.JRadioButton rdPos;
    private javax.swing.JRadioButton rdTipoCopago;
    private javax.swing.JRadioButton rdTipoNormal;
    private javax.swing.JScrollPane scrInventario;
    private javax.swing.JScrollPane scrProductos1;
    private javax.swing.JTabbedPane tapControl;
    private javax.swing.JTable tblArticulos;
    private javax.swing.JTable tblComprobantes;
    private javax.swing.JTable tblCuotas;
    private javax.swing.JTable tblImagenes;
    private javax.swing.JTable tblInventario;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtCant;
    private javax.swing.JTextField txtCantFacturados;
    private javax.swing.JTextField txtCantIncremento;
    private javax.swing.JLabel txtCantProductos;
    private javax.swing.JLabel txtCantUnidades;
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
    private javax.swing.JTextField txtGarantiaFuncionamiento;
    private javax.swing.JTextField txtGarantiaSeñal;
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
