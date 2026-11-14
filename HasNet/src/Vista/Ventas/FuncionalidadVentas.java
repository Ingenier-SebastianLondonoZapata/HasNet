package Vista.Ventas;

import Controlador.Alertas.ControladorAlertas;
import Enums.EstadosTipoDocumento;
import Enums.TipoDocumento;
import Impresiones.ImpresionesFacturas.DatosImpresionFactura;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.DatosMaestra;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import Modelo.Productos.ModeloProducto;
import Dao.Configuraciones.DaoResoluciones;
import Dao.Ventas.DaoCotizacion;
import Dao.Ventas.DaoFactura;
import Dao.Ventas.DaoOrdenServicio;
import Dao.Ventas.DaoPedido;
import Dao.Ventas.DaoPlanSepare;
import Procesos.Inventario.Servicio.ServicioInventario;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FuncionalidadVentas {

    private final DaoResoluciones daoResoluciones = new DaoResoluciones();
    private final Dao.Documentos.DaoDocumentos daoDocumentos = new Dao.Documentos.DaoDocumentos();
    private final Dao.Configuraciones.DaoMaestra daoMaestra = new Dao.Configuraciones.DaoMaestra();
    private final metodosGenerales metodos = new metodosGenerales();


    public void reiniciarPagos(Instancias instancias) {
        instancias.setEfectivoDevuelta(BigDecimal.ZERO);
        instancias.setNcDevuelta(BigDecimal.ZERO);
        instancias.setTarjetaDevuelta(BigDecimal.ZERO);
        instancias.setTarjetaCredito(BigDecimal.ZERO);
        instancias.setChequeDevuelta(BigDecimal.ZERO);
        instancias.setDevuelta(BigDecimal.ZERO);
        instancias.setPorcPropina("0");
        instancias.setPropina(BigDecimal.ZERO);
        instancias.setTotalPropina(BigDecimal.ZERO);
    }

    public void procesarMovimientoInventario(TipoDocumento tipoMovimiento, String idMovimiento,
            String idBodega, String usuario, List<MovimientoInventario> productos,
            List<DetalleProducto> detalles, InformacionAdicional informacionAdicional) {

        try {
            ServicioInventario servicioInventario = new ServicioInventario(
                    productos, detalles, tipoMovimiento, idMovimiento,
                    idBodega, usuario, informacionAdicional);
            servicioInventario.procesarMovimiento();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionalidadVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int incrementarTurno(Instancias instancias) {
        int nuevoTurno = daoMaestra.obtenerTurnoConsecutivo() + 1;
        if (nuevoTurno > 100) {
            nuevoTurno = 1;
        }
        daoMaestra.actualizarTurnoConsecutivo(nuevoTurno);
        instancias.getMaestra().setTurno(String.valueOf(nuevoTurno));
        instancias.getMaestra().actualizarTurno();
        return nuevoTurno;
    }


    public String obtenerNumeroCongelada(Instancias instancias, String tipoProceso, Instancias instancias1) {
        String numeroCongelada = "";

        String titulo = instancias1.getTitulo();
        if (titulo != null && !titulo.isEmpty()) {
            numeroCongelada = instancias.getConfiguraciones().isRestaurante()
                    ? titulo
                    : titulo.replace(": ", "-");
        } else if (TipoDocumento.FACTURACION.getValor().equals(tipoProceso)) {
            numeroCongelada = "SIN-CONSECUTIVO";
        }

        return numeroCongelada;
    }

    public String buscarCongeladaDisponible(Instancias instancias) {
        Object[][] congeladas = instancias.getSql().getDatosCongelada1();
        for (int i = 1; i <= 20; i++) {
            String slot = "CONGELADA-" + i;
            boolean ocupada = false;
            for (Object[] fila : congeladas) {
                if (fila[2] != null && slot.equals(fila[2].toString())) {
                    ocupada = true;
                    break;
                }
            }
            if (!ocupada) {
                return slot;
            }
        }
        return null;
    }

    public void agregamosRegistrosMediosDePago(Instancias instancias, String factura) {
        String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        agregarFormaPagoSiAplica(instancias, factura, "10", "EFECTIVO", instancias.getEfectivoDevuelta(), fecha, hora);
        agregarFormaPagoSiAplica(instancias, factura, "20", "CHEQUE", instancias.getChequeDevuelta(), fecha, hora);
        agregarFormaPagoSiAplica(instancias, factura, "49", "TARJETA_DEBITO", instancias.getTarjetaDevuelta(), fecha, hora);
        agregarFormaPagoSiAplica(instancias, factura, "48", "TARJETA_CREDITO", instancias.getTarjetaCredito(), fecha, hora);
    }

    private void agregarFormaPagoSiAplica(Instancias instancias, String factura, String codigo, String descripcion,
            BigDecimal valor, String fecha, String hora) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }

        DaoFactura daoFactura = new DaoFactura();
        int consecutivo = Integer.valueOf(daoFactura.getNextConsecutivo("FORMAPAGO"));
        String idPago = "PAGO-" + consecutivo;

        instancias.getSql().agregarFormaPago(idPago, factura, codigo, descripcion, valor, "", fecha, hora, instancias.getUsuario());
        instancias.getSql().aumentarConsecutivo("FORMAPAGO", consecutivo + 1);
    }

    public DatosImpresionFactura construirDatosImpresionFactura(Instancias instancias, boolean esMediaCarta, boolean esCarta) {
        String nombreReporte = obtenerPrefijoReporte(esMediaCarta, esCarta) + instancias.getRegimen();

        return new DatosImpresionFactura()
                .setNombreReporte(nombreReporte)
                .setImpresora(obtenerImpresora(esMediaCarta, esCarta))
                .setTitulo(obtenerTitulo(instancias))
                .setInformacionEmpresa(metodosGenerales.convertToMultiline(
                        instancias.getInformacionEmpresaCompleto() + "\n" + instancias.getResolucion()));
    }

    private String obtenerTitulo(Instancias instancias) {
        if (instancias.getConfiguraciones().isRestaurante()) {
            return DatosMaestra.isTurnoActivo() ? "Turno" : "";
        }

        return DatosMaestra.isConsecutivoAdicional() ? "Otro consecutivo" : "";
    }

    private String obtenerPrefijoReporte(boolean esMediaCarta, boolean esCarta) {
        if (esMediaCarta) {
            return "factura";
        }

        if (esCarta) {
            return "facturaCompleta";
        }

        return "pos";
    }

    private String obtenerImpresora(boolean esMediaCarta, boolean esCarta) {
        if (esMediaCarta) {
            return DatosMaestra.getImpresoraMediaCarta();
        }

        if (esCarta) {
            return DatosMaestra.getImpresoraCarta();
        }

        return DatosMaestra.getImpresoraPos();
    }

    public BigDecimal revisarPrecioProducto(String listaPrecio, BigDecimal valorUnitario, ModeloProducto datosProducto) {
        return obtenerPrecioLista(listaPrecio, datosProducto);
    }

    public BigDecimal obtenerPrecioLista(String lista, ModeloProducto datosProducto) {
        if (lista == null || !lista.trim().matches("(?i)L\\d+")) {
            return BigDecimal.ZERO;
        }

        return datosProducto.getPrecioLista(lista.trim());
    }

    public ConversorDocumentoAFactura inicializarConversorDocumentos(final Instancias instancias, final DaoOrdenServicio daoOrdenServicio,
            final DaoPedido daoPedido, final DaoCotizacion daoCotizacion, final DaoPlanSepare daoPlanSepare) {
        return new ConversorDocumentoAFactura()
                .registrar(TipoDocumento.PLAN_SEPARE.getValor(), TipoDocumento.ANULAR_PLAN_SEPARE,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String facturaGenerada) {
                                daoPlanSepare.modificarEstadoPlanSepare(EstadosTipoDocumento.FACTURADA.getNombre(), facturaGenerada, idDocumento);
                            }
                        })
                .registrar(TipoDocumento.MESA.getValor(), TipoDocumento.ANULAR_MESA,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String tituloDocumento) {
                                instancias.getSql().eliminarComanda(idDocumento, "factura");
                                daoDocumentos.eliminarPorFactura(TipoDocumento.MESA, idDocumento);
                                instancias.getSql().cambiarEstadoMesa(tituloDocumento, EstadosTipoDocumento.DISPONIBLE.getNombre());
                            }
                        })
                .registrar(TipoDocumento.PEDIDO.getValor(), TipoDocumento.ANULAR_PEDIDO,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String facturaGenerada) {
                                instancias.getSql().eliminarComanda(idDocumento, "factura");
                                daoPedido.modificarEstadoPedido(EstadosTipoDocumento.FACTURADA.getNombre(), facturaGenerada, idDocumento);
                            }
                        })
                .registrar(TipoDocumento.ORDER_SERVICIO.getValor(), TipoDocumento.ANULAR_ORDER_SERVICIO,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String tituloDocumento) {
                                daoOrdenServicio.modificarEstadoOrden(EstadosTipoDocumento.FACTURADA.getNombre(), idDocumento);
                            }
                        })
                .registrar(TipoDocumento.COTIZACION.getValor(), null,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String tituloDocumento) {
                                daoCotizacion.modificarEstadoCotizacion(EstadosTipoDocumento.FACTURADA.getNombre(), idDocumento);
                            }
                        })
                .registrar(TipoDocumento.CUENTA_COBRO.getValor(), null,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String tituloDocumento) {
                                //instancias.getSql().modificarEstadoCxcFactura(idDocumento, "");
                            }
                        });
    }

}
