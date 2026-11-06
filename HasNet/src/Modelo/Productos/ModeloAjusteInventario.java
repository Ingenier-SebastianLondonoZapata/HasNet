package Modelo.Productos;

import Enums.TipoDocumento;
import Modelo.Inventario.DetalleProducto;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ModeloAjusteInventario {

    public static final String TIPO_ENTRADA = "Ajustes Entrada";
    public static final String TIPO_SALIDA = "Ajuste Salida";

    private String documento;
    private String fecha;
    private String hora;
    private String usuario;
    private String terminal;
    private String idBodega;
    private boolean entrada;
    private boolean anulado;
    private final List<LineaAjusteInventario> lineas = new ArrayList<>();
    private final List<DetalleProducto> detallesProductos = new ArrayList<>();

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(String idBodega) {
        this.idBodega = idBodega;
    }

    public boolean esEntrada() {
        return entrada;
    }

    public void setEntrada(boolean entrada) {
        this.entrada = entrada;
    }

    public String getTipo() {
        return entrada ? TIPO_ENTRADA : TIPO_SALIDA;
    }

    public void setTipo(String tipo) {
        this.entrada = TIPO_ENTRADA.equals(tipo);
    }

    public TipoDocumento getTipoDocumento() {
        return entrada ? TipoDocumento.AJUSTE_ENTRADA : TipoDocumento.AJUSTE_SALIDA;
    }

    public TipoDocumento getTipoDocumentoAnulacion() {
        return entrada ? TipoDocumento.ANULAR_AJUSTE_ENTRADA : TipoDocumento.ANULAR_AJUSTE_SALIDA;
    }

    public boolean estaAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public List<LineaAjusteInventario> getLineas() {
        return lineas;
    }

    public void agregarLinea(LineaAjusteInventario linea) {
        lineas.add(linea);
    }

    public List<DetalleProducto> getDetallesProductos() {
        return detallesProductos;
    }

    public void agregarDetallesProductos(List<DetalleProducto> detalles) {
        detallesProductos.addAll(detalles);
    }

    public BigDecimal getUnidades() {
        BigDecimal unidades = BigDecimal.ZERO;
        for (LineaAjusteInventario linea : lineas) {
            unidades = unidades.add(linea.getCantidad());
        }
        return unidades;
    }

    public BigDecimal getValorTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (LineaAjusteInventario linea : lineas) {
            total = total.add(linea.getTotal());
        }
        return total;
    }
}
