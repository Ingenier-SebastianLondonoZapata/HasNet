package Modelo.Productos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ModeloArmado {

    public static final String TIPO_COSTEO = "COSTEO";

    private String documento;
    private String fecha;
    private String usuario;
    private String producto;
    private String tipo = TIPO_COSTEO;
    private String idBodega;
    private BigDecimal unidades = BigDecimal.ZERO;
    private BigDecimal costoTotal = BigDecimal.ZERO;
    private boolean anulado;
    private final List<LineaArmado> lineas = new ArrayList<>();

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(String idBodega) {
        this.idBodega = idBodega;
    }

    public BigDecimal getUnidades() {
        return unidades == null ? BigDecimal.ZERO : unidades;
    }

    public void setUnidades(BigDecimal unidades) {
        this.unidades = unidades;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal == null ? BigDecimal.ZERO : costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public boolean estaAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public List<LineaArmado> getLineas() {
        return lineas;
    }

    public void agregarLinea(LineaArmado linea) {
        lineas.add(linea);
    }
}
