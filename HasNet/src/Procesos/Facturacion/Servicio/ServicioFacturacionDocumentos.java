package Procesos.Facturacion.Servicio;

import Vista.Ventas.VistaFactura;

import Enums.EstadosTipoDocumento;
import Enums.TipoDocumento;
import Modelo.Maestra.ModeloResolucion;
import Modelo.Ventas.DocumentoMovimiento;
import Modelo.FacturacionMasiva.ResultadoFacturacion;
import Utilidades.Constantes;
import clases.Cartera.ndCxc;
import clases.Instancias;
import dao.Configuraciones.DaoResoluciones;
import dao.Ventas.DaoFactura;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServicioFacturacionDocumentos {

    private static final String CXC_CANCELADA = "CANCELADA";
    private static final String ESTADO_ORDEN_REALIZADA = "REALIZADO";

    private static final Set<String> DOCUMENTOS_EN_PROCESO = Collections.synchronizedSet(new HashSet<String>());

    private final Instancias instancias;
    private final DaoFactura daoFactura = new DaoFactura();
    private final DaoResoluciones daoResoluciones = new DaoResoluciones();

    public ServicioFacturacionDocumentos(Instancias instancias) {
        this.instancias = instancias;
    }

    public List<ModeloResolucion> obtenerComprobantes() {
        List<ModeloResolucion> comprobantes = daoResoluciones.obtenerResoluciones(TipoDocumento.FACTURACION.getValor());
        return comprobantes != null ? comprobantes : new ArrayList<ModeloResolucion>();
    }

    public boolean esTipoSoportado(String tipoDocumento) {
        return obtenerVistaFactura(tipoDocumento) != null;
    }

    public String validarComprobanteParaClientes(int indexComprobante, List<String> nitsClientes) {
        List<ModeloResolucion> comprobantes = obtenerComprobantes();
        if (indexComprobante < 0 || indexComprobante >= comprobantes.size()) {
            return "El comprobante seleccionado no existe.";
        }

        String tipoResolucion = comprobantes.get(indexComprobante).getTipoResolucion();
        if (Constantes.esFacturacionElectronica(tipoResolucion) && contieneClientePorDefecto(nitsClientes)) {
            return "No es posible generar facturación electrónica con el cliente por defecto ("
                    + Constantes.CLIENTE_POR_DEFECTO + ").\n"
                    + "La facturación electrónica requiere un cliente válido.";
        }

        return null;
    }

    public ResultadoFacturacion facturar(String tipoDocumento, String idDocumento, int indexComprobante, boolean imprimir) {
        List<String> ids = new ArrayList<>();
        ids.add(idDocumento);
        return facturarDocumentos(tipoDocumento, ids, indexComprobante, imprimir, true);
    }

    public ResultadoFacturacion facturarUnificado(String tipoDocumento, List<String> idsDocumentos, int indexComprobante,
            boolean imprimir) {
        return facturarDocumentos(tipoDocumento, idsDocumentos, indexComprobante, imprimir, true);
    }

    public ResultadoFacturacion facturarSepareQueSeSalda(String idSepare, int indexComprobante, boolean imprimir) {
        List<String> ids = new ArrayList<>();
        ids.add(idSepare);
        return facturarDocumentos(TipoDocumento.PLAN_SEPARE.getValor(), ids, indexComprobante, imprimir, false);
    }

    private ResultadoFacturacion facturarDocumentos(String tipoDocumento, List<String> ids, int indexComprobante,
            boolean imprimir, boolean exigirSaldado) {

        VistaFactura vistaFactura = obtenerVistaFactura(tipoDocumento);
        if (vistaFactura == null) {
            return ResultadoFacturacion.fallo("Tipo de documento no soportado para conversión: " + tipoDocumento);
        }

        List<String> errores = new ArrayList<>();
        List<String> facturables = new ArrayList<>();
        for (String id : ids) {
            String error = validarDocumento(tipoDocumento, id, exigirSaldado);
            if (error != null) {
                errores.add(id + ": " + error);
            } else if (DOCUMENTOS_EN_PROCESO.add(id)) {
                facturables.add(id);
            } else {
                errores.add(id + ": el documento ya se está facturando.");
            }
        }

        if (facturables.isEmpty()) {
            return new ResultadoFacturacion(false, errores);
        }

        try {
            boolean generada = ejecutar(vistaFactura, tipoDocumento, facturables, indexComprobante, imprimir, errores);
            return new ResultadoFacturacion(generada, errores);
        } catch (Exception e) {
            errores.add(facturables.get(0) + ": " + e.getMessage());
            return new ResultadoFacturacion(false, errores);
        } finally {
            vistaFactura.setSaltarPasosFactura(false);
            DOCUMENTOS_EN_PROCESO.removeAll(facturables);
        }
    }

    private boolean ejecutar(VistaFactura vistaFactura, String tipoDocumento, List<String> ids, int indexComprobante,
            boolean imprimir, List<String> errores) {

        String principal = ids.get(0);
        boolean unificado = ids.size() > 1;

        if (!vistaFactura.cargarDocumentoParaConversionAFactura(tipoDocumento, principal)) {
            errores.add(principal + ": no se pudo cargar el documento.");
            return false;
        }

        List<String> incluidos = new ArrayList<>();
        incluidos.add(principal);
        for (int i = 1; i < ids.size(); i++) {
            if (vistaFactura.agregarDocumentoParaUnificacion(tipoDocumento, ids.get(i))) {
                incluidos.add(ids.get(i));
            } else {
                errores.add(ids.get(i) + ": no se pudo agregar al lote unificado.");
            }
        }

        vistaFactura.seleccionarComprobanteParaConversion(indexComprobante);
        vistaFactura.setSaltarPasosFactura(true);

        // En el modo unificado cada documento se marca al final, solo si la factura se genero.
        boolean generada = vistaFactura.ejecutarConversionAFactura(imprimir, unificado ? "" : principal, tipoDocumento);
        if (!generada) {
            errores.add(principal + ": no se generó la factura. Verifique los datos del cliente y la facturación electrónica.");
            return false;
        }

        if (unificado) {
            for (String id : incluidos) {
                vistaFactura.marcarDocumentoOrigenComoConvertido(tipoDocumento, id);
            }
        }

        return true;
    }

    public String validarSepareAntesDeSaldar(String idSepare) {
        return validarDocumento(TipoDocumento.PLAN_SEPARE.getValor(), idSepare, false);
    }

    private String validarDocumento(String tipoDocumento, String idDocumento, boolean exigirSaldado) {
        DocumentoMovimiento documento = daoFactura.cargarDocumentoConvertible(tipoDocumento, idDocumento);
        if (documento == null || documento.isEmpty()) {
            return "el documento no existe.";
        }

        String estado = documento.getCabecera().getEstadoGeneral();
        if (EstadosTipoDocumento.FACTURADA.getNombre().equals(estado)
                || (TipoDocumento.ORDER_SERVICIO.getValor().equals(tipoDocumento) && ESTADO_ORDEN_REALIZADA.equals(estado))) {
            return "el documento ya fue facturado.";
        }
        if (EstadosTipoDocumento.ANULADA.getNombre().equals(estado)) {
            return "el documento está anulado.";
        }

        if (exigirSaldado && TipoDocumento.PLAN_SEPARE.getValor().equals(tipoDocumento) && !estaSaldado(idDocumento)) {
            return "el plan separe tiene saldo pendiente.";
        }

        return null;
    }

    private boolean estaSaldado(String idSepare) {
        ndCxc cuenta = daoFactura.getDatosCxc(idSepare);
        return CXC_CANCELADA.equals(cuenta.getEstado());
    }

    private VistaFactura obtenerVistaFactura(String tipoDocumento) {
        if (TipoDocumento.PEDIDO.getValor().equals(tipoDocumento)) {
            return instancias.getPedido();
        }
        if (TipoDocumento.COTIZACION.getValor().equals(tipoDocumento)) {
            return instancias.getCotiza();
        }
        if (TipoDocumento.ORDER_SERVICIO.getValor().equals(tipoDocumento)) {
            return instancias.getOrdenServicio();
        }
        if (TipoDocumento.PLAN_SEPARE.getValor().equals(tipoDocumento)) {
            return instancias.getPlanSepare();
        }
        return null;
    }

    private boolean contieneClientePorDefecto(List<String> nitsClientes) {
        for (String nit : nitsClientes) {
            String nitCliente = nit != null ? nit : "";
            String nitBase = nitCliente.contains("-") ? nitCliente.split("-")[0] : nitCliente;
            if (Constantes.CLIENTE_POR_DEFECTO.equals(nitBase)) {
                return true;
            }
        }
        return false;
    }
}
