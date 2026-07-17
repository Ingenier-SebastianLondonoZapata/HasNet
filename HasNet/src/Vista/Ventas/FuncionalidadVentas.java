package Vista.Ventas;

import Controlador.Alertas.ControladorAlertas;
import Enums.EstadosTipoDocumento;
import Enums.TipoDocumento;
import Modelo.Inventario.DetalleProducto;
import Modelo.Inventario.InformacionAdicional;
import Modelo.Inventario.MovimientoInventario;
import Modelo.Maestra.ModeloResolucion;
import Utilidades.DatosMaestra;
import clases.Instancias;
import clases.big;
import clases.metodosGenerales;
import clases.productos.ndProducto;
import dao.Configuraciones.DaoResoluciones;
import dao.Ventas.DaoCotizacion;
import dao.Ventas.DaoFactura;
import dao.Ventas.DaoOrdenServicio;
import dao.Ventas.DaoPedido;
import inventario.servicio.ServicioInventario;
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
    private final metodosGenerales metodos = new metodosGenerales();

    public boolean aumentarConsecutivoResolucion(int idResolucion) {
        if (!daoResoluciones.aumentarConsecutivoResolucion(idResolucion)) {
            ControladorAlertas.alertFail("Error al aumentar consecutivo de factura");
            return false;
        }
        return true;
    }

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
            String tablaUtilizada, String usuario, List<MovimientoInventario> productos,
            List<DetalleProducto> detalles, InformacionAdicional informacionAdicional) {

        try {
            ServicioInventario servicioInventario = new ServicioInventario(
                    productos, detalles, tipoMovimiento, idMovimiento,
                    tablaUtilizada, usuario, informacionAdicional);
            servicioInventario.procesarMovimiento();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionalidadVentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int incrementarTurno(Instancias instancias) {
        int nuevoTurno = Integer.parseInt(instancias.getSql().getTurno().trim()) + 1;
        if (nuevoTurno > 100) {
            nuevoTurno = 1;
        }
        instancias.getSql().aumentarTurno(String.valueOf(nuevoTurno));
        instancias.getMaestra().setTurno(String.valueOf(nuevoTurno));
        instancias.getMaestra().actualizarTurno();
        return nuevoTurno;
    }

    public String validarYObtenerFactura(int idResolucion) {
        ModeloResolucion resolucion = daoResoluciones.obtenerInformacionResolucion(idResolucion);
        if (resolucion == null) {
            ControladorAlertas.alert("No se pudo obtener los datos de la resolución.");
            return "";
        }

        String prefijo = (resolucion.getPrefijo() == null) ? "" : resolucion.getPrefijo();
        int segundosEsperando = 0;

        String facturaReal = "FACT-" + prefijo + resolucion.getConsecutivo();
        Object[][] datosFactura = Instancias.getInstancias().getSql().getVerificadorFactura(facturaReal);

        while (datosFactura.length > 0) {
            // Refrescar la resolución por si alguien más aumentó el consecutivo
            resolucion = daoResoluciones.obtenerInformacionResolucion(idResolucion);
            facturaReal = "FACT-" + prefijo + resolucion.getConsecutivo();
            datosFactura = Instancias.getInstancias().getSql().getVerificadorFactura(facturaReal);

            try {
                Thread.sleep(1000);
                segundosEsperando++;

                if (segundosEsperando > 2) {
                    if (!daoResoluciones.aumentarConsecutivoResolucion(idResolucion)) {
                        ControladorAlertas.alertFail("Error al aumentar consecutivo de la resolución");
                    }
                    segundosEsperando = 0;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                ControladorAlertas.alertFail("Error al aumentar consecutivo de la resolución");
                break;
            }
        }

        return "FACT-" + prefijo + resolucion.getConsecutivo();
    }

    public String obtenerNumeroCongelada(Instancias instancias, String tipoProceso) {
        String numeroCongelada = "";
        try {
            String titulo = instancias.getTitulo();
            if (titulo != null && !titulo.isEmpty()) {
                numeroCongelada = instancias.getConfiguraciones().isRestaurante()
                        ? titulo
                        : titulo.replace(": ", "-");
            } else if (TipoDocumento.FACTURACION.getValor().equals(tipoProceso)) {
                numeroCongelada = "SIN-CONSECUTIVO";
            }
        } catch (Exception e) {
            System.out.println("Falló al obtener el titulo de la mesa");
            if (TipoDocumento.FACTURACION.getValor().equals(tipoProceso)) {
                numeroCongelada = "SIN-CONSECUTIVO";
            }
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

    public void pausarImpresora() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    public String condicionFactura(Instancias instancias, String factura2) {
        return metodos.sentenciaImpresionFactura(
                tipoSentenciaFactura(instancias),
                " WHERE bdFactura.factura = '" + factura2 + "' ");
    }

    public void imprimirPasadaFactura(Instancias instancias, String factura2, String tipo,
            String observaciones, String legal, String pie, String titulo, String impresora,
            String impoconsumo, String retenciones, String etiquetaCopia,
            boolean porGrupo, Object[] grupos, int cantidadGrupos) {

        String infoEmpresa = infoEmpresaConResolucion(instancias);

        if (porGrupo) {
            for (int i = 0; i < cantidadGrupos; i++) {
                String grupo = grupos[i].toString();
                String impresoraGrupo = impresoraDeGrupo(instancias, grupo);
                String condicion = condicionFacturaPorGrupo(instancias, factura2, grupo);
                instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, etiquetaCopia, pie,
                        tipo, factura2, !DatosMaestra.isPrevisualizarFactura(), titulo,
                        impresoraGrupo, impoconsumo, retenciones, condicion, false);
                pausarImpresora();
            }
        } else {
            String condicion = condicionFactura(instancias, factura2);
            instancias.getReporte().ver_Factura(observaciones, infoEmpresa, legal, etiquetaCopia, pie,
                    tipo, factura2, !DatosMaestra.isPrevisualizarFactura(), titulo,
                    impresora, impoconsumo, retenciones, condicion, false);
        }
    }

    private String tipoSentenciaFactura(Instancias instancias) {
        return instancias.getConfiguraciones().isRestaurante() ? "agrupada" : "";
    }

    private String condicionFacturaPorGrupo(Instancias instancias, String factura2, String grupo) {
        String where = grupo.isEmpty()
                ? " WHERE bdFactura.factura = '" + factura2 + "' AND Grupo IS NULL "
                : " WHERE bdFactura.factura = '" + factura2 + "' AND Grupo = '" + grupo + "' ";
        return metodos.sentenciaImpresionFactura(tipoSentenciaFactura(instancias), where);
    }

    private String impresoraDeGrupo(Instancias instancias, String grupo) {
        if (grupo.isEmpty()) {
            return "Sin_impresora";
        }
        Object[] datosGrupo = instancias.getSql().getDatosGrupo(grupo);
        return datosGrupo[5].toString();
    }

    private String infoEmpresaConResolucion(Instancias instancias) {
        return metodosGenerales.convertToMultiline(
                instancias.getInformacionEmpresaCompleto() + "\n" + instancias.getResolucion());
    }

    public Object[] construirDatosReporteFactura(Instancias instancias, boolean esMediaCarta, boolean esCarta, boolean esPos) {
        String nombreReporte = obtenerPrefijoReporte(esMediaCarta, esCarta)
                + instancias.getRegimen()
                + obtenerSufijoReporte(instancias.getConfiguraciones().getTipoImpresion());

        String impresora = obtenerImpresora(esMediaCarta, esCarta);
        String titulo = obtenerTitulo(instancias);

        return new Object[]{nombreReporte, impresora, titulo};
    }

    private String obtenerTitulo(Instancias instancias) {
        if (instancias.getConfiguraciones().isRestaurante()) {
            return DatosMaestra.isTurnoActivo() ? "Turno" : "";
        }

        return DatosMaestra.isConsecutivoAdicional() ? "Num Fact2" : "";
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

    private String obtenerSufijoReporte(String tipoImpresion) {
        if ("Sin-Codigo".equals(tipoImpresion)) {
            return "1";
        }

        if ("Imei".equals(tipoImpresion)) {
            return "Imei";
        }

        return "";
    }

    public BigDecimal revisarPrecioProducto(String listaPrecio, BigDecimal valorUnitario, ndProducto datosProducto) {
        return obtenerPrecioLista(listaPrecio, datosProducto);
    }

    public BigDecimal obtenerPrecioLista(String lista, ndProducto datosProducto) {
        switch (lista) {
            case "L1":
                return big.getBigDecimal(datosProducto.getL1());
            case "L2":
                return big.getBigDecimal(datosProducto.getL2());
            case "L3":
                return big.getBigDecimal(datosProducto.getL3());
            case "L4":
                return big.getBigDecimal(datosProducto.getL4());
            case "L5":
                return big.getBigDecimal(datosProducto.getL5());
            case "L6":
                return big.getBigDecimal(datosProducto.getL6());
            case "L7":
                return big.getBigDecimal(datosProducto.getL7());
            case "L8":
                return big.getBigDecimal(datosProducto.getL8());
            default:
                return BigDecimal.ZERO;
        }
    }

    public ConversorDocumentoAFactura inicializarConversorDocumentos(final Instancias instancias, final DaoOrdenServicio daoOrdenServicio,
            final DaoPedido daoPedido, final DaoCotizacion daoCotizacion) {
        return new ConversorDocumentoAFactura()
                .registrar(TipoDocumento.MESA.getValor(), TipoDocumento.ANULAR_MESA,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String tituloDocumento) {
                                instancias.getSql().eliminarComanda(idDocumento, "factura");
                                instancias.getSql().eliminarMesa(idDocumento);
                                instancias.getSql().cambiarEstadoMesa(tituloDocumento, EstadosTipoDocumento.DISPONIBLE.getNombre());
                            }
                        })
                .registrar(TipoDocumento.PEDIDO.getValor(), TipoDocumento.ANULAR_PEDIDO,
                        new ConversorDocumentoAFactura.ActualizadorDocumento() {
                            @Override
                            public void actualizar(String idDocumento, String tituloDocumento) {
                                instancias.getSql().eliminarComanda(idDocumento, "pedido");
                                daoPedido.modificarEstadoPedido(EstadosTipoDocumento.FACTURADA.getNombre(), idDocumento);
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
